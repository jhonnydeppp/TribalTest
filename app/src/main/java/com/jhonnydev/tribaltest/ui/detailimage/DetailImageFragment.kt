package com.jhonnydev.tribaltest.ui.detailimage

import android.R.attr.data
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.jhonnydev.tribaltest.R
import com.jhonnydev.tribaltest.databinding.DetailImageFragmentBinding
import com.jhonnydev.tribaltest.models.PhotoResponse
import com.jhonnydev.tribaltest.utils.Utils
import kotlinx.android.synthetic.main.detail_image_fragment.*
import kotlinx.android.synthetic.main.detail_image_fragment.iv_back
import kotlinx.android.synthetic.main.detail_image_fragment.iv_user
import kotlinx.android.synthetic.main.user_fragment.*


class DetailImageFragment : Fragment() {
    lateinit var photo: PhotoResponse
    private lateinit var mDetailImageViewModel: DetailImageViewModel
    private val TAG = javaClass.simpleName
    companion object {
        fun newInstance(photo: PhotoResponse): DetailImageFragment{
            val mDetailImageFragment = DetailImageFragment()
            mDetailImageFragment.photo = photo
            return mDetailImageFragment
        }
    }

    private lateinit var viewModel: DetailImageViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mDetailImageViewModel = ViewModelProviders.of(this).get(DetailImageViewModel::class.java)

        val root = inflater.inflate(R.layout.detail_image_fragment, container, false)

        val binding: DetailImageFragmentBinding = DataBindingUtil.inflate(
            inflater,R.layout.detail_image_fragment, container, false
        )
        val view: View = binding.getRoot()
        binding.photoDetail = photo
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    fun init(){
       // mDetailImageViewModel.loadImage(photo)
        iv_back.setOnClickListener{ activity?.onBackPressed() }
        context?.let { Utils.loadImage(photo.urls.small, iv_image, it) }
        context?.let { Utils.loadImageRoundedImage(photo.user.profile_image.small, iv_user, it) }
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(DetailImageViewModel::class.java)
        // TODO: Use the ViewModel
    }

}