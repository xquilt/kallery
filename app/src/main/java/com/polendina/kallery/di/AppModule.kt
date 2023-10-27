package com.polendina.kallery.di

import android.app.Application
import com.polendina.kallery.data.network.GalleryApi
import com.polendina.kallery.data.repository.GalleryRepository
import com.polendina.kallery.data.repository.JsonPlaceHolderRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.HiltAndroidApp
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideGalleryApi(): GalleryApi {
        return Retrofit.Builder()
            .baseUrl("https://jsonplaceholder.typicode.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(GalleryApi::class.java)
    }

    @Provides
    @Singleton
    fun provideJsonPlaceholderRepository(galleryApi: GalleryApi): GalleryRepository {
        return JsonPlaceHolderRepository(galleryApi)
    }

}

@HiltAndroidApp
class MyApp: Application()