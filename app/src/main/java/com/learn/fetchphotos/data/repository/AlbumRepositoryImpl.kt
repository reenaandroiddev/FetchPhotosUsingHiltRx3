package com.learn.fetchphotos.data.repository

import com.learn.fetchphotos.data.mapper.ResponseToEntityMapper
import com.learn.fetchphotos.data.remote.AlbumPhotosApi
import com.learn.fetchphotos.domain.entities.AlbumPhotos
import com.learn.fetchphotos.domain.repository.AlbumPhotosRepository
import io.reactivex.rxjava3.core.Observable
import javax.inject.Inject

class AlbumRepositoryImpl @Inject constructor(private val albumPhotosApi: AlbumPhotosApi) :
    AlbumPhotosRepository {
    override fun getAlbumPhotos(): Observable<List<AlbumPhotos>> {
        return albumPhotosApi.getAlbumPhotos(1).map { ResponseToEntityMapper.doMapping(it) }
    }
}