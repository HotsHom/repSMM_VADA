package com.example.kafgoodline.presentation.loginScreen.registration

import com.arellomobile.mvp.MvpView

interface IRegistrationView : MvpView {
    fun showError(message: String?)
    fun showLoginSc()

}