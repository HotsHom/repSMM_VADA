package com.example.kafgoodline.presentation.loginScreen.login

import com.arellomobile.mvp.MvpView

interface ILoginView : MvpView {
    fun showError(message: String)
    fun showWork()
}