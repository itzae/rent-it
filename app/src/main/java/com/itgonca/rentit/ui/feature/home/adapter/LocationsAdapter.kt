package com.itgonca.rentit.ui.feature.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.itgonca.rentit.data.remote.model.Location
import com.itgonca.rentit.databinding.ItemLocationLayoutBinding
import com.itgonca.rentit.utils.extension.loadImageUrl

class LocationsAdapter :
    ListAdapter<Location, LocationsAdapter.LocationsViewHolder>(DiffUtilLocationCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LocationsViewHolder {
        val view =
            ItemLocationLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return LocationsViewHolder(view)
    }

    override fun onBindViewHolder(holder: LocationsViewHolder, position: Int) {
        val location = getItem(position)
        holder.bind(location)
    }

    inner class LocationsViewHolder(val binding: ItemLocationLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(itemLocation: Location) {
            with(itemLocation) {
                binding.ivImageLocation.loadImageUrl(image)
                binding.tvNameLocation.text = name
                binding.tvPrice.text = "$$price"
            }
        }
    }
}

private class DiffUtilLocationCallback : DiffUtil.ItemCallback<Location>() {
    override fun areItemsTheSame(oldItem: Location, newItem: Location): Boolean =
        oldItem.name == newItem.name


    override fun areContentsTheSame(oldItem: Location, newItem: Location): Boolean =
        oldItem == newItem
}