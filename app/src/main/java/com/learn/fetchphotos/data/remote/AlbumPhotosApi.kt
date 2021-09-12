package com.learn.fetchphotos.data.remote

import io.reactivex.rxjava3.core.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface AlbumPhotosApi {

    @GET("photos")
    fun getAlbumPhotos(@Query("albumId") albumId: Int): Observable<List<AlbumPhotosResponse>>
}