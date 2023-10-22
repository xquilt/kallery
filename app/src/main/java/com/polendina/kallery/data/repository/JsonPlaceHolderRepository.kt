package com.polendina.kallery.data.repository

import com.polendina.kallery.data.model.Album
import com.polendina.kallery.data.model.Photo
import com.polendina.kallery.data.model.User
import com.polendina.kallery.data.network.GalleryApi

class JsonPlaceHolderRepository(
    private val galleryApi: GalleryApi
): GalleryRepository {
    override suspend fun getUsers(): List<User>? = galleryApi.getUsers().execute().body()
    override suspend fun albumPhotos(): List<Photo>? = galleryApi.getPhotos().execute().body()
    override suspend fun userAlbums(): List<Album>? = galleryApi.getAlbums().execute().body()
}