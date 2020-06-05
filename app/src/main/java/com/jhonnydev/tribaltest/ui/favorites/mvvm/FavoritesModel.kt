package com.jhonnydev.tribaltest.ui.favorites.mvvm

import com.jhonnydev.tribaltest.api.PhotoRestApi
import com.jhonnydev.tribaltest.base.RetrofitClient
import com.jhonnydev.tribaltest.utils.Constants
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class FavoritesModel {
    private val API = RetrofitClient.getClient().create(PhotoRestApi::class.java)

    fun getFavoritesList() = API.getPhotoList(Constants.API_KEY,"1").subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())!!
}