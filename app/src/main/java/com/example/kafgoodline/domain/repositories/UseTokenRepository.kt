package com.example.kafgoodline.domain.repositories

import android.os.SystemClock
import com.example.kafgoodline.base.SubRX
import com.example.kafgoodline.base.standardSubscribeIO
import com.example.kafgoodline.domain.repositories.models.rest.User
import com.example.kafgoodline.domain.repositories.local.UserStorage
import com.example.kafgoodline.domain.repositories.rest.api.UserRestApi
import com.example.kafgoodline.domain.repositories.rest.api.UserWithTokenRestApi
import java.net.HttpURLConnection
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UseTokenRepository {

    private val restWithToken : UserWithTokenRestApi
    private val storage: UserStorage


    @Inject
    constructor(storage: UserStorage, restToken: UserWithTokenRestApi){
        this.storage = storage
        this.restWithToken = restToken
        this.storage.getUser() //TODO УБРАТЬ
    }

    fun putFSName(observer: SubRX<User>, firstName: String, secondName: String){

        val _user = getUser() ?: return
        _user.firstname = firstName
        _user.secondname = secondName

        restWithToken.putFSName(_user)?.doOnNext {
            storage.save(it, "Finish")
        }?.doOnError {  }?.standardSubscribeIO(observer)
    }

    fun getUser() = storage.getUser()

    fun getUserStatus() : Boolean? {
        val _user = getUser()
        val isUserStatus = _user?.isNewUser
        return isUserStatus
    }
}