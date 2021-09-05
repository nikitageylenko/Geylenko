package com.android.devlifes.api

import com.android.devlifes.model.Gif
import retrofit2.Response

class Repository {
    suspend fun getGif(): Response<Gif> {
        return RetrofitInstance.api.getGif()
    }
}