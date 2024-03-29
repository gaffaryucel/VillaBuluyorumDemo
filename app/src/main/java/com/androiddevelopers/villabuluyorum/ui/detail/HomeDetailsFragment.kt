package com.androiddevelopers.villabuluyorum.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import com.androiddevelopers.villabuluyorum.databinding.FragmentHomeDetailsBinding

class HomeDetailsFragment : Fragment() {
    private val viewModel: HomeDetailsViewModel by viewModels()
    private var _binding: FragmentHomeDetailsBinding? = null
    private val binding get() = _binding!!

    private val args: HomeDetailsFragmentArgs by navArgs()
    private val house = args.house

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setClicks()
        setViews()
    }

    private fun setClicks() {
        binding.imageBackNavigation.setOnClickListener {
            Navigation.findNavController(it).popBackStack()
        }
    }

    private fun setViews() {
        with(binding) {
            textDetailTitle.text = house.title
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}