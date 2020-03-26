package com.example.kafgoodline.presentation.mainScreen

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.kafgoodline.R
import com.example.kafgoodline.presentation.mainScreen.Profile.ProfileFragment

class WorkActivity : AppCompatActivity() {

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
