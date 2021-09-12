package com.learn.fetchphotos.data.mapper

import com.learn.fetchphotos.data.remote.AlbumPhotosResponse
import com.learn.fetchphotos.domain.entities.AlbumPhotos

object ResponseToEntityMapper : Mapper<List<AlbumPhotosResponse>, List<AlbumPhotos>> {
    override fun doMapping(from: List<AlbumPhotosResponse>): List<AlbumPhotos> {
        return from.map {
            AlbumPhotos(
                albumId = it.albumId,
                id = it.id,
                title = it.title,
                thumbnailUrl = it.thumbnailUrl,
                url = it.url
            )
        }
    }
}