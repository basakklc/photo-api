package com.example.photo.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.photo.domain.model.PhotoResponse
import com.example.photo.domain.usecase.PhotoListingUseCase
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers

class PhotoListingViewModel(private val photoListingUseCase: PhotoListingUseCase) : ViewModel()   {

    private val compositeDisposable = CompositeDisposable()

    private val photos = MutableLiveData<List<PhotoResponse>>()

    fun fetchPhotos() {
        compositeDisposable.add(
            photoListingUseCase.getPhotos()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ photos.postValue(it)},{ throwable ->photos.postValue(null)})
        )
    }

    fun getPhoto(): MutableLiveData<List<PhotoResponse>> {
        return photos
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()

    }
}