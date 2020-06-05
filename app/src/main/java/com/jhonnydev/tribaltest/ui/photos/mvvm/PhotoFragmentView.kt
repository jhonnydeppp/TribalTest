package com.jhonnydev.tribaltest.ui.photos.mvvm

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.jhonnydev.tribaltest.R

class PhotoFragmentView : Fragment() {
    private lateinit var mPhotoViewModel: PhotoViewModel
    companion object {
        fun newInstance() =
            PhotoFragmentView()
    }

    private lateinit var viewModel: PhotoViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        mPhotoViewModel =
            ViewModelProviders.of(this).get(PhotoViewModel::class.java)
        val root = inflater.inflate(R.layout.main_fragment, container, false)
        mPhotoViewModel.loadPhotos()
        return root

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(PhotoViewModel::class.java)


    }

}
