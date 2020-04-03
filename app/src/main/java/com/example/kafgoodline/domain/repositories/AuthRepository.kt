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
class AuthRepository {

    private val restWithOutToken : UserRestApi
    private val storage: UserStorage


    @Inject
    constructor(storage: UserStorage, rest : UserRestApi){
        this.storage = storage
        this.restWithOutToken = rest
        this.storage.getUser() //TODO УБРАТЬ
    }

    fun login(observer: SubRX<User>, login: String, pass: String) {
        restWithOutToken.login(login, pass).doOnNext {

//            val user = it ?: return@doOnNext
//                user.isNewUser = null
            storage.save(it, login, pass)
        }.doOnError {  }.standardSubscribeIO(observer)
    }

    fun registration(observer: SubRX<User>, login: String, pass: String) {
        restWithOutToken.registration(login, pass).doOnNext {
            storage.save(it, login, pass)
        }.doOnError {  }.standardSubscribeIO(observer)
    }

    fun getUser() = storage.getUser()

    fun getUserStatus() : Boolean? {
        val _user = getUser()
        val isUserStatus = _user?.isNewUser
        return isUserStatus
    }

    fun refreshToken(token: User, onRetry: (Int) -> Boolean = { it == HttpURLConnection.HTTP_UNAUTHORIZED }): User? {

        val response = restWithOutToken.refreshToken(token).execute()
        response.body()?.let {
            storage.save(it)
            return it
        }

        if (onRetry(response.code())) {
            SystemClock.sleep(500)
            return refreshToken(token)
        }

        return null
    }
}