package com.jhonnydev.tribaltest.ui.user.mvvm

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.jhonnydev.tribaltest.R
import com.jhonnydev.tribaltest.models.User

class UserFragmentView : Fragment() {

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
        return inflater.inflate(R.layout.user_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(UserViewModel::class.java)
        // TODO: Use the ViewModel
    }

}