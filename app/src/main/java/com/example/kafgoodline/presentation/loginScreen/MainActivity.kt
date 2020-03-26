package com.example.kafgoodline.presentation.loginScreen

import android.content.Intent
import android.os.Bundle
import android.view.View
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.example.kafgoodline.base.ABaseActivity
import com.example.kafgoodline.R
import com.example.kafgoodline.domain.di.DaggerAppComponent
import com.example.kafgoodline.presentation.loginScreen.loading.LoadFragment
import com.example.kafgoodline.presentation.mainScreen.WorkActivity
import com.example.kafgoodline.presentation.loginScreen.login.LoginFragment
import com.example.kafgoodline.presentation.loginScreen.registration.RegisterFragment
import javax.inject.Inject


class MainActivity : IMainActivity, ICredentionalsRouter, ABaseActivity() {

    override fun inject() {
        DaggerAppComponent.create().inject(this)
    }

    @Inject
    @InjectPresenter
    lateinit var presenter: MainActivityPresenter

    @ProvidePresenter
    fun providePresenter() = presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState != null)
            return
        showLoading()
    }

    override fun showRegistration() {
        replace(RegisterFragment(), "Registration")
    }

    override fun showLoginScreen() {
        replace(LoginFragment())
    }

    override fun showLoading() {
        replace(LoadFragment())
    }

    override fun goToMainWorkScreen() {
        val intent = Intent(this, WorkActivity::class.java)
        startActivity(intent)
    }

    fun registerAction(view: View) {
        presenter.showRegister()
    }

    fun doRegistration() {
        presenter.showLogin()
    }

    fun doWork(){
        presenter.goToWork()
    }



}
