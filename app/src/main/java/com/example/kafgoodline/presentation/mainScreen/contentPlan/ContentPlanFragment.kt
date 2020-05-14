package com.example.kafgoodline.presentation.mainScreen.contentPlan

import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TableRow
import android.widget.TextView
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.example.kafgoodline.R
import com.example.kafgoodline.base.ABaseFragment
import com.example.kafgoodline.base.SubRX
import com.example.kafgoodline.domain.di.component.DaggerAppComponent
import com.example.kafgoodline.domain.repositories.models.rest.Post
import kotlinx.android.synthetic.main.fragment_content_plan.*
import javax.inject.Inject


class ContentPlanFragment : ABaseFragment(), IContentPlanView {

    @Inject
    @InjectPresenter
    lateinit var presenter: ContentPlanPresenter

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
        return inflater.inflate(R.layout.fragment_content_plan, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        refreshTable()
        btnRefresh.setOnClickListener {
            refreshTable()
        }
    }

    private fun refreshTable() {
        presenter.userTokenRepository.getUserPosts(SubRX { _, e ->
            if (e != null) {
                e.printStackTrace()
                return@SubRX
            }else{
                val listPosts : List<Post>? = presenter.userTokenRepository.getPosts()

                listPosts?.let {
                    tvInformText.visibility = View.GONE
                    tlTable.removeAllViews()
                }

                if (listPosts.isNullOrEmpty()){
                    tvInformText.visibility = View.VISIBLE
                }

                val tableRowHeader = TableRow(activity)
                tableRowHeader.layoutParams = TableRow.LayoutParams(
                    TableRow.LayoutParams.MATCH_PARENT,
                    TableRow.LayoutParams.WRAP_CONTENT
                )
                tableRowHeader.gravity = Gravity.CENTER

                val header = TextView(activity)
                header.text = "ПОСТЫ"
                header.setTypeface(null, Typeface.BOLD)
                header.setBackgroundColor(Color.GRAY)
                header.gravity = Gravity.CENTER

                tableRowHeader.addView(header)
                tlTable.addView(tableRowHeader)

                listPosts?.forEach{
                    val tableRowTitle = TableRow(activity)
                    tableRowTitle.layoutParams = TableRow.LayoutParams(
                        TableRow.LayoutParams.MATCH_PARENT,
                        TableRow.LayoutParams.WRAP_CONTENT
                    )
                    tableRowTitle.gravity = Gravity.CENTER

                    val title = TextView(activity)
                    title.text = "Заголовок"
                    title.setTypeface(null, Typeface.BOLD)
                    title.gravity = Gravity.CENTER

                    val tableRowTitleText = TableRow(activity)
                    tableRowTitleText.layoutParams = TableRow.LayoutParams(
                        TableRow.LayoutParams.MATCH_PARENT,
                        TableRow.LayoutParams.WRAP_CONTENT
                    )
                    tableRowTitleText.gravity = Gravity.CENTER

                    val titleText = TextView(activity)
                    titleText.text = it.title
                    titleText.gravity = Gravity.CENTER

                    val tableRowMain = TableRow(activity)
                    tableRowMain.layoutParams = TableRow.LayoutParams(
                        TableRow.LayoutParams.MATCH_PARENT,
                        TableRow.LayoutParams.WRAP_CONTENT
                    )
                    tableRowMain.gravity = Gravity.CENTER

                    val social = TextView(activity)
                    var time = it.date_post.replace('T', ' ').replace('Z',' ')
                    it.vk?.let { it1 ->
                        social.text = "Соц. сеть: Vk\n Дата: " + time
                    }
                    social.setTypeface(null, Typeface.BOLD)
                    social.gravity = Gravity.CENTER

                    val tableRowTextTitle = TableRow(activity)
                    tableRowTextTitle.layoutParams = TableRow.LayoutParams(
                        TableRow.LayoutParams.MATCH_PARENT,
                        TableRow.LayoutParams.WRAP_CONTENT
                    )
                    tableRowTextTitle.gravity = Gravity.CENTER

                    val textTitle = TextView(activity)
                    var textTit = "Текст"
                    textTitle.text = textTit
                    textTitle.setTypeface(null, Typeface.BOLD)
                    textTitle.gravity = Gravity.CENTER

                    val tableRowText = TableRow(activity)
                    tableRowText.layoutParams = TableRow.LayoutParams(
                        TableRow.LayoutParams.MATCH_PARENT,
                        TableRow.LayoutParams.WRAP_CONTENT
                    )
                    tableRowText.gravity = Gravity.CENTER

                    val textPost = TextView(activity)
                    var text = ""
                    it.text?.let{
                        if (it.length < 20){
                            text += it
                        }else{
                            text += it.substring(0..20) + "..."
                        }
                    }
                    textPost.text = text
                    textPost.gravity = Gravity.CENTER

                    val tableRowLine = TableRow(activity)
                    tableRowLine.layoutParams = TableRow.LayoutParams(
                        TableRow.LayoutParams.MATCH_PARENT,
                        TableRow.LayoutParams.WRAP_CONTENT
                    )
                    tableRowLine.gravity = Gravity.CENTER

                    val lineOne = TextView(activity)
                    val displaymetrics = resources.displayMetrics
                    val lenght = displaymetrics.xdpi
                    var lineString = ""
                    for (i in 1..(lenght / 2).toInt()){
                        lineString += "-"
                    }
                    lineOne.text = lineString
                    lineOne.setBackgroundColor(Color.GRAY)
                    lineOne.gravity = Gravity.CENTER

                    tableRowTitle.addView(title)
                    tableRowTitleText.addView(titleText)
                    tableRowMain.addView(social)
                    tableRowTextTitle.addView(textTitle)
                    tableRowText.addView(textPost)
                    tableRowLine.addView(lineOne)
                    tlTable.addView(tableRowTitle)
                    tlTable.addView(tableRowTitleText)
                    tlTable.addView(tableRowMain)
                    tlTable.addView(tableRowTextTitle)
                    tlTable.addView(tableRowText)
                    tlTable.addView(tableRowLine)
                }
            }

        })


    }

    override fun lock() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun unlock() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
