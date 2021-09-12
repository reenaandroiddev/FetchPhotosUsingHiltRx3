package com.learn.fetchphotos.domain.entities

data class AlbumPhotos(
    val albumId: Int,
    val id: Int,
    val title: String,
    val url: String,
    val thumbnailUrl: String
)

