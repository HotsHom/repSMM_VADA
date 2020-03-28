package com.example.kafgoodline.domain.repositories.models

import com.google.gson.annotations.SerializedName

data class User(
    val id: Int? = null,
    var username: String,
    var password: String,
    @SerializedName("first_name")
    var firstname: String? = null,
    @SerializedName("last_name")
    var secondname: String? = null,
    var access: String? = null,
    var refresh: String? = null,
    var isNewUser: Boolean? = true
)