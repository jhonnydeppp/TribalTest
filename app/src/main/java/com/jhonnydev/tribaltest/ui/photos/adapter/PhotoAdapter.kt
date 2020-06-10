package com.jhonnydev.tribaltest.ui.photos.adapter

import android.app.Activity
import com.jhonnydev.tribaltest.models.PhotoResponse
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.jhonnydev.tribaltest.MainActivity
import com.jhonnydev.tribaltest.R
import com.jhonnydev.tribaltest.databinding.ItemPhotoBinding
import com.jhonnydev.tribaltest.models.User
import com.jhonnydev.tribaltest.ui.detailimage.DetailImageFragment
import com.jhonnydev.tribaltest.ui.user.mvvm.UserFragmentView
import com.jhonnydev.tribaltest.utils.Utils



class PhotoAdapter (
    private val photoList: List<PhotoResponse>,
    private val context: Context,
    private val activity: MainActivity
) : RecyclerView.Adapter<PhotoAdapter.PhotoViewHolder>(){

    override fun getItemCount() = photoList.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        PhotoViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_photo,
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: PhotoViewHolder, position: Int) {
        holder.recyclerviewPhotoBinding.photoItem = photoList[position]
        Utils.loadImage(photoList[position].urls.full,  holder.recyclerviewPhotoBinding.ivPhoto, context)
        Utils.loadImageRoundedImage(photoList[position].user.profile_image.medium,  holder.recyclerviewPhotoBinding.ivUser, context)
        holder.recyclerviewPhotoBinding.ivUser.setOnClickListener{goToUser(photoList[position].user,activity)}
        holder.recyclerviewPhotoBinding.tvName.setOnClickListener{goToUser(photoList[position].user,activity)}
        holder.recyclerviewPhotoBinding.ivStart.setOnClickListener{saveFavorite(photoList[position])}
        holder.recyclerviewPhotoBinding.tvGuardar.setOnClickListener{saveFavorite(photoList[position])}
        holder.recyclerviewPhotoBinding.ivPhoto .setOnClickListener{goToDetailPhoto(photoList[position],activity)}
    }

    private fun saveFavorite(photo :PhotoResponse){
        Utils.makeToast(context, context.resources.getString(R.string.guardar_favoritos))
        Utils.saveFavorite(photo)
    }

    private fun goToUser(user: User, activity: MainActivity){
        Utils.cambiarFragments(UserFragmentView.newInstance(user),activity.supportFragmentManager,R.id.container)
    }

    private fun goToDetailPhoto(photo: PhotoResponse, activity: MainActivity){
        Utils.cambiarFragments(DetailImageFragment.newInstance(photo),activity.supportFragmentManager,R.id.container)
    }


    inner class PhotoViewHolder(
        val recyclerviewPhotoBinding: ItemPhotoBinding
    ) : RecyclerView.ViewHolder(recyclerviewPhotoBinding.root)

}