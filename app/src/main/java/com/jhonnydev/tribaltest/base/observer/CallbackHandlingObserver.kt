package com.jhonnydev.tribaltest.base.observer

import android.util.Log
import com.jhonnydev.tribaltest.base.apicontract.BaseContract


import io.reactivex.observers.DisposableObserver
import retrofit2.HttpException
import java.io.IOException
import java.net.SocketTimeoutException

abstract class CallbackHandlingObserver<T>(private val errorApi: BaseContract.ServiceErrorApi, private val interactor: String) : DisposableObserver<T>() {

    override fun onNext(t: T) {
        onSuccess(t)
    }

    override fun onError(ex: Throwable) {
        Log.i("Info", "onError: ex cause-->" + ex.cause)
        Log.i("Info", "onError: ex message-->" + ex.message)
        when (ex) {
            is HttpException -> {
                val errorCode = ex.response()!!.code()
                Log.i("Info", "onError: ex-->" + ex.message())
                handleErrorCode(errorCode, interactor)
            }
            is SocketTimeoutException -> {
                errorApi.onTimeoutError(interactor)

            }
            is IOException -> {
                errorApi.onNetworkError(interactor)
            }
            else -> {
                errorApi.onUnknownError(ex.message!!, interactor)
            }
        }

        errorApi.infoError(ex.cause, ex.message)

    }


    private fun handleErrorCode(errorCode: Int, interactor: String) {

        Log.i("Info", "handleErrorCode: codigo error $errorCode")

        if (errorCode == 500) {
            errorApi.onServerError(interactor)
        } else if (errorCode in 400..499)
            errorApi.onBadRequestError(interactor, errorCode)

    }

    override fun onComplete() {
        //DO NOTHING
    }


    protected abstract fun onSuccess(data: T)
}