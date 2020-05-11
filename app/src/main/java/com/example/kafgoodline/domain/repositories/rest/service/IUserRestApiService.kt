package com.example.kafgoodline.domain.repositories.rest.service

import com.example.kafgoodline.domain.repositories.models.rest.User
import com.example.kafgoodline.domain.repositories.models.rest.UserAPI
import io.reactivex.Observable
import retrofit2.Call
import retrofit2.http.*

interface IUserRestApiService {


    /**
     * Регистрация нового профиля пользователя
     */
    @POST("/auth/users/")
    fun registration(@Body user: User): Observable<User>


    /**
     * Авторизация пользователя по существующему профилю
     */
    @POST("/auth/jwt/create/")
    fun login(@Body user: User): Observable<User>

    /**
     * Изменение имени и фамилии пользователя. ИСПОЛЬЗОВАТЬ С ТОКЕНОМ!
     */
    @PUT("/list_user/users/{id}/")
    fun putFSName(
        @Path("id") id: Int,
        @Body user: User
    ): Observable<User>


    /**
     * Будет использовать для обновления текущего токена пользователя
     */
    @POST("/auth/jwt/refresh/")
    fun refreshToken(
        @Body user: User
    ): Call<User>


    @POST("/list_user/token/")
    fun putVkToken(
        @Body user: UserAPI
    ): Observable<UserAPI>

    @GET("/token/{user_id}/")
    fun getTokenVk(
        @Path ("user_id") id: Int
    ): Observable<UserAPI>

}