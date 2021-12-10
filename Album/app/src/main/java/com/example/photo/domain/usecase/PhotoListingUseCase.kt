package com.example.photo.domain.usecase

import com.example.photo.domain.model.AlbumResponse
import com.example.photo.domain.model.PhotoResponse
import com.example.photo.domain.repository.PhotoListingRepository
import io.reactivex.rxjava3.core.Single

class PhotoListingUseCase (private val albumListingRepository: PhotoListingRepository) {

    fun getPhotos(): Single<List<PhotoResponse>> = albumListingRepository.getPhotos()
    fun getAlbum(albumId:Int): Single<List<AlbumResponse>> = albumListingRepository.getAlbum(albumId)
}