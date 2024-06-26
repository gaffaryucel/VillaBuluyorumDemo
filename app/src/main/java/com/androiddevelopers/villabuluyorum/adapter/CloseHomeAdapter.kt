package com.androiddevelopers.villabuluyorum.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.androiddevelopers.villabuluyorum.databinding.RowHouseBinding
import com.androiddevelopers.villabuluyorum.model.House
import com.androiddevelopers.villabuluyorum.ui.home.HomeFragment
import com.androiddevelopers.villabuluyorum.ui.home.HomeFragmentDirections
import com.bumptech.glide.Glide

class HouseAdapter : RecyclerView.Adapter<HouseAdapter.HouseViewHolder>() {

    private val diffUtil = object : DiffUtil.ItemCallback<House>() {
        override fun areItemsTheSame(oldItem: House, newItem: House): Boolean {
            return oldItem.name == newItem.name
        }

        override fun areContentsTheSame(oldItem: House, newItem: House): Boolean {
            return oldItem == newItem
        }
    }
    private val recyclerListDiffer = AsyncListDiffer(this, diffUtil)

    var housesList: List<House>
        get() = recyclerListDiffer.currentList
        set(value) = recyclerListDiffer.submitList(value)

    inner class HouseViewHolder(val binding: RowHouseBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HouseViewHolder {
        val binding = RowHouseBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HouseViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HouseViewHolder, position: Int) {
        val house = housesList[position]

        try {
            println("try")
            Glide.with(holder.itemView.context).load(house.mainImage)
                .into(holder.binding.imageHouse)

            holder.binding.textTitle.text = house.title
            holder.binding.textAddress.text = house.address
            holder.binding.textDistance.text = house.distance

            holder.itemView.setOnClickListener {
                val directions =
                    HomeFragmentDirections.actionNavigationHomeToHomeDetailsFragment(house)
                Navigation.findNavController(it).navigate(directions)
            }
        } catch (e: Exception) {
            // Hata durumunda bir işlem yapabilirsiniz
            println("error : "+e)
        }
    }

    override fun getItemCount(): Int {
        return housesList.size
    }
}
