package com.example.kafgoodline.presentation.loginScreen.login

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.example.kafgoodline.repositories.UserRepository
import javax.inject.Inject

@InjectViewState
class LoginPresenter : MvpPresenter<ILoginView> {

    @Inject
    constructor()

    var userRepository: UserRepository = UserRepository()

    fun login(login: String, pass: String) {

        //TODO Инициализация окна блокировки

        userRepository.login({
            //TODO Скрытие окна блокировки
            if (it == "1 : 1") {
                viewState.showWork()
            } else {
                viewState.showError(it)
            }
        }, login, pass)
    }

}