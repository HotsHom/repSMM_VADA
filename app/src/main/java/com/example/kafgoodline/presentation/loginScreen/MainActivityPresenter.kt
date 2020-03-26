package com.example.kafgoodline.presentation.loginScreen

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.example.kafgoodline.repositories.UserRepository
import javax.inject.Inject

@InjectViewState
class MainActivityPresenter : MvpPresenter<IMainActivity> {

    @Inject
    constructor()

    var userRepository: UserRepository = UserRepository()

    fun showRegister() {
        viewState.showRegistrationScreen()
    }

    fun showLogin() {
        viewState.showLoginScreen()
    }

    fun goToWork() {
        viewState.goToMainWorkScreen()
    }
}