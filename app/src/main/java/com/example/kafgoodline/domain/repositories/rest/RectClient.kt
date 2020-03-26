package com.example.kafgoodline.domain.repositories.rest

import com.example.kafgoodline.base.IRestClient
import com.google.gson.Gson
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RestClient(private val client: OkHttpClient, gson: Gson, baseUrl: String) : IRestClient {

    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .client(client)
        .build()


    override fun <S> createService(serviceClass: Class<S>): S {
        return retrofit.create(serviceClass)
    }

    override fun cancelAllRequests() {
        client.dispatcher().cancelAll()
    }

}