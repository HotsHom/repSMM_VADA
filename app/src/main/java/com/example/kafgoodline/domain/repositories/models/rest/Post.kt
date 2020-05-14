package com.example.kafgoodline.domain.repositories.models.rest

import com.google.gson.annotations.SerializedName

data class Post(
    var id: Int? = 0,
    var user_id: String? = null,
    var title: String? = null,
    var text: String? = null,
    var vk: Boolean? = null,
    var date_post: String = "",
    var group_id: String? = null
)