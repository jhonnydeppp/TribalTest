package com.jhonnydev.tribaltest.ui.photos.mvvm

import com.jhonnydev.tribaltest.models.PhotoResponse
import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.jhonnydev.tribaltest.base.apicontract.BaseContract
import com.jhonnydev.tribaltest.base.observer.CallbackHandlingObserver

class PhotoViewModel : ViewModel(), BaseContract.ServiceErrorApi{
    private val TAG = javaClass.simpleName

    val mPhotoModel: PhotoModel = PhotoModel()
    private val ENDPOINT_PHOTOS = "ENDPOINT_PHOTOS"
    var photosList: MutableLiveData<MutableList<PhotoResponse>> = MutableLiveData()
    private var aux :MutableLiveData<MutableList<PhotoResponse>> = MutableLiveData()
    var page = 0

    @SuppressLint("CheckResult")
    fun loadPhotos() {
        page+=1
        mPhotoModel.getPhotoList("$page").subscribeWith(object: CallbackHandlingObserver<List<PhotoResponse>>(this, ENDPOINT_PHOTOS){
            override fun onSuccess(data: List<PhotoResponse>) {
                if(photosList.value == null)
                    photosList.value = data as MutableList<PhotoResponse>
                else
                    photosList.value!!.addAll(data)

                aux.value = photosList.value

                Log.i(TAG, "DATA --->"+photosList.value)
            }
        })
    }

    fun filterByUser(user :String){
            if (user.length <= 1)
                photosList.value = aux.value
            else
                photosList.value = aux.value!!.filter { it.user.username == user || it.user.first_name == user || it.user.last_name == user
                        || (it.user.first_name + it.user.first_name)  == user} as MutableList
    }

    override fun onUnknownError(error: String, caller: String) {
        Log.e(TAG,"onUnknownError error: $error, caller: $caller")
    }

    override fun onTimeoutError(caller: String) {
        Log.e(TAG,"onTimeoutError caller: $caller")
    }

    override fun onNetworkError(caller: String) {
        Log.e(TAG,"onNetworkError caller: $caller")
    }

    override fun onBadRequestError(caller: String, codeError: Int) {
        Log.e(TAG,"onBadRequestError caller: $caller, codeError: $codeError")
    }

    override fun onServerError(caller: String) {
        Log.e(TAG,"onServerError caller: $caller")
    }

    override fun infoError(cause: Throwable?, msg: String?) {
        Log.e(TAG,"onServerError cause: $cause, msg: $msg")
    }
}
