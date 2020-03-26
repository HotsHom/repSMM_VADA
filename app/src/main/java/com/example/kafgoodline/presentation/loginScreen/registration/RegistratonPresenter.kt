package com.example.kafgoodline.presentation.loginScreen.registration

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.example.kafgoodline.domain.repositories.UserRepository
import javax.inject.Inject

@InjectViewState
class RegistratonPresenter : MvpPresenter<IRegistrationView> {

    @Inject
    lateinit var userRepository: UserRepository

    @Inject
    constructor()

    fun registration(login: String, pass1: String, pass2: String) {

        //TODO Инициализация окна блокировки

        userRepository.registration({
            //TODO Скрытие окна блокировки
            if (it == "1 : 1 : 1") {
                viewState.showLoginSc()
            } else {
                viewState.showError(it)
            }
        }, login, pass1, pass2)
    }
}