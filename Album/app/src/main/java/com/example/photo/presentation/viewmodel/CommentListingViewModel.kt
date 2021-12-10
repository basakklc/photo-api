package com.example.photo.presentation.viewmodel


import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.photo.domain.model.CommentResponse
import com.example.photo.domain.usecase.CommentListingUseCase
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers

class CommentListingViewModel (private val commentListingUseCase: CommentListingUseCase,
                               private val postId:Int ): ViewModel() {

    private val compositeDisposable = CompositeDisposable()

    private val comments = MutableLiveData<List<CommentResponse>>()

    init {
        fetchComments()
    }

    private fun fetchComments() {
        compositeDisposable.add(
            commentListingUseCase.getComments(postId)
                .toObservable()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({comments.postValue(it)},{throwable ->comments.postValue(null)})
        )

    }

    fun getComments(): MutableLiveData<List<CommentResponse>> {
        return comments
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()

    }
}