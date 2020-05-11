package com.example.kafgoodline.presentation.mainScreen.homePage

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.arellomobile.mvp.MvpView
import com.example.kafgoodline.domain.repositories.AuthRepository
import javax.inject.Inject

@InjectViewState
class HomePresenter : MvpPresenter<IHomeView>{

    @Inject
    constructor()

    @Inject
    lateinit var userRepository: AuthRepository


}