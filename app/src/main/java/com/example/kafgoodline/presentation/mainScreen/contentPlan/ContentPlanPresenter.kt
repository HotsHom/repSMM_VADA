package com.example.kafgoodline.presentation.mainScreen.contentPlan

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.example.kafgoodline.domain.repositories.AuthRepository
import com.example.kafgoodline.domain.repositories.UseTokenRepository
import com.example.kafgoodline.presentation.mainScreen.createPost.IPostView
import javax.inject.Inject

@InjectViewState
class ContentPlanPresenter : MvpPresenter<IContentPlanView> {

    @Inject
    constructor()

    @Inject
    lateinit var userRepository: AuthRepository
    @Inject
    lateinit var userTokenRepository: UseTokenRepository
}