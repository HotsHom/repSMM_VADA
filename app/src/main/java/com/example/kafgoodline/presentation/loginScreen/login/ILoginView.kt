package com.example.kafgoodline.presentation.loginScreen.login

import com.example.kafgoodline.base.IBaseView

interface ILoginView : IBaseView {
    override fun lock() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun unlock() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    fun showError(message: String?)
    fun showWork()
}