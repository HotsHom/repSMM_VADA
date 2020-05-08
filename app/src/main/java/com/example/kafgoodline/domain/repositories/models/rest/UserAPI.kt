package com.example.kafgoodline.domain.repositories.models.rest

import com.google.gson.annotations.SerializedName

data class UserAPI(
    var id: Int? = 0,
    var user_id: String? = null,
    var token: String? = null,
    var title: String? = null,
    var text: String? = null,
    var likes: String? = null
)