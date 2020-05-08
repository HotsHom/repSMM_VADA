package com.example.kafgoodline.presentation.mainScreen.profile.web

import android.annotation.TargetApi
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import com.example.kafgoodline.R
import com.example.kafgoodline.base.SubRX
import com.example.kafgoodline.domain.repositories.UseTokenRepository
import kotlinx.android.synthetic.main.fragment_web.*
import javax.inject.Inject

/**
 * A simple [Fragment] subclass.
 */
class WebFragment : Fragment() {

    @Inject
    lateinit var userRepositoryWithToken: UseTokenRepository

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        var webView : WebView = webview
        webView.webViewClient = MyWebViewClient()
        webView.settings.javaScriptEnabled = true
        // указываем страницу загрузки
        webView.loadUrl("https://oauth.vk.com/authorize?client_id=7273900&scope=photos,audio,video,docs,notes,pages,status,offers,questions,wall,groups,email,notifications,stats,ads,offline,docs,pages,stats,notifications&response_type=token")

        return inflater.inflate(R.layout.fragment_web, container, false)
    }

    public class MyWebViewClient : WebViewClient() {
        @Inject
        lateinit var userRepositoryWithToken: UseTokenRepository

        @TargetApi(Build.VERSION_CODES.N)
        override fun shouldOverrideUrlLoading(
            view: WebView,
            request: WebResourceRequest
        ): Boolean {
            if (request.url.toString().contains("access_token")) {
                val URL = request.url.toString()
                val id = request.url.toString().length
                val words1 = URL.split("/").toTypedArray()
                val words2 = words1[3].split("=").toTypedArray()
                val words3 = words2[1].split("&").toTypedArray()
                API(words3[0])
            }
            view.loadUrl(request.url.toString())
            return true
        }

        private fun API(words3: String) {
            userRepositoryWithToken.putVkTokenFinction(SubRX { _, e ->

                if (e != null) {
                    e.printStackTrace()
                    //Переход к фрагментам рабочего экрана
                    return@SubRX
                }
            }, words3)
        }
    }



}
