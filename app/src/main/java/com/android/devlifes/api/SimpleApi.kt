package com.android.devlifes.api

import com.android.devlifes.model.Gif
import retrofit2.Response
import retrofit2.http.GET

interface SimpleApi {
    @GET("./random?json=true")
    suspend fun getGif(): Response<Gif>
}