package com.jhonnydev.tribaltest.ui.photos.mvvm

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.jhonnydev.tribaltest.MainActivity
import com.jhonnydev.tribaltest.R
import com.jhonnydev.tribaltest.ui.photos.adapter.PhotoAdapter
import kotlinx.android.synthetic.main.photo_fragment.*
import java.util.*


class PhotoFragmentView : Fragment() {
    private lateinit var mPhotoViewModel: PhotoViewModel
    private val TAG = javaClass.simpleName
    companion object {
        fun newInstance() =
            PhotoFragmentView()
    }

    private lateinit var viewModel: PhotoViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        mPhotoViewModel =
            ViewModelProviders.of(this).get(PhotoViewModel::class.java)
        val root = inflater.inflate(R.layout.photo_fragment, container, false)
        mPhotoViewModel.loadPhotos()
        rvConfig()
        return root
    }

    private fun rvConfig(){
        mPhotoViewModel.photosList.observe(viewLifecycleOwner, Observer { photo ->
            rv_photos.also {
                it.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
                it.setHasFixedSize(true)
                it.adapter = context?.let { it1 -> PhotoAdapter(photo, it1, activity as MainActivity
                ) }
            }
        })
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    fun init(){
        et_buscar.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(
                s: CharSequence, start: Int, count: Int,
                after: Int
            ) {
            }

            override fun onTextChanged(
                s: CharSequence, start: Int, before: Int,
                count: Int
            ) {

            }

            override fun afterTextChanged(s: Editable) {
                //avoid triggering event when text is too short
                mPhotoViewModel.filterByUser(et_buscar.text.toString())
                //Log.i(TAG, "afterTextChanged: "+et_buscar.text.toString())
            }
        })
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(PhotoViewModel::class.java)

    }

}
