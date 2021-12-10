package com.example.photo.domain.model

import com.google.gson.annotations.SerializedName

data class PhotoResponse (

    @SerializedName("albumId")
    val albumId: Int,

    @SerializedName("id")
    var id: Int,

    @SerializedName("title")
    var title: String,

    @SerializedName("url")
    var url: String,

    @SerializedName("thumbnailUrl")
    val thumbnailUrl: String


)