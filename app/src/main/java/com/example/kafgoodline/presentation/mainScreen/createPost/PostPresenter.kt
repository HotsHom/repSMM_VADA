package com.example.kafgoodline.presentation.mainScreen.createPost

import com.arellomobile.mvp.MvpPresenter
import com.example.kafgoodline.domain.repositories.AuthRepository
import javax.inject.Inject

class PostPresenter : MvpPresenter<IPostView> {

    @Inject
    constructor()

    @Inject
    lateinit var userRepository: AuthRepository

}