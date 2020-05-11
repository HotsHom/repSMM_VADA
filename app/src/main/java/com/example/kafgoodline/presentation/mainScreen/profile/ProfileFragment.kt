package com.example.kafgoodline.presentation.mainScreen.profile


import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.example.kafgoodline.R
import com.example.kafgoodline.base.ABaseFragment
import com.example.kafgoodline.domain.di.component.DaggerAppComponent
import com.example.kafgoodline.presentation.loginScreen.ICredentionalsRouter
import com.example.kafgoodline.presentation.mainScreen.ICredentionalsRouterWorkActivity
import com.example.kafgoodline.presentation.mainScreen.profile.web.VkLoginActivity
import com.vk.sdk.VKScope
import com.vk.sdk.VKSdk
import kotlinx.android.synthetic.main.fragment_profile.*
import javax.inject.Inject

/**
 * A simple [Fragment] subclass.
 */
class ProfileFragment : ABaseFragment(), IProfileView {

    @Inject
    @InjectPresenter
    lateinit var presenter: ProfilePresenter

    @ProvidePresenter
    fun providePresenter() = presenter

    var flag: Boolean = true

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    /**
     * Called when a fragment is first attached to its activity.
     * [.onCreate] will be called after this.
     *
     */

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setInformationProfile()
        btnViewVkSettings.setOnClickListener {
            presenter.viewVkSett()
        }
        vk_button.setOnClickListener {
            linkVkTakeToken()
        }
        ibSaveVkIdGroup.setOnClickListener {
            presenter.saveVkIdGroup(etVkIdGroup.text.toString())
            toast("ID паблика/группы добавлено")
        }
        btnLogout.setOnClickListener {
            logoutAccount()
        }
    }

    private fun logoutAccount() {
        presenter.userRepositoryWithToken.logout()
        activity.let {
            if (it is ICredentionalsRouterWorkActivity)
                it.goToLoginScreen()
        }
    }

    private fun linkVkTakeToken() {
        var wb: Activity = VkLoginActivity()
        activity?.let {
            VKSdk.login(
                it,
                    VKScope.EMAIL,
                    VKScope.GROUPS,
                    VKScope.NOTIFICATIONS,
                    VKScope.ADS,
                    VKScope.AUDIO,
                    VKScope.NOTIFY,
                    VKScope.OFFLINE,
                    VKScope.PAGES,
                    VKScope.STATS,
                    VKScope.STATUS,
                    VKScope.WALL
            )
        }
    }

    override fun inject() {
        DaggerAppComponent.create().inject(this)
    }

    override fun getViewId() = R.layout.fragment_profile

    override fun setInformationProfile() {
        first_name.text = presenter.userRepositoryWithToken.getUser()?.firstname
        last_name.text = presenter.userRepositoryWithToken.getUser()?.secondname
        username_profile.text = presenter.userRepositoryWithToken.getUser()?.username
        if (!presenter.userRepositoryWithToken.getUser()?.vkToken.isNullOrEmpty()) {
            vk_button.isEnabled = false
            vk_button.setBackgroundResource(R.drawable.butt_style_black_low_radius)
            vk_button.isClickable = false
            linked.text = "1 соц. сеть подключена"
        }
        if (!presenter.userRepositoryWithToken.getUser()?.vkIdGroup.isNullOrEmpty()) {
            etVkIdGroup.setText(presenter.userRepositoryWithToken.getUser()?.vkIdGroup)
        }
    }

    override fun viewSetting() {
        visibility(llVkSetiings, flag)
        flag = !flag
    }
}
