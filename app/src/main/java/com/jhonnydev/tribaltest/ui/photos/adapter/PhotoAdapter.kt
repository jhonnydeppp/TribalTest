package com.jhonnydev.tribaltest.ui.photos.adapter

import com.jhonnydev.tribaltest.models.PhotoResponse
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.jhonnydev.tribaltest.R
import com.jhonnydev.tribaltest.databinding.ItemPhotoBinding
import com.jhonnydev.tribaltest.utils.Utils
import com.squareup.picasso.Picasso


class PhotoAdapter (
    private val photoList: List<PhotoResponse>,
    context: Context
) : RecyclerView.Adapter<PhotoAdapter.PhotoViewHolder>(){

    private val context = context
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
        Utils.loadImage(photoList[position].urls.small,  holder.recyclerviewPhotoBinding.ivPhoto, context)
        Utils.loadImageRoundedImage(photoList[position].user.profile_image.small,  holder.recyclerviewPhotoBinding.ivUser, context)
    }


    inner class PhotoViewHolder(
        val recyclerviewPhotoBinding: ItemPhotoBinding
    ) : RecyclerView.ViewHolder(recyclerviewPhotoBinding.root)

}