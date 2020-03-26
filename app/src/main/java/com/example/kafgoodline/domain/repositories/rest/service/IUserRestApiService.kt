package com.example.kafgoodline.domain.repositories.rest.service

import retrofit2.http.Body
import retrofit2.http.PUT
import io.reactivex.Observable
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.Call
import com.example.kafgoodline.domain.di.models.Token
import com.example.kafgoodline.domain.di.models.User

interface IUserRestApiService {


    /**
     * Регистрация нового профиля пользователя
     */
    @PUT("/auth/users/")
    fun registration(@Body user: User): Call<User>


    /**
     * Авторизация пользователя по существующему профилю
     */
    @POST("/auth/jwt/create/")
    fun login(@Body user: User): Observable<Token>


    /**
     * Будет использовать для обновления текущего токена пользователя
     */
    @POST("/auth/jwt/refresh/")
    fun refreshToken(
        @Header("refresh_token") refreshToken: String
    ): Call<Token>
}