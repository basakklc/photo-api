package com.example.photo.data.repository


import com.example.photo.data.network.PhotoService
import com.example.photo.data.network.ApiClient
import com.example.photo.domain.model.CommentResponse
import com.example.photo.domain.repository.CommentListingRepository
import io.reactivex.rxjava3.core.Single

class CommentListingRepositoryImpl : CommentListingRepository {

    var photoService = ApiClient.getClient().create(PhotoService::class.java)

    override fun getComments(postId:Int): Single<List<CommentResponse>> = photoService.getComments(postId)
}