package com.polendina.kallery.utils

import kotlinx.coroutines.runBlocking

import org.junit.Test

class JsonPlaceHolderRepositoryTest {

    @Test
    fun getUsers(): Unit = runBlocking {
        assert(
            JsonPlaceHolderRepository.getUsers()?.isNotEmpty() ?: false
        )
    }

    @Test
    fun albumPhotos(): Unit = runBlocking {
        assert(
            JsonPlaceHolderRepository.albumPhotos()?.isNotEmpty() ?: false
        )
    }

    @Test
    fun userAlbums(): Unit = runBlocking {
        assert(
            JsonPlaceHolderRepository.userAlbums()?.isNotEmpty() ?: false
        )
    }
}