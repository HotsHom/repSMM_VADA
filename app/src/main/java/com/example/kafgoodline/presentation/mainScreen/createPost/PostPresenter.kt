package com.example.kafgoodline.presentation.mainScreen.createPost

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.example.kafgoodline.domain.repositories.AuthRepository
import com.example.kafgoodline.domain.repositories.UseTokenRepository
import javax.inject.Inject

@InjectViewState
class PostPresenter : MvpPresenter<IPostView> {

    @Inject
    constructor()

    @Inject
    lateinit var userRepository: AuthRepository
    @Inject
    lateinit var userTokenRepository: UseTokenRepository

    fun viewWork(){
        viewState.viewForm()
    }

}