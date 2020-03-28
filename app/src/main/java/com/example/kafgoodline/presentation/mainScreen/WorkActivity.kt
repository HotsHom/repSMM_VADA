package com.example.kafgoodline.presentation.mainScreen

import android.content.Intent
import android.os.Bundle
import android.view.View
import com.example.kafgoodline.R
import com.example.kafgoodline.App
import com.example.kafgoodline.base.ABaseActivity
import com.example.kafgoodline.domain.di.component.DaggerAppComponent
import com.example.kafgoodline.domain.repositories.UserRepository
import com.example.kafgoodline.presentation.mainScreen.Profile.ProfileFragment
import com.example.kafgoodline.presentation.mainScreen.startApp.StartFragment
import kotlinx.android.synthetic.main.activity_work.*
import javax.inject.Inject

class WorkActivity : ABaseActivity(), ICredentionalsRouterWorkActivity {

    companion object {

        fun show() {
            App.appContext.let {
                it.startActivity(Intent(it, WorkActivity::class.java).apply {
                    flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                })
            }
        }
    }

    @Inject
    lateinit var userRepository: UserRepository

    init {
        inject()
    }

    fun inject() {
        DaggerAppComponent.create().inject(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_work)

        if(userRepository.getUserStatus() == null && userRepository.getUser() != null){
            supportFragmentManager.beginTransaction()
                .add(
                    R.id.container,
                    StartFragment()
                )
                .addToBackStack(null)
                .commit()
            bar.visibility = View.GONE
        }else{
            showHome()
        }

    }

    override fun showMenu() {
        bar.visibility = View.VISIBLE
    }

    override fun showHome() {
        supportFragmentManager.beginTransaction()
            .add(
                R.id.container,
                ProfileFragment()
                //TODO ПОМЕНЯТЬ НА ДОМАШНЮЮ СТРАНИЦУ
            )
            .addToBackStack(null)
            .commit()
    }
}
