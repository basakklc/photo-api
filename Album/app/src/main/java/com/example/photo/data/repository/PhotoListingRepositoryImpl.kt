package com.example.photo.data.repository

import com.example.photo.data.network.PhotoService
import com.example.photo.data.network.ApiClient
import com.example.photo.domain.model.AlbumResponse
import com.example.photo.domain.model.PhotoResponse
import com.example.photo.domain.repository.PhotoListingRepository
import io.reactivex.rxjava3.core.Single

class PhotoListingRepositoryImpl : PhotoListingRepository{

    var photoService = ApiClient.getClient().create(PhotoService::class.java)

    override fun getPhotos(): Single<List<PhotoResponse>> = photoService.getPhotos()
    override fun getAlbum(albumId: Int): Single<List<AlbumResponse>> = photoService.getAlbum(albumId)
}