package com.example.kafgoodline.presentation.loginScreen.login


import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.example.kafgoodline.R
import com.example.kafgoodline.base.ABaseFragment
import com.example.kafgoodline.domain.di.component.DaggerAppComponent
import com.example.kafgoodline.presentation.loginScreen.ICredentionalsRouter
import kotlinx.android.synthetic.main.fragment_login.*
import javax.inject.Inject

class LoginFragment : ABaseFragment(), ILoginView {

    @Inject
    @InjectPresenter
    lateinit var presenter: LoginPresenter

    @ProvidePresenter
    fun providePresenter() = presenter

    override fun inject() {
        DaggerAppComponent.create().inject(this)
    }

    override fun getViewId() = R.layout.fragment_login

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btlogin.setOnClickListener {
            lock()
            val login : String = "${etLogin.text}"
            val password : String = "${etPass.text}"

            if (login.isEmpty() || password.isEmpty()){
                toast(R.string.error_login_password)
                return@setOnClickListener
            }

            presenter.login(login, password)
        }
    }

    override fun showError(message: String?) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show()
    }

    override fun showWork() {
        activity.let {
            if (it is ICredentionalsRouter)
                it.goToMainWorkScreen()
        }
    }

    override fun lock() {
        visibility(flBtnContainer)
    }

    override fun unlock() {
        visibility(flBtnContainer, false)
    }
}
