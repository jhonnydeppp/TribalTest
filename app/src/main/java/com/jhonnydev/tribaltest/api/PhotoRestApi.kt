package com.jhonnydev.tribaltest.api

import PhotoResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface PhotoRestApi {

    @Headers("Content-Type:application/json", "Accept:application/json")
    @GET("photos/")
    fun getPhotoList(@Query("client_id")  client_id: String,
                     @Query("page")  page: String): Observable<List<PhotoResponse>>
}