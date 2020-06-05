package com.jhonnydev.tribaltest.ui.photos.mvvm

import PhotoResponse
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
    var photosList: MutableLiveData<List<PhotoResponse>> = MutableLiveData()

    @SuppressLint("CheckResult")
    fun loadPhotos() {
        mPhotoModel.getPhotoList().subscribeWith(object: CallbackHandlingObserver<List<PhotoResponse>>(this, ENDPOINT_PHOTOS){
            override fun onSuccess(data: List<PhotoResponse>) {
                photosList.value = data
                Log.i(TAG, "DATA --->"+photosList.value)
            }
        })
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
