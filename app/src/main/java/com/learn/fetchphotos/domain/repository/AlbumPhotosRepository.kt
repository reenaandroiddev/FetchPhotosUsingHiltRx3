package com.learn.fetchphotos.domain.repository

import com.learn.fetchphotos.domain.entities.AlbumPhotos
import io.reactivex.rxjava3.core.Observable

interface AlbumPhotosRepository {
    fun getAlbumPhotos(): Observable<List<AlbumPhotos>>
}