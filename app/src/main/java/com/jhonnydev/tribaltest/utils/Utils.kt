package com.jhonnydev.tribaltest.utils

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterInside
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.jhonnydev.tribaltest.models.PhotoResponse



object Utils {

    private val TAG = javaClass.simpleName

    fun changeActivity(activityDestino: Class<*>, bundle: Bundle?, context: Context) {
        val intent = Intent(context, activityDestino)
        if (bundle != null)
            intent.putExtras(bundle)
        context.startActivity(intent)
    }

    fun changeActivityClearTask(activityDestino: Class<*>, bundle: Bundle?, context: Context) {
        val intent = Intent(context, activityDestino)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
        if (bundle != null)
            intent.putExtras(bundle)
        context.startActivity(intent)
    }

    fun cambiarFragments(fragment: Fragment, fragmentManager: FragmentManager, contenedor: Int) {
        fragmentManager
            .beginTransaction()
            .replace(contenedor, fragment)
            .addToBackStack(null)
            .commit()
    }

    fun makeToast(context: Context, msg: String) {
        Toast.makeText(context, msg, Toast.LENGTH_LONG).show()
    }

    fun loadImage(url: String, image: ImageView, context: Context) {
        Glide.with(context)
            .load(url)
            .transform(CenterInside(), RoundedCorners(24))
            .into(image)
    }

    fun loadImageRoundedImage(url: String, image: ImageView, context: Context) {
        Glide.with(context)
            .load(url)
            .transform(CenterInside(), RoundedCorners(100))
            .into(image)
    }

    fun saveFavorite(mPhotoResponse: PhotoResponse) {
        val list :List<PhotoResponse> = PreferencesUtils.getFavoritesList()
        if (list.isNullOrEmpty())
            PreferencesUtils.setFavoritesList(listOf(mPhotoResponse))
        else
            if (list.filter { it == mPhotoResponse }.isEmpty()){
                val listAux = list as MutableList
                listAux.add(mPhotoResponse)
                PreferencesUtils.setFavoritesList((listAux))
            }
    }


    fun deleteFavorite(mPhotoResponse: PhotoResponse) {
        val list :List<PhotoResponse> = PreferencesUtils.getFavoritesList()
        if (!list.isNullOrEmpty())
            if (list.filter { it == mPhotoResponse }.isNotEmpty()){
                val listAux = list as MutableList
                listAux.remove(mPhotoResponse)
                PreferencesUtils.setFavoritesList(listAux)
            }
    }
}