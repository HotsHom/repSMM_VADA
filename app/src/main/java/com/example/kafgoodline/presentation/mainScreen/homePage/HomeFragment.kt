package com.example.kafgoodline.presentation.mainScreen.homePage

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Handler
import android.os.Message
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
import kotlinx.android.synthetic.main.fragment_home.*
import org.json.JSONArray
import org.json.JSONObject
import java.time.LocalDate
import javax.inject.Inject
import kotlin.concurrent.thread


/**
 * A simple [Fragment] subclass.
 */
class HomeFragment : ABaseFragment(), IHomeView {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    @Inject
    @InjectPresenter
    lateinit var presenter: HomePresenter

    @ProvidePresenter
    fun providePresenter() = presenter

    override fun inject() {
        DaggerAppComponent.create().inject(this)
    }

    override fun getViewId() = R.layout.fragment_home

    var views: String = "0"
    var visitors: String = "0"
    var subscribed: String = "0"
    var mHandler: Handler? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        @SuppressLint("HandlerLeak")
        mHandler = object : Handler() {
            override fun handleMessage(msg: Message) {
                view_counter?.text = views
                visitorsCount?.text = visitors
                subscribeCount?.text = subscribed
            }
        }

        thread(isDaemon = true) {
            while (true) {
                val timeTo = LocalDate.now().toString()
                val timeFrom = LocalDate.now().minusDays(1).toString()
                var par = VKParameters()
                par.put(VKApiConst.GROUP_ID, presenter.userRepository.getUser()?.vkIdGroup)
                par.put("app_id", "7454841")
                par.put("date_from", timeFrom)
                par.put("date_to", timeTo)
                par.put("interval", "day")
                par.put("intervals_count", 1)
                par.put("access_token", presenter.userRepository.getUser()?.vkToken)
                var request: VKRequest = VKRequest("stats.get", par)

                request.executeWithListener(object : VKRequest.VKRequestListener() {
                    override fun onComplete(response: VKResponse) { //Do complete stuff
                        val jsA: JSONArray = response.json.getJSONArray("response")
                        val jsO: JSONObject = jsA.getJSONObject(0)
                        views = if (jsO.getString("views") != "null"){
                            jsO.getString("views")
                        }else{
                            "0"
                        }
                        visitors = if (jsO.getString("visitors") != "null"){
                            jsO.getString("visitors")
                        }else{
                            "0"
                        }
                        subscribed = if (jsO.getString("subscribed") != "null"){
                            jsO.getString("subscribed")
                        }else{
                            "0"
                        }
                        (mHandler as Handler).sendEmptyMessage(1)
                    }

                    override fun onError(error: VKError) { //Do error stuff
                        print(error.errorMessage)
                    }

                    override fun attemptFailed(
                        request: VKRequest,
                        attemptNumber: Int,
                        totalAttempts: Int
                    ) { //I don't really believe in progress
                    }
                })
                Thread.sleep(1000)
            }
        }
    }
}
