package com.example.kafgoodline.domain.repositories.models.realm

import com.google.gson.annotations.SerializedName
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class SocialRealm : RealmObject() {

    @PrimaryKey
    var id: Int? = 0
    var vkToken: String? = null
}