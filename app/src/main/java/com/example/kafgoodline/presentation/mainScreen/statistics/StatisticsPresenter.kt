package com.example.kafgoodline.presentation.mainScreen.statistics

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.example.kafgoodline.domain.repositories.UseTokenRepository
import com.example.kafgoodline.presentation.mainScreen.profile.IProfileView
import javax.inject.Inject

@InjectViewState
class StatisticsPresenter : MvpPresenter<IStatisticsView> {

    @Inject
    constructor()

    @Inject
    lateinit var userRepositoryWithToken: UseTokenRepository
}