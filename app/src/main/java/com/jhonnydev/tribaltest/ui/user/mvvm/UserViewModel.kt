package com.jhonnydev.tribaltest.ui.user.mvvm

import android.annotation.SuppressLint
import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.jhonnydev.tribaltest.base.apicontract.BaseContract
import com.jhonnydev.tribaltest.base.observer.CallbackHandlingObserver
import com.jhonnydev.tribaltest.models.PhotoResponse
import com.jhonnydev.tribaltest.ui.photos.mvvm.PhotoModel

class UserViewModel : ViewModel() , BaseContract.ServiceErrorApi{

    private val TAG = javaClass.simpleName

    val mUserModel: UserModel = UserModel()
    private val ENDPOINT_PHOTOS = "ENDPOINT_PHOTOS"
    var photosList: MutableLiveData<MutableList<PhotoResponse>> = MutableLiveData()
    var page = 0 // empiezan desde la pagina 10 por que la api de cayo y no funcionaba, cuando regreso solo a partir de la pagina 10 funcionaba

    @SuppressLint("CheckResult")
    fun loadPhotos() {
        page+=1
        mUserModel.getPhotoList("$page").subscribeWith(object: CallbackHandlingObserver<List<PhotoResponse>>(this, ENDPOINT_PHOTOS){
            override fun onSuccess(data: List<PhotoResponse>) {
                if(photosList.value == null)
                    photosList.value = data as MutableList<PhotoResponse>
                else
                    photosList.value!!.addAll(data)

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
