package com.example.kafgoodline.presentation.mainScreen.startApp

import com.arellomobile.mvp.MvpView

interface IStartView : MvpView {
    fun doFinishRegsidtarion()
    fun showError(message: String?)
    fun showHome()
}