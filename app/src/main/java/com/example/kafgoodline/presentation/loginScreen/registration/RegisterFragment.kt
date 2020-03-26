package com.example.kafgoodline.presentation.loginScreen.registration


import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.example.kafgoodline.base.ABaseFragment
import com.example.kafgoodline.R
import com.example.kafgoodline.domain.di.DaggerAppComponent
import com.example.kafgoodline.presentation.loginScreen.MainActivity
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

        btnRegister.setOnClickListener {
            presenter.registration("${nickname.text}", "${pass1.text}", "${pass2.text}")
        }
    }

    override fun showError(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show()
    }

    override fun showLoginSc() {
        val r : MainActivity = getActivity() as MainActivity
        r.showLoginScreen()
    }
}
