package com.example.photo.domain.model

import java.io.Serializable


data class CommentDto (
    var postID: Int,
    var title: String,
    var thumbnailUrl: String
) : Serializable
