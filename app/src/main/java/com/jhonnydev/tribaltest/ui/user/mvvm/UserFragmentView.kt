package com.jhonnydev.tribaltest.ui.user.mvvm

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.jhonnydev.tribaltest.MainActivity
import com.jhonnydev.tribaltest.R
import com.jhonnydev.tribaltest.databinding.UserFragmentBinding
import com.jhonnydev.tribaltest.models.User
import com.jhonnydev.tribaltest.ui.photos.adapter.PhotoAdapter
import com.jhonnydev.tribaltest.ui.user.adapter.UserAdapter
import com.jhonnydev.tribaltest.utils.Utils
import kotlinx.android.synthetic.main.photo_fragment.*
import kotlinx.android.synthetic.main.user_fragment.*


class UserFragmentView : Fragment() {
    private lateinit var mUserViewModel: UserViewModel
    lateinit var user: User
    companion object {
        fun newInstance(
            user: User
        ): UserFragmentView{
            val mUserFragmentView : UserFragmentView = UserFragmentView()
            mUserFragmentView.user = user
            return mUserFragmentView
        }
    }

    private lateinit var viewModel: UserViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mUserViewModel = ViewModelProviders.of(this).get(UserViewModel::class.java)

        val binding: UserFragmentBinding = DataBindingUtil.inflate(
            inflater,R.layout.user_fragment, container, false
        )
        val view: View = binding.getRoot()
        binding.userDetail = user
        mUserViewModel.loadPhotos()
        rvConfig()
        return view
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init(){
       context?.let { Utils.loadImageRoundedImage(user.profile_image.small, iv_user, it) }
    }

    private fun rvConfig(){
        mUserViewModel.photosList.observe(viewLifecycleOwner, Observer { photo ->
            rv_user_images.also {
                it.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.HORIZONTAL)
                it.setHasFixedSize(true)

                it.addOnScrollListener(object : RecyclerView.OnScrollListener() {
                    override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                        super.onScrollStateChanged(recyclerView, newState)
                        if (!recyclerView.canScrollVertically(1)) {
                           // mUserViewModel.loadPhotos()
                            //rvConfig()
                        }
                    }
                })

                it.adapter = context?.let { it1 -> UserAdapter(photo, it1, activity as MainActivity
                ) }
            }
        })

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(UserViewModel::class.java)
    }

}