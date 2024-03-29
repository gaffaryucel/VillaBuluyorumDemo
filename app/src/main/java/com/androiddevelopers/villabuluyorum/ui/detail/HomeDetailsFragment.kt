package com.androiddevelopers.villabuluyorum.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import com.androiddevelopers.villabuluyorum.R
import com.androiddevelopers.villabuluyorum.adapter.CarouselImageAdapter
import com.androiddevelopers.villabuluyorum.databinding.FragmentHomeDetailsBinding
import com.androiddevelopers.villabuluyorum.model.House
import com.androiddevelopers.villabuluyorum.util.hideBottomNavigation
import com.androiddevelopers.villabuluyorum.util.showBottomNavigation
import com.bumptech.glide.Glide
import com.google.android.material.carousel.CarouselLayoutManager

class HomeDetailsFragment : Fragment() {
    private val viewModel: HomeDetailsViewModel by viewModels()
    private var _binding: FragmentHomeDetailsBinding? = null
    private val binding get() = _binding!!

    private lateinit var house: House
    private val adapter = CarouselImageAdapter()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val args: HomeDetailsFragmentArgs by navArgs()
        house = args.house
    }

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
        with(binding) {
            imageBackNavigation.setOnClickListener {
                Navigation.findNavController(it).popBackStack()
            }

            imageBookmark.setOnClickListener {
                Toast.makeText(it.context, "Listenize kaydedildi", Toast.LENGTH_SHORT).show()
            }

            buttonDetailChat.setOnClickListener {
                Toast.makeText(it.context, "Mesajlar açılıyor", Toast.LENGTH_SHORT).show()
            }

            buttonDetailCall.setOnClickListener {
                Toast.makeText(it.context, "Arama yapılıyor", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun setViews() {
        with(binding) {
            Glide.with(root)
                .load(house.mainImage)
                .error(R.drawable.placeholder)
                .into(imageTitle)
            textDetailTitle.text = house.title
            textDetailAddress.text = house.address
            "${house.bedrooms} Yatak Odası".also { textDetailBedRoom.text = it }
            "${house.bathrooms} Banyo".also { textDetailBathRoom.text = it }
            carouselRecyclerView.layoutManager = CarouselLayoutManager()
            carouselRecyclerView.adapter = adapter
            adapter.images = house.images.toList()
            textDetailDescription.text = house.description
            "${house.rentPrice} TL / Aylık".also { textDetailPrice.text = it }
        }
    }

    override fun onPause() {
        super.onPause()
        showBottomNavigation(requireActivity())
    }

    override fun onResume() {
        super.onResume()
        hideBottomNavigation(requireActivity())
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}