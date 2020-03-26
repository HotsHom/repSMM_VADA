package com.example.kafgoodline.domain.repositories.local

import com.example.kafgoodline.domain.di.models.Token
import com.example.kafgoodline.domain.di.models.User
import javax.inject.Inject

class UserStorage {

    var user: User? = null
        private set

    @Inject
    constructor()

    fun save(user: User, login: String, pass: String) {
        user.username = login
        this.user = user

    }

    fun save(_user: User, flag: String? = null){
        if (flag.isNullOrEmpty()){
            val user = this.user ?: return
                user.access = _user.access
                user.refresh = _user.access
        }
    }

    fun dropCredentials() {
        user = null
    }
}