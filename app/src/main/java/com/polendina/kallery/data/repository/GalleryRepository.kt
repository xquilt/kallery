package com.polendina.kallery.data.repository

import com.polendina.kallery.data.model.Album
import com.polendina.kallery.data.model.Photo
import com.polendina.kallery.data.model.User

interface GalleryRepository {
    suspend fun getUsers(): List<User>?
    suspend fun userAlbums(): List<Album>?
    suspend fun albumPhotos(): List<Photo>?
}