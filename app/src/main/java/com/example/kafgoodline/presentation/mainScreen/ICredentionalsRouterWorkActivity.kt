package com.example.kafgoodline.presentation.mainScreen

import android.view.View
import androidx.fragment.app.Fragment

interface ICredentionalsRouterWorkActivity {

    fun showMenu()
    fun showHome()
    fun showCreatePost(view: View)
    fun add(fragment: Fragment)

}