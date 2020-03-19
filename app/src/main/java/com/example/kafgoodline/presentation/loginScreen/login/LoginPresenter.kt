package com.example.kafgoodline.presentation.loginScreen.login

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.example.kafgoodline.repositories.UserRepository

@InjectViewState
class LoginPresenter : MvpPresenter<ILoginView>() {

    var userRepository: UserRepository = UserRepository()

    fun login(login: String, pass: String) {

        //TODO Инициализация окна блокировки

        userRepository.login({
            //TODO Скрытие окна блокировки
            viewState.showError(it)}, login, pass)
    }

}