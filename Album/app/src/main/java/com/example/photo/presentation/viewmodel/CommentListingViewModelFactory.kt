package com.example.photo.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.photo.domain.usecase.CommentListingUseCase

class CommentListingViewModelFactory(private  val commentListingUseCase: CommentListingUseCase,
                                     private val id: Int) : ViewModelProvider.Factory  {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CommentListingViewModel::class.java)) {
            return CommentListingViewModel(commentListingUseCase,id) as T
        }
        throw IllegalArgumentException("Unknown class name")
    }
}