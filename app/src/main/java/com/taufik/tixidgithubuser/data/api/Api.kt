package com.taufik.tixidgithubuser.data.api

import com.taufik.tixidgithubuser.data.model.UserResponseItem
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface Api {

    @GET(UrlEndPoint.USERS_PARAM)
    @Headers("Authorization: token ${UrlEndPoint.API_TOKEN}")
    suspend fun getAllUsers(
        @Query("per_page") perPage: Int
    ): List<UserResponseItem>
}