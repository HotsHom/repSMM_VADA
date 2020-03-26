package com.example.kafgoodline.domain.di.models

data class User(
    val id: Int? = null,
    val username: String,
    val password: String,
    var token: Token? = null
)