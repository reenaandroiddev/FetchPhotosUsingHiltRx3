package com.learn.fetchphotos.domain.usecases

import com.learn.fetchphotos.domain.entities.AlbumPhotos
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.observers.DisposableObserver

interface GetAlbumPhotosUseCase {
    fun getAlbumPhotos(): Observable<List<AlbumPhotos>>
}