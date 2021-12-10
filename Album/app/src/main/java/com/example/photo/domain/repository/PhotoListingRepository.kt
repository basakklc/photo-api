package com.example.photo.domain.repository

import com.example.photo.domain.model.AlbumResponse
import com.example.photo.domain.model.PhotoResponse
import io.reactivex.rxjava3.core.Single

interface PhotoListingRepository  {

    fun getPhotos(): Single<List<PhotoResponse>>

    fun getAlbum(albumId:Int): Single<List<AlbumResponse>>

}