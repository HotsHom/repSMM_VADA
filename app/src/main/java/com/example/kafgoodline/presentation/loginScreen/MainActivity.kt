package com.example.kafgoodline.presentation.loginScreen

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.arellomobile.mvp.presenter.InjectPresenter
import com.example.kafgoodline.R
import com.example.kafgoodline.presentation.loginScreen.login.LoginFragment
import com.example.kafgoodline.presentation.loginScreen.login.LoginPresenter
import com.example.kafgoodline.presentation.loginScreen.registration.RegisterFragment

class MainActivity : AppCompatActivity(), IMainActivity {
    @InjectPresenter
    lateinit var presenter: MainActivityPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState != null)
            return

        supportFragmentManager.beginTransaction()
            .replace(R.id.container, LoginFragment())
            .commit()
    }

    override fun showRegisterScreen() {
        supportFragmentManager.beginTransaction()
            .add(R.id.container, RegisterFragment())
            .commit()
    }

    override fun showLoginScreen() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    fun registerAction(view: View) {
        presenter.showRegister()
    }
}
