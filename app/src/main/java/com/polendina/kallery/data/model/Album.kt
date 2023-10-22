package com.polendina.kallery.data.model

data class Album(
    val userId: Int,
    val id: Int,
    val title: String,
    var favorite: Boolean?
)