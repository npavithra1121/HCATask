package com.hca.hcatask.domain

import javax.inject.Inject

class ApiHelper @Inject constructor(private val apiInterface: ApiInterface) {

    suspend fun getQuestionsData(page: Int, perPage: Int) = apiInterface.getQuestionsResults(page, perPage)

    suspend fun getAnswersData(question_id: Int) = apiInterface.getAnswerResults(question_id)

}