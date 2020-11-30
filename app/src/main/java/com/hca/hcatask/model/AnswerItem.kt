package com.hca.hcatask.model

import com.google.gson.annotations.SerializedName

data class AnswerItem(@SerializedName("owner")
                      val owner: Owner,
                      @SerializedName("content_license")
                      val contentLicense: String = "",
                      @SerializedName("score")
                      val score: Int = 0,
                      @SerializedName("is_accepted")
                      val isAccepted: Boolean = false,
                      @SerializedName("last_activity_date")
                      val lastActivityDate: Int = 0,
                      @SerializedName("creation_date")
                      val creationDate: Int = 0,
                      @SerializedName("body")
                      var body: String = "",
                      @SerializedName("answer_id")
                      val answerId: Int = 0,
                      @SerializedName("question_id")
                      val questionId: Int = 0)