package com.jhonnydev.tribaltest.ui.favorites.mvvm

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.jhonnydev.tribaltest.base.apicontract.BaseContract
import com.jhonnydev.tribaltest.base.observer.CallbackHandlingObserver
import com.jhonnydev.tribaltest.models.PhotoResponse

class FavoritesViewModel : ViewModel(){
    private val TAG = javaClass.simpleName

    val mFavoritesModel: FavoritesModel = FavoritesModel()
    private val ENDPOINT_FAVORITES = "ENDPOINT_FAVORITES"
    var favoriteList: MutableLiveData<List<PhotoResponse>> = MutableLiveData()
    private var aux :MutableLiveData<List<PhotoResponse>> = MutableLiveData()

    @SuppressLint("CheckResult")
    fun loadFavorites() {
        favoriteList.value = mFavoritesModel.getFavoritessList()
        aux.value = favoriteList.value
    }

    fun filterByUser(user :String){
        if (user.length <= 1)
            favoriteList.value = aux.value
        else
            favoriteList.value = aux.value!!.filter { it.user.username == user || it.user.first_name == user || it.user.last_name == user
                    || (it.user.first_name + it.user.first_name)  == user}
    }
}
