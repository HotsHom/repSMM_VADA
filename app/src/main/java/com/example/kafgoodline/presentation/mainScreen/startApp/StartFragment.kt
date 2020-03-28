package com.example.kafgoodline.presentation.mainScreen.startApp


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.example.kafgoodline.R
import com.example.kafgoodline.base.ABaseFragment
import com.example.kafgoodline.domain.di.component.DaggerAppComponent
import com.example.kafgoodline.presentation.loginScreen.ICredentionalsRouter
import com.example.kafgoodline.presentation.mainScreen.ICredentionalsRouterWorkActivity
import kotlinx.android.synthetic.main.fragment_start.*
import javax.inject.Inject

/**
 * A simple [Fragment] subclass.
 */


class StartFragment : ABaseFragment(), IStartView {

    @Inject
    @InjectPresenter
    lateinit var presenter: StartPresenter

    @ProvidePresenter
    fun providePresenter() = presenter

    override fun inject() {
        DaggerAppComponent.create().inject(this)
    }

    override fun getViewId() = R.layout.fragment_start

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_start, container, false)
    }

    override fun doFinishRegsidtarion() {
        if (chtvSuccess.isChecked){
            if (etFirstName.text.isNotEmpty() || etSecondName.text.isNotEmpty()){
                presenter.putFSName("${etFirstName.text}","${etSecondName.text}")
            }
        }
    }

    override fun showError(message: String?) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show()
    }

    override fun showHome() {
        activity.let {
            if (it is ICredentionalsRouterWorkActivity) {
                it.showHome()
                it.showMenu()
            }
        }
    }
}
