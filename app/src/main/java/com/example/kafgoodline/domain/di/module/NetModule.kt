package com.example.kafgoodline.domain.di.module

import com.example.kafgoodline.R
import com.example.kafgoodline.base.IRestClient
import com.example.kafgoodline.domain.repositories.AuthRepository
import com.example.kafgoodline.domain.repositories.rest.RestClient
import com.example.kafgoodline.domain.repositories.rest.TokenInterceptor
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton

@Module
class NetModule {

    companion object {

        private const val DOMAIN = "516c5133.ngrok.io"
        private const val DOMAIN_MAIN_API = "http://$DOMAIN"
        private const val CONNECTION_TIMEOUT = 20000L

        const val NAME_AUTH_REST_CLIENT = "NAME_AUTH_REST_CLIENT"
        const val NAME_MAIN_REST_CLIENT = "NAME_MAIN_REST_CLIENT"
        const val NAME_CLIENT_WITHOUT_TOKEN_INTERCEPTOR = "NAME_CLIENT_WITHOUT_TOKEN_INTERCEPTOR"
        const val NAME_CLIENT_WITH_TOKEN_INTERCEPTOR = "NAME_CLIENT_WITH_TOKEN_INTERCEPTOR"
    }


    @Provides
    @Singleton
    fun provideTokenInterceptor(userRepository: AuthRepository) = TokenInterceptor(userRepository)


    @Provides
    @Singleton
    fun provideLoggerInterceptor() = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    } as Interceptor


    @Provides
    @Singleton
    @Named(NAME_CLIENT_WITHOUT_TOKEN_INTERCEPTOR)
    fun provideOkHttpClientWithOutTokenInterceptor(logger: Interceptor) = OkHttpClient.Builder().apply {
        connectTimeout(CONNECTION_TIMEOUT, TimeUnit.MILLISECONDS)
        readTimeout(CONNECTION_TIMEOUT, TimeUnit.MILLISECONDS)
        writeTimeout(CONNECTION_TIMEOUT, TimeUnit.MILLISECONDS)
        addInterceptor(logger)
    }.build()

    @Provides
    @Singleton
    @Named(NAME_CLIENT_WITH_TOKEN_INTERCEPTOR)
    fun provideOkHttpClientWithTokenInterceptor(logger: Interceptor, token: TokenInterceptor): OkHttpClient {
        return OkHttpClient.Builder()
            .connectTimeout(CONNECTION_TIMEOUT, TimeUnit.MILLISECONDS)
            .readTimeout(CONNECTION_TIMEOUT, TimeUnit.MILLISECONDS)
            .writeTimeout(CONNECTION_TIMEOUT, TimeUnit.MILLISECONDS)
            .addInterceptor(logger)
            .addInterceptor(token)
            .build()
    }

    @Provides
    @Singleton
    fun provideGson() = GsonBuilder().create()


    @Provides
    @Singleton
    @Named(NAME_AUTH_REST_CLIENT)
    fun provideAuthRestClient(@Named(NAME_CLIENT_WITHOUT_TOKEN_INTERCEPTOR) client: OkHttpClient, gson: Gson)
            = RestClient(client, gson, DOMAIN_MAIN_API) as IRestClient


    @Provides
    @Singleton
    @Named(NAME_MAIN_REST_CLIENT)
    fun provideMainRestClient(@Named(NAME_CLIENT_WITH_TOKEN_INTERCEPTOR) client: OkHttpClient, gson: Gson)
            = RestClient(client, gson, DOMAIN_MAIN_API) as IRestClient

}