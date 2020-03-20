package com.example.kafgoodline.presentation.loginScreen.login


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.arellomobile.mvp.MvpAppCompatFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import com.example.kafgoodline.R
import com.example.kafgoodline.presentation.loginScreen.MainActivity
import kotlinx.android.synthetic.main.fragment_login.*

class LoginFragment :  MvpAppCompatFragment(), ILoginView{
    @InjectPresenter
    lateinit var presenter: LoginPresenter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btlogin.setOnClickListener {
            presenter.login("${etLogin.text}", "${etPass.text}")
        }
    }

    override fun showError(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show()
    }

    override fun showWork() {
        val r : MainActivity = getActivity() as MainActivity
        r.doWork()
    }

}
