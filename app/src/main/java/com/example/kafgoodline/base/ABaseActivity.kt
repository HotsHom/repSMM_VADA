package com.example.kafgoodline.base

import android.content.pm.ActivityInfo
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.kafgoodline.R

abstract class ABaseActivity : AppCompatActivity() {

    fun replace(fragment: Fragment, backStack: String? = null, tag: String? = null) {
        supportFragmentManager.beginTransaction().replace(R.id.container, fragment, tag).apply {
            backStack?.let {
                addToBackStack(it)
            }
        }.commit()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED);
    }
}