package com.example.kafgoodline.presentation.loginScreen

import android.content.Intent
import android.os.Bundle
import android.view.View
import com.example.kafgoodline.App
import com.example.kafgoodline.R
import com.example.kafgoodline.base.ABaseActivity
import com.example.kafgoodline.presentation.loginScreen.loading.LoadFragment
import com.example.kafgoodline.presentation.loginScreen.login.LoginFragment
import com.example.kafgoodline.presentation.loginScreen.registration.RegisterFragment
import com.example.kafgoodline.presentation.mainScreen.WorkActivity
import com.vk.api.sdk.ui.VKWebViewAuthActivity
import com.vk.api.sdk.utils.VKUtils


class MainActivity : ICredentionalsRouter, ABaseActivity() {

    companion object {

        fun show() {
            App.appContext.let {
                it.startActivity(Intent(it, MainActivity::class.java).apply {
                    flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                })
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState != null)
            return
        val fingerprints: Array<String?>? =
            VKUtils.getCertificateFingerprint(this, this.packageName)

        showLoading()
    }

    override fun showRegistration(view: View) {
        replace(RegisterFragment(), "Registration")
    }

    override fun showLogin(view: View?) {
        replace(LoginFragment())
    }

    override fun showLoading() {
        replace(LoadFragment())
    }

    override fun goToMainWorkScreen() {
        val intent = Intent(this, WorkActivity::class.java)
        startActivity(intent)
    }
}
