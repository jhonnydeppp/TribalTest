package com.jhonnydev.tribaltest.ui.favorites.mvvm

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.jhonnydev.tribaltest.MainActivity
import com.jhonnydev.tribaltest.R
import com.jhonnydev.tribaltest.ui.favorites.adapter.FavoriteAdapter
import kotlinx.android.synthetic.main.favorites_fragment.*
import kotlinx.android.synthetic.main.photo_fragment.et_buscar
import kotlinx.android.synthetic.main.photo_fragment.rv_photos

class FavoritesFragment : Fragment() {
    private lateinit var mFavoritesViewModel: FavoritesViewModel
    companion object {
        fun newInstance() = FavoritesFragment()
    }

    private lateinit var viewModel: FavoritesViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mFavoritesViewModel =
            ViewModelProviders.of(this).get(FavoritesViewModel::class.java)
        val root = inflater.inflate(R.layout.favorites_fragment, container, false)
        mFavoritesViewModel.loadFavorites()
        rvConfig()
        return root
    }
    private fun rvConfig(){
        mFavoritesViewModel.favoriteList.observe(viewLifecycleOwner, Observer { favorite ->
            rv_photos.also {
                if(favorite.isEmpty())
                    emptyList()
                else
                    hideMessage()
                it.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
                it.setHasFixedSize(true)
                it.adapter = context?.let { it1 -> FavoriteAdapter(favorite, it1, activity as MainActivity) }
            }
        })
    }

    private fun emptyList(){
        tv_favorite_empty.visibility = View.VISIBLE
    }

    private fun hideMessage(){
        tv_favorite_empty.visibility = View.GONE
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
                mFavoritesViewModel.filterByUser(et_buscar.text.toString())
                //Log.i(TAG, "afterTextChanged: "+et_buscar.text.toString())
            }
        })
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(FavoritesViewModel::class.java)
        // TODO: Use the ViewModel
    }

}