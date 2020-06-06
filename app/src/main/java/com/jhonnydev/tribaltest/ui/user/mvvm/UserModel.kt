package com.jhonnydev.tribaltest.ui.user.mvvm

import com.jhonnydev.tribaltest.api.PhotoRestApi
import com.jhonnydev.tribaltest.base.RetrofitClient
import com.jhonnydev.tribaltest.utils.Constants
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class UserModel {
    private val API = RetrofitClient.getClient().create(PhotoRestApi::class.java)

    fun getPhotoList(page: String ) = API.getPhotoList(Constants.API_KEY,page).subscribeOn(
        Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())!!
}