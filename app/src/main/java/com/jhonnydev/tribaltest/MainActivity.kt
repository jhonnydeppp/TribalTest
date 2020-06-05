package com.jhonnydev.tribaltest


import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.jhonnydev.tribaltest.ui.photos.mvvm.PhotoFragmentView
import com.jhonnydev.tribaltest.utils.Utils


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        if (savedInstanceState == null) {
            Utils.cambiarFragments(PhotoFragmentView.newInstance(),supportFragmentManager,R.id.container)

        }
    }
}
