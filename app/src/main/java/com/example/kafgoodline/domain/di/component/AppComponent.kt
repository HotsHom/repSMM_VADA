package com.example.kafgoodline.domain.di.component

import com.example.kafgoodline.domain.di.module.NetModule
import com.example.kafgoodline.presentation.loginScreen.MainActivity
import com.example.kafgoodline.presentation.loginScreen.loading.LoadFragment
import com.example.kafgoodline.presentation.loginScreen.login.LoginFragment
import com.example.kafgoodline.presentation.loginScreen.registration.RegisterFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [
//    AppModule::class,
    NetModule::class
])
interface AppComponent {

    fun inject(target: RegisterFragment)
    fun inject(target: LoginFragment)
    fun inject(target: LoadFragment)
    fun inject(target: MainActivity)

}