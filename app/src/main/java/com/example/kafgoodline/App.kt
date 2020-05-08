package com.example.kafgoodline

import android.app.Application
import android.content.Context
import com.vk.api.sdk.VK
import io.realm.Realm
import io.realm.RealmConfiguration

class App : Application() {

    companion object {

        lateinit var appContext: Context
    }

    override fun onCreate() {
        super.onCreate()
        appContext = applicationContext
        VK.initialize(appContext);
        initRealm()
    }

    private fun initRealm(){

        Realm.init(this)
        Realm.setDefaultConfiguration(RealmConfiguration.Builder()
            .deleteRealmIfMigrationNeeded()
            .build())
    }
}