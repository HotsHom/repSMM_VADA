package com.example.kafgoodline.domain.repositories.models.realm

import com.google.gson.annotations.SerializedName
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class UserRealm : RealmObject() {

    @PrimaryKey
    var id: Int? = 0
    var username: String? = null
    var password: String? = null
    var firstname: String? = null
    var secondname: String? = null
    var access: String? = null
    var refresh: String? = null
    var isNewUser: Boolean? = true
}