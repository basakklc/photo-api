package com.example.photo.domain.repository

import com.example.photo.domain.model.CommentResponse
import io.reactivex.rxjava3.core.Single

interface CommentListingRepository {

    fun getComments(postId:Int): Single<List<CommentResponse>>
}