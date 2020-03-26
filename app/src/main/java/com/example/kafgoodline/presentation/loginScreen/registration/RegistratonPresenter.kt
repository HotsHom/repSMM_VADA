package com.example.kafgoodline.presentation.loginScreen.registration

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.example.kafgoodline.base.SubRX
import com.example.kafgoodline.domain.repositories.UserRepository
import javax.inject.Inject

@InjectViewState
class RegistratonPresenter : MvpPresenter<IRegistrationView> {

    @Inject
    lateinit var userRepository: UserRepository

    @Inject
    constructor()

    fun registration(login: String, pass1: String) {

        userRepository.registration(SubRX { _, e ->

            if (e != null) {
                e.printStackTrace()
                viewState.showError(e.message)
                return@SubRX
            }

            viewState.showLoginSc()
        }, login, pass1)
    }
}