package com.example.kafgoodline.presentation.loginScreen.loading

import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.example.kafgoodline.R
import com.example.kafgoodline.base.ABaseFragment
import com.example.kafgoodline.domain.di.component.DaggerAppComponent
import com.example.kafgoodline.presentation.loginScreen.ICredentionalsRouter
import javax.inject.Inject


class LoadFragment : ABaseFragment(), ILoadView {

    override fun getViewId() = R.layout.fragment_load

    @Inject
    @InjectPresenter
    lateinit var presenter: LoadPresenter

    @ProvidePresenter
    fun providePresenter() = presenter

    override fun inject() {
        DaggerAppComponent.create().inject(this)
    }

    override fun onLoadingComplete() {
        activity.let {
            if (it is ICredentionalsRouter)
                it.showLogin(this.view)
        }
    }

    override fun onLoadingCompleteLogining() {
        activity.let {
            if (it is ICredentionalsRouter)
                it.goToMainWorkScreen()
        }
    }
}
