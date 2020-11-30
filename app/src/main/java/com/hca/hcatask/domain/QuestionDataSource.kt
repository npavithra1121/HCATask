package com.hca.hcatask.domain

import androidx.paging.PagingSource
import com.hca.hcatask.model.QuestionItem
import com.hca.hcatask.utils.HcaConstants.Companion.QUERY_PAGE_SIZE
import com.hca.hcatask.utils.NoInternetException
import javax.inject.Inject

class QuestionDataSource @Inject constructor(private val repository: ApiHelper) :
        PagingSource<Int, QuestionItem>() {
    private val initialPageIndex: Int = 1
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, QuestionItem> {
        return try {
            val position = params.key ?: initialPageIndex
            val response = repository.getQuestionsData(position, QUERY_PAGE_SIZE)
            val responseData = mutableListOf<QuestionItem>()
            val data = response.items
            responseData.addAll(data!!)
            LoadResult.Page(
                    data = responseData,
                    prevKey = if (position == initialPageIndex) null else position - 1,
                    nextKey = if (responseData.isEmpty()) null else position + 1
            )
        } catch (e: NoInternetException) {
            return LoadResult.Error(e)
        } catch (e: Exception) {
            return LoadResult.Error(e)
        }
    }
}