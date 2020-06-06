package com.jhonnydev.tribaltest.ui.favorites.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.jhonnydev.tribaltest.MainActivity
import com.jhonnydev.tribaltest.R
import com.jhonnydev.tribaltest.databinding.ItemFavoriteBinding
import com.jhonnydev.tribaltest.models.PhotoResponse
import com.jhonnydev.tribaltest.models.User
import com.jhonnydev.tribaltest.ui.detailimage.DetailImageFragment
import com.jhonnydev.tribaltest.ui.user.mvvm.UserFragmentView
import com.jhonnydev.tribaltest.utils.Utils
import okhttp3.internal.Util


class FavoriteAdapter (
    private val photoList: List<PhotoResponse>,
    private val context: Context,
    private val activity: MainActivity,
    private val mRefreshRecycler: RefreshRecycler
) : RecyclerView.Adapter<FavoriteAdapter.FavoriteViewHolder>(){

    override fun getItemCount() = photoList.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        FavoriteViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_favorite,
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: FavoriteViewHolder, position: Int) {
        holder.recyclerviewFavoriteBinding.favoriteItem = photoList[position]
        Utils.loadImage(photoList[position].urls.small,  holder.recyclerviewFavoriteBinding.ivPhoto, context)
        Utils.loadImageRoundedImage(photoList[position].user.profile_image.small,  holder.recyclerviewFavoriteBinding.ivUser, context)
        holder.recyclerviewFavoriteBinding.ivUser.setOnClickListener{goToUser(photoList[position].user,activity)}
        holder.recyclerviewFavoriteBinding.tvName.setOnClickListener{goToUser(photoList[position].user,activity)}
        holder.recyclerviewFavoriteBinding.ivStart.setOnClickListener{deleteFavorite(photoList[position])}
        holder.recyclerviewFavoriteBinding.tvGuardar.setOnClickListener{deleteFavorite(photoList[position])}
        holder.recyclerviewFavoriteBinding.ivPhoto .setOnClickListener{goToDetailPhoto(photoList[position],activity)}
    }

    private fun deleteFavorite(photo :PhotoResponse){
        Utils.makeToast(context, context.resources.getString(R.string.borrar_favoritos))
        Utils.deleteFavorite(photo)
        mRefreshRecycler.refresh()
    }

    private fun goToUser(user: User, activity: MainActivity){
        Utils.cambiarFragments(UserFragmentView.newInstance(user),activity.supportFragmentManager,R.id.container)
    }

    private fun goToDetailPhoto(photo: PhotoResponse, activity: MainActivity){
        Utils.cambiarFragments(DetailImageFragment.newInstance(photo),activity.supportFragmentManager,R.id.container)
    }


    inner class FavoriteViewHolder(
        val recyclerviewFavoriteBinding: ItemFavoriteBinding
    ) : RecyclerView.ViewHolder(recyclerviewFavoriteBinding.root)

}

interface RefreshRecycler{
    fun refresh()
}