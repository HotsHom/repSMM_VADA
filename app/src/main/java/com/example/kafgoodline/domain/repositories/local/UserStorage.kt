package com.example.kafgoodline.domain.repositories.local

import com.example.kafgoodline.domain.di.models.Token
import com.example.kafgoodline.domain.di.models.User
import javax.inject.Inject

class UserStorage {

    var user: User? = null
        private set

    var tokens: Token? = null
        private set

    @Inject
    constructor()

    fun save(user: User) {
        this.user = user
    }

    fun save(token: Token) {
        user?.token = token
    }

    fun dropCredentials() {
        user = null
    }
}