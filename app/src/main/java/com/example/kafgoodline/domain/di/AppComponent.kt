package com.example.kafgoodline.domain.di

import com.example.kafgoodline.presentation.loginScreen.registration.RegisterFragment
import com.example.kafgoodline.repositories.UserRepository
import dagger.Component

@Component
interface AppComponent {

    fun inject(target: RegisterFragment)
    fun inject(target: UserRepository)

}