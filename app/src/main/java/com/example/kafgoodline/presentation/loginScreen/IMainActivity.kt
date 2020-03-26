package com.example.kafgoodline.presentation.loginScreen

import com.arellomobile.mvp.MvpView

interface IMainActivity : MvpView {
    fun showRegistrationScreen()
    fun showLoginScreen()
    fun goToMainWorkScreen()
}