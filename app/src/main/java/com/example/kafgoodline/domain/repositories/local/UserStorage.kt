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
        this.user = user
        this.user!!.username = login
    }

    fun save(user: User, flag: String? = null){
        if (flag.isNullOrEmpty()){
            this.user!!.access = user.access
            this.user!!.refresh = user.refresh
        }
    }

    fun dropCredentials() {
        user = null
    }
}