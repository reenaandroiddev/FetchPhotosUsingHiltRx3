package com.learn.fetchphotos.data.remote

data class AlbumPhotosResponse(
    val albumId: Int,
    val id: Int,
    val title: String,
    val url: String,
    val thumbnailUrl: String
)