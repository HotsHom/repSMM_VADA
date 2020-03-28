package com.example.kafgoodline.domain.repositories

import android.os.SystemClock
import com.example.kafgoodline.base.SubRX
import com.example.kafgoodline.base.standardSubscribeIO
import com.example.kafgoodline.domain.repositories.models.User
import com.example.kafgoodline.domain.repositories.local.UserStorage
import com.example.kafgoodline.domain.repositories.rest.api.UserRestApi
import java.net.HttpURLConnection
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserRepository {

    private val rest : UserRestApi
    private val storage: UserStorage


    @Inject
    constructor(storage: UserStorage, rest : UserRestApi){
        this.storage = storage
        this.rest = rest
    }

    fun login(observer: SubRX<User>, login: String, pass: String) {
        rest.login(login, pass).doOnNext {

//            val user = it ?: return@doOnNext
//                user.isNewUser = null
            storage.save(it, login, pass)
        }.doOnError {  }.standardSubscribeIO(observer)
    }

    fun registration(observer: SubRX<User>, login: String, pass: String) {
        rest.registration(login, pass).doOnNext {
            storage.save(it, login, pass)
        }.doOnError {  }.standardSubscribeIO(observer)
    }

    fun putFSName(observer: SubRX<User>, firstName: String, secondName: String){

        val _user = getUser() ?: return
        _user.firstname = firstName
        _user.secondname = secondName

        rest.putFSName(_user)?.doOnNext {
            storage.save(it, "Finish")
        }?.doOnError {  }?.standardSubscribeIO(observer)
    }

    fun getUser() = storage.user

    fun getUserStatus() : Boolean? {
        val _user = getUser()
        val isUserStatus = _user?.isNewUser
        return isUserStatus
    }

    fun refreshToken(token: User, onRetry: (Int) -> Boolean = { it == HttpURLConnection.HTTP_UNAUTHORIZED }): User? {

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