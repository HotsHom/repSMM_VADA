package com.example.kafgoodline.presentation.loginScreen.loading

import android.os.Handler
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import javax.inject.Inject

@InjectViewState
class LoadPresenter : MvpPresenter<ILoadView> {

    @Inject
    constructor()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()

        loadStaticResources()
    }

    fun loadStaticResources() {
        Handler().postDelayed({
            viewState.onLoadingComplete()
        }, 2000)
    }
}