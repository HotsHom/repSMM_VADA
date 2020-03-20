package com.example.kafgoodline.presentation.loginScreen

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.example.kafgoodline.presentation.loginScreen.login.ILoginView
import com.example.kafgoodline.repositories.UserRepository

@InjectViewState
class MainActivityPresenter : MvpPresenter<IMainActivity>() {

    var userRepository: UserRepository = UserRepository()

    fun showRegister(){
        viewState.showRegisterScreen()
    }

    fun showLogin(){
        viewState.showLoginScreen()
    }

    fun goToWork(){
        viewState.goToMainWorkScreen()
    }
}