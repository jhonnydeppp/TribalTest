package com.jhonnydev.tribaltest.base.apicontract

interface BaseContract {

    interface ErrorApi{

    }

    interface ServiceErrorApi: ErrorApi {
         fun onUnknownError(error: String, caller: String)

         fun onTimeoutError(caller: String)

         fun onNetworkError(caller: String)

         fun onBadRequestError(caller: String, codeError: Int)

         fun onServerError(caller: String)

         fun infoError(cause: Throwable?, msg: String?)

    }



}