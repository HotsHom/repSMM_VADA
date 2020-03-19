package com.example.kafgoodline.presentation.loginScreen

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.kafgoodline.R
import com.example.kafgoodline.presentation.loginScreen.login.LoginFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState != null)
            return
                // Не будем пересоздавать фрагмент, пусть берется старый из стека

        supportFragmentManager.beginTransaction()
            .replace(R.id.container, LoginFragment())
            .commit()
    }
}
