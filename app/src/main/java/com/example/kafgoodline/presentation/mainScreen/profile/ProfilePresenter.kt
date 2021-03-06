package com.example.kafgoodline.presentation.mainScreen.profile

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.example.kafgoodline.domain.repositories.UseTokenRepository
import javax.inject.Inject

@InjectViewState
class ProfilePresenter : MvpPresenter<IProfileView> {
    @Inject
    constructor()

    @Inject
    lateinit var userRepositoryWithToken: UseTokenRepository

    fun viewVkSett(){
        viewState.viewSetting()
    }

    fun saveVkIdGroup(id : String){
        userRepositoryWithToken.saveIdVkGroup(id)
    }

}