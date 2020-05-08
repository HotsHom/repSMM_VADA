package com.example.kafgoodline.presentation.mainScreen.profile.web

import android.annotation.TargetApi
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.content.SharedPreferences
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import com.example.kafgoodline.R
import kotlinx.android.synthetic.main.fragment_web.*
import org.json.JSONException
import org.json.JSONObject
import java.util.*

/**
 * A simple [Fragment] subclass.
 */
class WebFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        var webView : WebView = webview
        webView.webViewClient = MyWebViewClient()

        return inflater.inflate(R.layout.fragment_web, container, false)
    }

    private class MyWebViewClient : WebViewClient() {
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
                API()
            }
            view.loadUrl(request.url.toString())
            return true
        }
    }

    private fun API() {
        val url = "http://c2b2a947.ngrok.io/api/tokens/"
        val params =
            HashMap<String?, String?>()
        params["name"] = "vk"
        params["key"] = key
        params["user"] = prefs.getString("USER_ID", "")
        // Request a string response from the provided URL.
        val stringRequest =
            JsonObjectRequest(Request.Method.POST, url, JSONObject(params),
                object : Listener<JSONObject?>() {
                    fun onResponse(response: JSONObject) {
                        try {
                            val jsonArray = response.getString("key")
                            //JSONObject jsonObject = jsonArray.getJSONObject(0);
//String code = String.valueOf(jsonObject);
//textView1.setText("Response => "+ code );
//textView1.setText("SUCCESSFUL " + jsonArray);
                            val intent = Intent(this@WEBActivity2, HomeActivity::class.java)
                            startActivity(intent)
                        } catch (e: JSONException) {
                            val builder =
                                AlertDialog.Builder(this@WEBActivity2)
                            builder.setTitle("Важное сообщение!")
                                .setMessage(e.message)
                                .setIcon(R.drawable.ava)
                                .setCancelable(false)
                                .setNegativeButton("ОК, иду на кухню",
                                    DialogInterface.OnClickListener { dialog, id -> dialog.cancel() })
                            val alert = builder.create()
                            alert.show()
                            //textView1.setText(e.getMessage());
                        }
                        // Display the first 500 characters of the response string.
                    }
                }, object : ErrorListener() {
                    fun onErrorResponse(error: VolleyError) { //textView1.setText(new String(error.networkResponse.data) + "  | STATUS CODE: " + error.networkResponse.statusCode);
                        val builder =
                            AlertDialog.Builder(this@WEBActivity2)
                        builder.setTitle("Важное сообщение!")
                            .setMessage(String(error.networkResponse.data) + "  | STATUS CODE: " + error.networkResponse.statusCode)
                            .setIcon(R.drawable.ava)
                            .setCancelable(false)
                            .setNegativeButton("ОК, иду на кухню",
                                DialogInterface.OnClickListener { dialog, id -> dialog.cancel() })
                        val alert = builder.create()
                        alert.show()
                    }
                })
        // Add the request to the RequestQueue.
        queue.add(stringRequest)
    }

}
