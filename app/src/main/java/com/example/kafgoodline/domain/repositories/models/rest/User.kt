package com.example.kafgoodline.domain.repositories.models.rest

import com.google.gson.annotations.SerializedName

data class User(
    var id: Int? = 0,
    var username: String? = null,
    var password: String? = null,
    @SerializedName("first_name")
    var firstname: String? = null,
    @SerializedName("last_name")
    var secondname: String? = null,
    var access: String? = null,
    var refresh: String? = null,
    var isNewUser: Boolean? = true,
    var vkToken: String? = null
)