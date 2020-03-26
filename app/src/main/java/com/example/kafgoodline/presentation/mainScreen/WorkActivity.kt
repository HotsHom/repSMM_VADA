package com.example.kafgoodline.presentation.mainScreen

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.kafgoodline.R
import com.example.kafgoodline.presentation.mainScreen.Profile.ProfileFragment
import com.example.kafgoodline.App
import com.example.kafgoodline.presentation.loginScreen.MainActivity

class WorkActivity : AppCompatActivity() {

    companion object {

        fun show() {
            App.appContext.let {
                it.startActivity(Intent(it, WorkActivity::class.java).apply {
                    flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                })
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_work)

        supportFragmentManager.beginTransaction()
            .add(
                R.id.container,
                ProfileFragment()
            )
            .addToBackStack(null)
            .commit()
    }
}
