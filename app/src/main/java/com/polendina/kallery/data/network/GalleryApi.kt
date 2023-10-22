package com.polendina.kallery.data.network

import com.polendina.kallery.data.model.Album
import com.polendina.kallery.data.model.Photo
import com.polendina.kallery.data.model.User
import retrofit2.Call
import retrofit2.http.GET

interface GalleryApi {
    @GET("users")
    fun getUsers(): Call<List<User>>
    @GET("albums")
    fun getAlbums(): Call<List<Album>>
    @GET("photos")
    fun getPhotos(): Call<List<Photo>>
}