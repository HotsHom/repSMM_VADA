package com.example.kafgoodline.presentation.loginScreen

import android.view.View

interface ICredentionalsRouter {
    fun showLoading()
    fun showLogin(view: View?)
    fun showRegistration(view: View)
    fun goToMainWorkScreen()
}