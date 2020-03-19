package com.example.kafgoodline.presentation.loginScreen.login

import com.arellomobile.mvp.MvpView

interface ILoginView : MvpView {
    //Показать сообщение об ошибке
        fun showError(message: String)
    //Показать Фрагмент регистрации
        fun showRegisterScreen()
    //Перейти к основному рабочему активити приложения
        fun goToWorkActivity()
}