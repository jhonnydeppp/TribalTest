package com.jhonnydev.tribaltest.ui.favorites.mvvm

import com.jhonnydev.tribaltest.api.PhotoRestApi
import com.jhonnydev.tribaltest.base.RetrofitClient
import com.jhonnydev.tribaltest.models.PhotoResponse
import com.jhonnydev.tribaltest.utils.Constants
import com.jhonnydev.tribaltest.utils.PreferencesUtils
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class FavoritesModel {

    fun getFavoritessList(): List<PhotoResponse>{
       return  PreferencesUtils.getFavoritesList()
    }
}