package com.example.kafgoodline.domain.di.models

data class User(
    val id: Int? = null,
    var username: String,
    var password: String,
    var access: String? = null,
    var refresh: String? = null
)