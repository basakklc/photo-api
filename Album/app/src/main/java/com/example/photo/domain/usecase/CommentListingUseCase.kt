package com.example.photo.domain.usecase

import com.example.photo.domain.model.CommentResponse
import com.example.photo.domain.repository.CommentListingRepository
import io.reactivex.rxjava3.core.Single

class CommentListingUseCase (private val photoDetailRepository: CommentListingRepository){

    fun getComments(postId:Int): Single<List<CommentResponse>> = photoDetailRepository.getComments(postId)

}