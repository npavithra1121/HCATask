package com.hca.hcatask.viewmodel

import com.hca.hcatask.base.BaseViewModel
import com.hca.hcatask.domain.ApiHelper
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.hca.hcatask.domain.QuestionDataSource
import com.hca.hcatask.utils.HcaConstants.Companion.QUERY_PAGE_SIZE
import dagger.hilt.android.scopes.ActivityRetainedScoped
import javax.inject.Inject

@ActivityRetainedScoped
class QuestionsViewModel @Inject constructor(
        private val repository: ApiHelper
) : BaseViewModel() {

    val listData = Pager(PagingConfig(pageSize = QUERY_PAGE_SIZE)) {
        QuestionDataSource(repository)
    }.flow.cachedIn(viewModelScope)

}