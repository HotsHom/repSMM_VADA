package com.example.kafgoodline.presentation.mainScreen

import android.app.Activity
import android.app.Application
import android.content.Intent
import android.content.pm.ActivityInfo
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.Button
import androidx.annotation.NonNull
import androidx.fragment.app.Fragment
import com.example.kafgoodline.R
import com.example.kafgoodline.App
import com.example.kafgoodline.base.ABaseActivity
import com.example.kafgoodline.domain.di.component.DaggerAppComponent
import com.example.kafgoodline.domain.repositories.AuthRepository
import com.example.kafgoodline.presentation.mainScreen.createPost.PostFragment
import com.example.kafgoodline.presentation.mainScreen.homePage.HomeFragment
import com.example.kafgoodline.presentation.mainScreen.profile.ProfileFragment
import com.example.kafgoodline.presentation.mainScreen.startApp.StartFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_work.*
import kotlinx.android.synthetic.main.fragment_home.*
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
    lateinit var userRepository: AuthRepository

    init {
        inject()
    }

    fun inject() {
        DaggerAppComponent.create().inject(this)
    }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_work)
        val bt: BottomNavigationView = findViewById(R.id.bar)

        if(userRepository.getUserStatus() != null && userRepository.getUserStatus() == true){
            supportFragmentManager.beginTransaction()
                .replace(
                    R.id.container,
                    StartFragment()
                )
                .commit()
            bar.visibility = View.GONE
        }else{
            showHome()
            bt.selectedItemId = R.id.home

            bt.setOnNavigationItemSelectedListener{ item ->
                when (item.itemId) {
                    R.id.statictics -> {
                        add(HomeFragment()) //TODO!
                    }
                    R.id.contentPlan -> {
                        add(HomeFragment()) //TODO!
                    }
                    R.id.home -> {
                        add(HomeFragment())
                    }
                    R.id.training -> {
                        add(HomeFragment()) //TODO!
                    }
                    R.id.progile -> {
                        add(ProfileFragment())
                    }
                }
                return@setOnNavigationItemSelectedListener true
            }
        }
    }

    override fun showMenu() {
        bar.visibility = View.VISIBLE
    }

    override fun showHome() {
        supportFragmentManager.beginTransaction()
            .replace(
                R.id.container,
                HomeFragment()
                //TODO ПОМЕНЯТЬ НА ДОМАШНЮЮ СТРАНИЦУ И прописать меню
            )
            .commit()
    }

    override fun showCreatePost(view: View) {
        val bt: BottomNavigationView = findViewById(R.id.bar)
        bt.selectedItemId = R.id.contentPlan
        add(PostFragment())
    }

    override fun add(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .add(
                R.id.container,
                fragment
            )
            .addToBackStack(null)
            .commit()
    }
}
