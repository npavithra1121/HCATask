package com.hca.hcatask.model

import com.google.gson.annotations.SerializedName

data class QuestionItem(@SerializedName("owner")
                     val owner: Owner,
                        @SerializedName("content_license")
                     val contentLicense: String = "",
                        @SerializedName("link")
                     val link: String = "",
                        @SerializedName("last_activity_date")
                     val lastActivityDate: Int = 0,
                        @SerializedName("creation_date")
                     val creationDate: Int = 0,
                        @SerializedName("answer_count")
                     val answerCount: Int = 0,
                        @SerializedName("title")
                     val title: String = "",
                        @SerializedName("question_id")
                     val questionId: Int = 0,
                        @SerializedName("tags")
                     val tags: List<String>?,
                        @SerializedName("score")
                     val score: Int = 0,
                        @SerializedName("accepted_answer_id")
                     val acceptedAnswerId: Int = 0,
                        @SerializedName("is_answered")
                     val isAnswered: Boolean = false,
                        @SerializedName("view_count")
                     val viewCount: Int = 0,
                        @SerializedName("last_edit_date")
                     val lastEditDate: Int = 0)