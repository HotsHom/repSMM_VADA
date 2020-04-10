package com.example.kafgoodline.domain.repositories.local

import com.example.kafgoodline.domain.repositories.models.realm.UserRealm
import com.example.kafgoodline.domain.repositories.models.rest.User
import com.example.kafgoodline.domain.repositories.models.toBase
import com.example.kafgoodline.domain.repositories.models.toRealm
import io.realm.Realm
import io.realm.RealmConfiguration
import javax.inject.Inject

class UserStorage {

    private var user: User? = null


    @Inject
    constructor()

    fun save(_user: User, login: String, pass: String) {
        _user.username = login
        this.user = _user
        if (_user.firstname != ""){
            val user = this.user ?: return
                user.firstname = _user.firstname
                user.secondname = _user.secondname
                user.isNewUser = false
        }

        Realm.getDefaultInstance().use {
            it.executeTransaction {
                it.insertOrUpdate(user.toRealm())
            }
        }
    }

    fun save(_user: User, flag: String? = null){
        if (flag.isNullOrEmpty()){
            val user = getUser() ?: return
                user.access = _user.access
                user.refresh = _user.access
            Realm.getDefaultInstance().use {
                it.executeTransaction {
                    it.insertOrUpdate(user.toRealm())
                }
            }
        }
        if(flag == "Finish"){
            val  user = getUser() ?: return
                user.firstname = _user.firstname
                user.secondname = _user.secondname
                user.isNewUser = false
            Realm.getDefaultInstance().use {
                it.executeTransaction {
                    it.insertOrUpdate(user.toRealm())
                }
            }
        }
    }

    fun dropCredentials() {
        user = null
        Realm.getDefaultInstance().use {
            it.executeTransaction {
                it.where(UserRealm :: class.java).findAll().deleteAllFromRealm()
            }
        }
    }

    fun getUser() : User? {

        user?.let {
            return  it
        }

        Realm.getDefaultInstance().use {
            return it.where(UserRealm::class.java).findFirst()?.toBase().apply {
                    user = this
            }
        }
    }

    fun Delete(){
        Realm.deleteRealm(Realm.getDefaultConfiguration())
    }
}
