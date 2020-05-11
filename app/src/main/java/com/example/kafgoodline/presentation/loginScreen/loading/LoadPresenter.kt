package com.example.kafgoodline.presentation.loginScreen.loading

import android.os.Handler
import android.widget.Toast
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.example.kafgoodline.base.SubRX
import com.example.kafgoodline.domain.repositories.UseTokenRepository
import com.example.kafgoodline.presentation.mainScreen.profile.ProfileFragment
import javax.inject.Inject

@InjectViewState
class LoadPresenter : MvpPresenter<ILoadView> {

    @Inject
    constructor()

    @Inject
    lateinit var userRepositoryWithToken: UseTokenRepository

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()

        loadStaticResources()
    }

    fun loadStaticResources() {
        if (userRepositoryWithToken.getUser()?.username.isNullOrEmpty()) {
            Handler().postDelayed({
                viewState.onLoadingComplete()
            }, 500)
        } else {
            Handler().postDelayed({
                viewState.onLoadingCompleteLogining()
            }, 500)
        }

    }
}