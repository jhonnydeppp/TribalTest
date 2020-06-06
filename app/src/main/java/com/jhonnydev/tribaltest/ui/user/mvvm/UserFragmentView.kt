package com.jhonnydev.tribaltest.ui.user.mvvm

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.jhonnydev.tribaltest.R
import com.jhonnydev.tribaltest.databinding.UserFragmentBinding
import com.jhonnydev.tribaltest.models.User
import com.jhonnydev.tribaltest.utils.Utils
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

        val root = inflater.inflate(R.layout.user_fragment, container, false)
        val binding: UserFragmentBinding = DataBindingUtil.inflate(
            inflater,R.layout.user_fragment, container, false
        )
        val view: View = binding.getRoot()

        binding.userDetail = user
        return view
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init(){
       context?.let { Utils.loadImage(user.profile_image.small, iv_user, it) }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(UserViewModel::class.java)
    }

}