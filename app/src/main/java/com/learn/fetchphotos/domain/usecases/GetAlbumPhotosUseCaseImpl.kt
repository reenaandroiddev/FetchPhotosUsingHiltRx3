package com.learn.fetchphotos.domain.usecases

import com.learn.fetchphotos.domain.entities.AlbumPhotos
import com.learn.fetchphotos.domain.repository.AlbumPhotosRepository
import io.reactivex.rxjava3.core.Observable
import javax.inject.Inject

class GetAlbumPhotosUseCaseImpl @Inject constructor(private val repository: AlbumPhotosRepository) :
    GetAlbumPhotosUseCase {
    override fun getAlbumPhotos(): Observable<List<AlbumPhotos>> {
        return repository.getAlbumPhotos()
    }
}