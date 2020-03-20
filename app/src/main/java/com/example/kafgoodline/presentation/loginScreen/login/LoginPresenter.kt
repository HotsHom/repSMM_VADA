package com.example.kafgoodline.presentation.loginScreen.login

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.example.kafgoodline.presentation.loginScreen.IMainActivity
import com.example.kafgoodline.presentation.loginScreen.MainActivity
import com.example.kafgoodline.presentation.loginScreen.MainActivityPresenter
import com.example.kafgoodline.repositories.UserRepository

@InjectViewState
class LoginPresenter : MvpPresenter<ILoginView>() {

    var userRepository: UserRepository = UserRepository()

    fun login(login: String, pass: String) {

        //TODO Инициализация окна блокировки

        userRepository.login({
            //TODO Скрытие окна блокировки
            if (it == "1 : 1"){
                viewState.showWork()
            }else{
            viewState.showError(it)}}, login, pass)
    }

}