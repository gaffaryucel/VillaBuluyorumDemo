package com.androiddevelopers.villabuluyorum.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.androiddevelopers.villabuluyorum.databinding.RowBestHouseBinding
import com.androiddevelopers.villabuluyorum.databinding.RowHouseBinding
import com.androiddevelopers.villabuluyorum.model.House
import com.bumptech.glide.Glide

class BestHouseAdapter : RecyclerView.Adapter<BestHouseAdapter.HouseViewHolder>() {

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

    inner class HouseViewHolder(val binding: RowBestHouseBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HouseViewHolder {
        val binding = RowBestHouseBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HouseViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HouseViewHolder, position: Int) {
        val house = housesList[position]

        try {
            Glide.with(holder.itemView.context).load(house.mainImage)
                .into(holder.binding.ivBestHouse)

            holder.binding.tvTitle.text = house.title
            holder.binding.tvPrice.text = "₺${house.rentPrice}/Ay"
            holder.binding.tvBedroom.text = "${house.bedrooms} Yatak odası"
            holder.binding.tvBathroom.text ="${house.bathrooms} Banyo"

            holder.itemView.setOnClickListener {

            }
        } catch (e: Exception) {
            // Hata durumunda bir işlem yapabilirsiniz
            println("error : $e")
        }
    }

    override fun getItemCount(): Int {
        return housesList.size
    }
}
