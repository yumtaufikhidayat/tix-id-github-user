package com.taufik.tixidgithubuser.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.liveData
import com.taufik.tixidgithubuser.data.api.Api
import com.taufik.tixidgithubuser.data.source.TixIdPagingSource
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TixIdRepository @Inject constructor(private val api: Api) {
    fun getAllUsers() =
        Pager(
            config = PagingConfig(
                pageSize = 10,
                maxSize = 100,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { TixIdPagingSource(api) }
        ).liveData
}