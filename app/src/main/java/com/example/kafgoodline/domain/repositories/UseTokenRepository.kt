package com.example.kafgoodline.domain.repositories

import com.example.kafgoodline.base.SubRX
import com.example.kafgoodline.base.standardSubscribeIO
import com.example.kafgoodline.domain.repositories.local.UserStorage
import com.example.kafgoodline.domain.repositories.models.rest.Post
import com.example.kafgoodline.domain.repositories.models.rest.User
import com.example.kafgoodline.domain.repositories.models.rest.UserAPI
import com.example.kafgoodline.domain.repositories.rest.api.UserWithTokenRestApi
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UseTokenRepository {

    private val restWithToken: UserWithTokenRestApi
    private val storage: UserStorage


    @Inject
    constructor(storage: UserStorage, restToken: UserWithTokenRestApi) {
        this.storage = storage
        this.restWithToken = restToken
        this.storage.getUser() //TODO УБРАТЬ
    }

    fun putFSName(observer: SubRX<User>, firstName: String, secondName: String) {

        val _user = getUser() ?: return
        _user.firstname = firstName
        _user.secondname = secondName

        restWithToken.putFSName(_user)?.doOnNext {
            storage.save(it, "Finish")
        }?.doOnError { }?.standardSubscribeIO(observer)
    }

    fun getUser() = storage.getUser()

    fun getUserStatus(): Boolean? {
        val _user = getUser()
        val isUserStatus = _user?.isNewUser
        return isUserStatus
    }

    fun putVkTokenFinction(observer: SubRX<UserAPI>, token: String) {

        val user: User?
        user = storage.getUser()
        var userID = user?.id.toString()


        val userToken = UserAPI()
        userToken.user_id = userID
        userToken.token = token


        restWithToken.putVkToken(userToken)?.doOnNext {
            storage.saveToken(token)
        }?.doOnError { }?.standardSubscribeIO(observer)

    }

    fun getTokenVk(observer: SubRX<UserAPI>, user: User) {
        var user : User = getUser() ?: return
        restWithToken.getTokenVk(user)?.doOnNext{
            it.token?.let { it1 -> storage.saveToken(it1) }
        }?.doOnError{

        }?.standardSubscribeIO(observer)

    }

    fun saveIdVkGroup(id: String) {
        storage.saveIdVkGroup(id)
    }

    fun logout() {
        storage.logout()
    }

    fun putPostDelay(observer: SubRX<Post>, text: String, title: String, date: String) {

        val _post = Post()
        _post.user_id = getUser()?.id.toString()
        _post.title = title
        _post.text = text
        _post.vk = true
        _post.date_post = date
        _post.group_id = getUser()?.vkIdGroup

        restWithToken.putPostDelay(_post)?.doOnNext {

        }?.doOnError { }?.standardSubscribeIO(observer)
    }
}