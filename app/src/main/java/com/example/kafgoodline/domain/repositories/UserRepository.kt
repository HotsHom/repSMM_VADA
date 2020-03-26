package com.example.kafgoodline.domain.repositories

import android.os.SystemClock
import com.example.kafgoodline.base.SubRX
import com.example.kafgoodline.base.standardSubscribeIO
import com.example.kafgoodline.domain.di.models.Token
import com.example.kafgoodline.domain.di.models.User
import com.example.kafgoodline.domain.repositories.local.UserStorage
import com.example.kafgoodline.domain.repositories.rest.api.UserRestApi
import java.net.HttpURLConnection
import javax.inject.Inject

class UserRepository {

    private val rest : UserRestApi
    private val storage: UserStorage

    @Inject
    constructor(storage: UserStorage, rest : UserRestApi){
        this.storage = storage
        this.rest = rest
    }

    fun login(observer: SubRX<Token>, login: String, pass: String) {
        rest.login(login, pass).doOnNext {
            println(it)
        }.doOnError {  }.standardSubscribeIO(observer)
    }

    fun registration(subscriber: (String) -> Unit, login: String, pass1: String, pass2: String) {
        subscriber.invoke("$login : $pass1 : $pass2")
    }

    fun getUser() = storage.user
    fun refreshToken(token: Token, onRetry: (Int) -> Boolean = { it == HttpURLConnection.HTTP_UNAUTHORIZED }): Token? {

        val response = rest.refreshToken(token.refresh).execute()
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