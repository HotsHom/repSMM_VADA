package com.example.kafgoodline.domain.repositories.rest.api

import com.example.kafgoodline.base.ABaseRestApi
import com.example.kafgoodline.base.IRestClient
import com.example.kafgoodline.domain.repositories.models.User
import com.example.kafgoodline.domain.di.module.NetModule
import com.example.kafgoodline.domain.repositories.rest.service.IUserRestApiService
import javax.inject.Inject
import javax.inject.Named

class UserRestApi : ABaseRestApi<IUserRestApiService> {


    @Inject
    constructor(@Named(NetModule.NAME_AUTH_REST_CLIENT) client: IRestClient) : super(client)


    fun registration(login: String, password: String)
            = service.registration(User(username = login, password = password))


    fun login(login: String, password: String)
            = service.login(User(username = login, password = password))


    fun refreshToken(refreshToken: String?)
            = service.refreshToken(refreshToken)

    fun putFSName(user: User)
        = user.id?.let { service.putFSName(it, user) }

}