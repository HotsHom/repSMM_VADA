package com.example.kafgoodline.base

interface IRestClient {

    fun <S> createService(serviceClass: Class<S>): S

    fun cancelAllRequests()
}