package com.example.kafgoodline.domain.repositories.models

import com.example.kafgoodline.domain.repositories.models.realm.UserRealm
import com.example.kafgoodline.domain.repositories.models.rest.User

 fun User?.toRealm() : UserRealm? {

    this ?: return null

    return UserRealm().let {
        it.access = access
        it.refresh = refresh
        it.id = id
        it.firstname = firstname
        it.secondname = secondname
        it.isNewUser = isNewUser
        it.username = username
        it.vkToken = vkToken
        it.vkIdGroup = vkIdGroup
        it
    }
}

fun UserRealm?.toBase() : User? {

    this ?: return null

    return User().let {
        it.access = access
        it.refresh = refresh
        it.id = id ?: 0
        it.firstname = firstname
        it.secondname = secondname
        it.isNewUser = isNewUser
        it.username = username ?: ""
        it.password = password ?: ""
        it.vkToken = vkToken ?: ""
        it.vkIdGroup = vkIdGroup ?: ""
        it
    }
}
