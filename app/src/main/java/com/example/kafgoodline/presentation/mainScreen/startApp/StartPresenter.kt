package com.example.kafgoodline.presentation.mainScreen.startApp

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.example.kafgoodline.R
import com.example.kafgoodline.base.SubRX
import com.example.kafgoodline.domain.repositories.UserRepository
import com.example.kafgoodline.domain.repositories.UserRepositoryWithToken
import javax.inject.Inject

@InjectViewState
class StartPresenter : MvpPresenter<IStartView> {

    @Inject
    constructor()

    @Inject
    lateinit var userRepositoryWithToken: UserRepositoryWithToken

    fun putFSName(firstname : String, secondname: String){
        userRepositoryWithToken.putFSName(SubRX { _, e ->

            if (e != null) {
                e.printStackTrace()
                viewState.showError(e.message)
                return@SubRX
            }

            viewState.showHome()
        }, firstname, secondname)
    }

}