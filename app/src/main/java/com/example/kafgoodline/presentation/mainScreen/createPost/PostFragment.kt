package com.example.kafgoodline.presentation.mainScreen.createPost

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
import com.vk.sdk.api.*
import com.vk.sdk.api.VKRequest.VKRequestListener
import com.vk.sdk.api.model.VKWallPostResult
import kotlinx.android.synthetic.main.fragment_post.*
import javax.inject.Inject


/**
 * A simple [Fragment] subclass.
 */
class PostFragment : ABaseFragment(), IPostView {

    @Inject
    @InjectPresenter
    lateinit var presenter: PostPresenter

    @ProvidePresenter
    fun providePresenter() = presenter

    override fun inject() {
        DaggerAppComponent.create().inject(this)
    }

    override fun getViewId() = R.layout.fragment_post

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_post, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btnDoPost.setOnClickListener {
            makePost()
        }
    }

    private fun makePost() {
        val parameters = VKParameters()
        parameters.put(VKApiConst.OWNER_ID, "-${presenter.userRepository.getUser()?.vkIdGroup}")
        parameters.put(VKApiConst.MESSAGE, etTextPost.text.toString())
        parameters.put(VKApiConst.FROM_GROUP, 1)
        val post = VKApi.wall().post(parameters)
        post.setModelClass(VKWallPostResult::class.java)
        post.executeWithListener(object : VKRequestListener() {
            override fun onComplete(response: VKResponse) { // post was added
            }

            override fun onError(error: VKError) { // error
                print(error.errorMessage)
            }
        })
    }

    override fun lock() {

    }

    override fun unlock() {
    }
}
