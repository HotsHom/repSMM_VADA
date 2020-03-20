package com.example.kafgoodline.presentation.loginScreen.registration


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.arellomobile.mvp.MvpAppCompatFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.example.kafgoodline.R
import com.example.kafgoodline.domain.di.DaggerAppComponent
import com.example.kafgoodline.presentation.loginScreen.MainActivity
import com.example.kafgoodline.presentation.loginScreen.login.LoginPresenter
import kotlinx.android.synthetic.main.fragment_login.*
import kotlinx.android.synthetic.main.fragment_login.btnRegister
import kotlinx.android.synthetic.main.fragment_register.*
import javax.inject.Inject


class RegisterFragment : MvpAppCompatFragment, IRegistrationView {

    @Inject
    @InjectPresenter
    lateinit var presenter: RegistratonPresenter

    @ProvidePresenter
    fun providePresenter() = presenter

    constructor(){
        DaggerAppComponent.create().inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_register, container, false)
    }

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
        r.doRegistration()
    }
}
