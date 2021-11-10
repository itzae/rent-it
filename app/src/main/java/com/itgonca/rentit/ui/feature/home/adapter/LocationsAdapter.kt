package com.itgonca.rentit.ui.feature.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.itgonca.rentit.data.remote.model.Location
import com.itgonca.rentit.databinding.ItemLocationLayoutBinding
import com.itgonca.rentit.utils.extension.loadImageUrl

class LocationsAdapter(val onFavoriteClick: (Int, Boolean) -> Unit) :
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

    override fun onViewRecycled(holder: LocationsViewHolder) {
        super.onViewRecycled(holder)
        holder.unbind()
    }

    inner class LocationsViewHolder(private val binding: ItemLocationLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(itemLocation: Location) {
            with(itemLocation) {
                checkboxCurrentState = favorite
                binding.ivImageLocation.loadImageUrl(image)
                binding.tvNameLocation.text = name
                binding.tvPrice.text = "$$price"
                binding.btnFavorite.isChecked = checkboxCurrentState
                binding.btnFavorite.setOnCheckedChangeListener { _, isChecked ->
                    checkboxCurrentState = isChecked
                    onFavoriteClick(id, checkboxCurrentState)
                }
            }
        }

        fun unbind() {
            binding.btnFavorite.setOnCheckedChangeListener(null)
            binding.btnFavorite.isChecked = false
        }
    }
}

private class DiffUtilLocationCallback : DiffUtil.ItemCallback<Location>() {
    override fun areItemsTheSame(oldItem: Location, newItem: Location): Boolean =
        oldItem.id == newItem.id


    override fun areContentsTheSame(oldItem: Location, newItem: Location): Boolean =
        oldItem == newItem
}