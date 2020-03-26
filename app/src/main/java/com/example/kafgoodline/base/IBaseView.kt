package com.example.kafgoodline.base

import com.arellomobile.mvp.MvpView

interface IBaseView : MvpView {

    fun lock()
    fun unlock()
}