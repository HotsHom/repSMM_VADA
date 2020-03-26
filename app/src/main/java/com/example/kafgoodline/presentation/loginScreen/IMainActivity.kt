package com.example.kafgoodline.presentation.loginScreen

import com.arellomobile.mvp.MvpView

interface IMainActivity : MvpView {
    fun showRegistration()
    fun showLoginScreen()
    fun goToMainWorkScreen()
}