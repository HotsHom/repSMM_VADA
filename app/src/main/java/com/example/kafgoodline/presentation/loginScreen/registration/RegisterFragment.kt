package com.example.kafgoodline.presentation.loginScreen.registration


import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.example.kafgoodline.R
import com.example.kafgoodline.base.ABaseFragment
import com.example.kafgoodline.domain.di.component.DaggerAppComponent
import com.example.kafgoodline.presentation.loginScreen.ICredentionalsRouter
import kotlinx.android.synthetic.main.fragment_register.*
import javax.inject.Inject


class RegisterFragment : ABaseFragment(), IRegistrationView {
    override fun getViewId() = R.layout.fragment_register

    override fun inject() {
        DaggerAppComponent.create().inject(this)
    }

    @Inject
    @InjectPresenter
    lateinit var presenter: RegistratonPresenter

    @ProvidePresenter
    fun providePresenter() = presenter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

            btnDoRegister.setOnClickListener {
                val login : String = "${nickname.text}"
                val password : String = "${pass1.text}"

                if (login.isEmpty() || password.isEmpty()){
                    toast(R.string.error_login_password)
                    return@setOnClickListener
                }

                presenter.registration(login, password)

            }
        }

    override fun showError(message: String?) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show()
    }

    override fun showLoginSc() {
        activity.let {
            if (it is ICredentionalsRouter)
                it.showLogin(this.view)
        }
    }
}
