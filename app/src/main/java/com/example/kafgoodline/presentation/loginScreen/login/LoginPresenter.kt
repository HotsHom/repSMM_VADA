package com.example.kafgoodline.presentation.loginScreen.login

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.example.kafgoodline.base.SubRX
import com.example.kafgoodline.domain.repositories.UserRepository
import javax.inject.Inject

@InjectViewState
class LoginPresenter : MvpPresenter<ILoginView> {

    @Inject
    constructor()

    @Inject
    lateinit var userRepository: UserRepository

    fun login(login: String, password: String) {

        userRepository.login(SubRX { _, e ->

            if (e != null) {
                e.printStackTrace()
                viewState.showError(e.message)
                return@SubRX
            }

            viewState.showWork()
        }, login, password)
    }


}