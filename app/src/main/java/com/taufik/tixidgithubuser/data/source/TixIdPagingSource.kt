package com.taufik.tixidgithubuser.data.source

import androidx.paging.PagingSource
import com.taufik.tixidgithubuser.data.api.Api
import com.taufik.tixidgithubuser.data.api.UrlEndPoint
import com.taufik.tixidgithubuser.data.model.UserResponseItem
import retrofit2.HttpException
import java.io.IOException

class TixIdPagingSource(
    private val api: Api
) : PagingSource<Int, UserResponseItem>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, UserResponseItem> {
        val position = params.key ?: UrlEndPoint.STARTING_PAGE_INDEX

        return try {
            val response = api.getAllUsers(params.loadSize)

            LoadResult.Page(
                data = response,
                prevKey = if (position == UrlEndPoint.STARTING_PAGE_INDEX) null else position - 1,
                nextKey = if (response.isEmpty()) null else position + 1
            )
        } catch (e: IOException) {
            LoadResult.Error(e)
        } catch (e: HttpException) {
            LoadResult.Error(e)
        }
    }
}