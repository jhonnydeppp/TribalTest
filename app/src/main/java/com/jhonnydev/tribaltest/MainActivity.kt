package com.jhonnydev.tribaltest


import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.jhonnydev.tribaltest.ui.favorites.mvvm.FavoritesFragment
import com.jhonnydev.tribaltest.ui.photos.mvvm.PhotoFragmentView
import com.jhonnydev.tribaltest.utils.PreferencesUtils
import com.jhonnydev.tribaltest.utils.Utils
import kotlinx.android.synthetic.main.main_activity.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        if (savedInstanceState == null) {
            Utils.cambiarFragments(PhotoFragmentView.newInstance(),supportFragmentManager,R.id.container)
            PreferencesUtils.init(applicationContext)
            iv_photos.setOnClickListener{Utils.cambiarFragments(PhotoFragmentView.newInstance(),supportFragmentManager,R.id.container)}
            iv_favorite.setOnClickListener{Utils.cambiarFragments(FavoritesFragment.newInstance(),supportFragmentManager,R.id.container)}
        }
    }
}
