package com.example.photo.domain.model

import com.google.gson.annotations.SerializedName

data class CommentResponse (
    @SerializedName("postId")
    val postId: Int,

    @SerializedName("id")
    var id: Int,

    @SerializedName("name")
    var name: String,

    @SerializedName("email")
    var email: String,

    @SerializedName("body")
    var body: String
)