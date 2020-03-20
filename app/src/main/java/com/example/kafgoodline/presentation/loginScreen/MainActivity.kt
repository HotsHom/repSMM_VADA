package com.example.kafgoodline.presentation.loginScreen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.arellomobile.mvp.MvpAppCompatActivity
import com.arellomobile.mvp.presenter.InjectPresenter
import com.example.kafgoodline.R
import com.example.kafgoodline.WorkActivity
import com.example.kafgoodline.presentation.loginScreen.login.LoginFragment
import com.example.kafgoodline.presentation.loginScreen.login.LoginPresenter
import com.example.kafgoodline.presentation.loginScreen.registration.RegisterFragment
import androidx.core.app.ComponentActivity
import androidx.core.app.ComponentActivity.ExtraData
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T




class MainActivity : MvpAppCompatActivity(), IMainActivity {

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
            .addToBackStack(null)
            .commit()
    }

    override fun showLoginScreen() {
        supportFragmentManager.beginTransaction()
            .add(R.id.container, LoginFragment())
            .addToBackStack(null)
            .commit()
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
