package com.example.kafgoodline.presentation.loginScreen

import com.arellomobile.mvp.MvpView

interface IMainActivity : MvpView {
    fun showRegisterScreen()
    fun showLoginScreen()
    fun goToMainWorkScreen()
}