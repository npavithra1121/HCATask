package com.hca.hcatask.domain

import com.hca.hcatask.model.AnswerResponse
import com.hca.hcatask.model.QuestionResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface ApiInterface {

    @GET("2.2/questions?order=asc&site=stackoverflow")
    suspend fun getQuestionsResults(
            @Query("page") page: Int,
            @Query("pagesize") perPage: Int
    ): QuestionResponse

    @GET("2.2/questions/{question_id}/answers?order=asc&sort=activity&site=stackoverflow&filter=withbody")
    suspend fun getAnswerResults(@Path("question_id") question_id: Int): AnswerResponse
}