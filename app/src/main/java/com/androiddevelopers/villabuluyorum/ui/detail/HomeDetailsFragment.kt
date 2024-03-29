package com.androiddevelopers.villabuluyorum.ui.detail

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.androiddevelopers.villabuluyorum.R

class HomeDetailsFragment : Fragment() {

    companion object {
        fun newInstance() = HomeDetailsFragment()
    }

    private lateinit var viewModel: HomeDetailsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home_details, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(HomeDetailsViewModel::class.java)
        // TODO: Use the ViewModel
    }

}