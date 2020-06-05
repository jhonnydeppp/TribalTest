package com.jhonnydev.tribaltest.ui.detailimage

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.jhonnydev.tribaltest.models.PhotoResponse

class DetailImageViewModel : ViewModel() {

    var photoLiveData : MutableLiveData<PhotoResponse> = MutableLiveData()

    fun loadImage( photo :PhotoResponse){
        photoLiveData.value = photo
    }
}