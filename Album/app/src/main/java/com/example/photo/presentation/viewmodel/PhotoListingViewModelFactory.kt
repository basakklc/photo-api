package com.example.photo.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.photo.domain.usecase.PhotoListingUseCase

class PhotoListingViewModelFactory(private  val photoListingUseCase: PhotoListingUseCase) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PhotoListingViewModel::class.java)) {
            return PhotoListingViewModel(photoListingUseCase) as T
        }
        throw IllegalArgumentException("Unknown class name")
    }
}
