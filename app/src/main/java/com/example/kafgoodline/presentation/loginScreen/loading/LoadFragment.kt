package com.example.kafgoodline.presentation.loginScreen.loading

import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.example.kafgoodline.R
import com.example.kafgoodline.base.ABaseFragment
import com.example.kafgoodline.domain.di.DaggerAppComponent
import com.example.kafgoodline.presentation.loginScreen.IMainActivity
import javax.inject.Inject

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


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
            if (it is IMainActivity)
                it.showLoginScreen()
        }
    }
}
