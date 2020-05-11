package com.example.kafgoodline.domain.repositories.rest.api

import com.example.kafgoodline.base.ABaseRestApi
import com.example.kafgoodline.base.IRestClient
import com.example.kafgoodline.domain.di.module.NetModule
import com.example.kafgoodline.domain.repositories.models.rest.User
import com.example.kafgoodline.domain.repositories.models.rest.UserAPI
import com.example.kafgoodline.domain.repositories.rest.service.IUserRestApiService
import javax.inject.Inject
import javax.inject.Named

class UserWithTokenRestApi : ABaseRestApi<IUserRestApiService> {


    @Inject
    constructor(@Named(NetModule.NAME_MAIN_REST_CLIENT) client: IRestClient) : super(client)

    fun putFSName(user: User) = user.id?.let { service.putFSName(it, user) }

    fun putVkToken(user: UserAPI) = user.user_id?.let { service.putVkToken(user) }

    fun getTokenVk(user: User) = user.id?.let { service.getTokenVk(it) }

}