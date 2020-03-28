package com.example.kafgoodline.domain.repositories.local

import com.example.kafgoodline.domain.repositories.models.User
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
        if(flag == "Finish"){
            val  user = this.user ?: return
                user.firstname = _user.firstname
                user.secondname = _user.secondname
                user.isNewUser = false
        }
    }

    fun dropCredentials() {
        user = null
    }
}