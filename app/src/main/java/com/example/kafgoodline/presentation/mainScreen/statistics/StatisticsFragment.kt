package com.example.kafgoodline.presentation.mainScreen.statistics

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
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.vk.sdk.api.*
import kotlinx.android.synthetic.main.fragment_statistics.*
import org.json.JSONArray
import org.json.JSONObject
import java.time.LocalDate
import javax.inject.Inject
import kotlin.concurrent.thread

/**
 * A simple [Fragment] subclass.
 */
class StatisticsFragment : ABaseFragment(), IStatisticsView {

    @Inject
    @InjectPresenter
    lateinit var presenter: StatisticsPresenter

    @ProvidePresenter
    fun providePresenter() = presenter

    override fun inject() {
        DaggerAppComponent.create().inject(this)
    }

    override fun getViewId() = R.layout.fragment_statistics

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_statistics, container, false)
    }

    var mHandler: Handler? = null
    var listView : Array<String> = Array(10) {""}
    var listVisitors : Array<String> = Array(10) {""}

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewViews()
        @SuppressLint("HandlerLeak")
        mHandler = object : Handler() {
            override fun handleMessage(msg: Message) {
                listView?.let {
                    val entries: Array<Entry> = Array(listView.size) { Entry() }
                    val entriesVisitors: Array<Entry> = Array(listView.size) { Entry() }
                    var i = 1
                    listView.reverse()
                    listVisitors.reverse()
                    listView?.forEach {
                        entries[i - 1] = Entry(i.toFloat(), it.toFloat())
                        i++
                    }
                    i = 1
                    listVisitors?.forEach {
                        entriesVisitors[i - 1] = Entry(i.toFloat(), it.toFloat())
                        i++
                    }
                    val dataSet = LineDataSet(entries.toMutableList(), "Количество просмотров за ${listView.size} дней")
                    val lineData = LineData(dataSet)
                    chartViews.setData(lineData)
                    chartViews.getDescription().setEnabled(false)
                    chartViews.invalidate()
                    val dataSetVisitors = LineDataSet(entriesVisitors.toMutableList(), "Количество посещений за ${listView.size} дней")
                    val lineDataVisitors = LineData(dataSetVisitors)
                    chartVisitors.setData(lineDataVisitors)
                    chartVisitors.getDescription().setEnabled(false)
                    chartVisitors.invalidate()
                }
            }
        }
    }



    fun viewViews(){
        thread{
            val timeTo = LocalDate.now().toString()
            val timeFrom = LocalDate.now().minusMonths(1).toString()
            var par = VKParameters()
            par.put(VKApiConst.GROUP_ID, presenter.userRepositoryWithToken.getUser()?.vkIdGroup)
            par.put("app_id", "7454841")
            par.put("date_from", timeFrom)
            par.put("date_to", timeTo)
            par.put("interval", "day")
            par.put("intervals_count", 10)
            par.put("access_token", presenter.userRepositoryWithToken.getUser()?.vkToken)
            var request: VKRequest = VKRequest("stats.get", par)

            request.executeWithListener(object : VKRequest.VKRequestListener() {
                override fun onComplete(response: VKResponse) { //Do complete stuff
                    val jsA: JSONArray = response.json.getJSONArray("response")
                    var timeInterval = 10
                    if (jsA.length() < 10){
                        timeInterval = jsA.length()
                    }
                    listView  = Array(timeInterval) {""}
                    listVisitors  = Array(timeInterval) {""}
                    timeInterval--
                    for (i in 0..timeInterval){
                        val jsO: JSONObject = jsA.getJSONObject(i)
                        if (jsO.getString("views") != "null"){
                            listView[i] = jsO.getString("views")
                        }
                        if (jsO.getString("visitors") != "null"){
                            listVisitors[i] = jsO.getString("visitors")
                        }
                    }

//                    if (jsO.getString("views") != "null"){
//                        views = jsO.getString("views")
//                    }else{
//                        views = "0"
//                    }
//                    if (jsO.getString("visitors") != "null"){
//                        visitors = jsO.getString("visitors")
//                    }else{
//                        visitors = "0"
//                    }
//                    if (jsO.getString("subscribed") != "null"){
//                        subscribed = jsO.getString("subscribed")
//                    }else{
//                        subscribed = "0"
//                    }
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
        }

    }
}
