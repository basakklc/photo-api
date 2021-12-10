package com.example.photo.data.network

import com.example.photo.domain.model.AlbumResponse
import com.example.photo.domain.model.PhotoResponse
import com.example.photo.domain.model.CommentResponse
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface PhotoService {

    @GET("photos")
    fun getPhotos() : Single<List<PhotoResponse>>

    @GET("comments")
    fun getComments(@Query("postId") postId: Int ): Single<List<CommentResponse>>

    @GET("albums")
    fun getAlbum(@Query("id") albumId: Int ): Single<List<AlbumResponse>>




}
