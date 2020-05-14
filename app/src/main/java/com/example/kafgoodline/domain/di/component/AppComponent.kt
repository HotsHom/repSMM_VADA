package com.example.kafgoodline.domain.di.component

import com.example.kafgoodline.domain.di.module.NetModule
import com.example.kafgoodline.presentation.loginScreen.MainActivity
import com.example.kafgoodline.presentation.loginScreen.loading.LoadFragment
import com.example.kafgoodline.presentation.loginScreen.login.LoginFragment
import com.example.kafgoodline.presentation.loginScreen.registration.RegisterFragment
import com.example.kafgoodline.presentation.mainScreen.profile.ProfileFragment
import com.example.kafgoodline.presentation.mainScreen.WorkActivity
import com.example.kafgoodline.presentation.mainScreen.contentPlan.ContentPlanFragment
import com.example.kafgoodline.presentation.mainScreen.createPost.PostFragment
import com.example.kafgoodline.presentation.mainScreen.homePage.HomeFragment
import com.example.kafgoodline.presentation.mainScreen.startApp.StartFragment
import com.example.kafgoodline.presentation.mainScreen.statistics.StatisticsFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [
//    AppModule::class,
    NetModule::class
])
interface AppComponent {

    fun inject(target: RegisterFragment)
    fun inject(target: LoginFragment)
    fun inject(target: LoadFragment)
    fun inject(target: MainActivity)
    fun inject(target: StartFragment)
    fun inject(target: WorkActivity)
    fun inject(target: ProfileFragment)
    fun inject(target: HomeFragment)
    fun inject(target: PostFragment)
    fun inject(target: ContentPlanFragment)
    fun inject(target: StatisticsFragment)
}