package com.mvvmsample.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mvvmsample.R
import com.mvvmsample.model.LocationModel
import kotlinx.android.synthetic.main.view_location_item.view.*
import kotlin.properties.Delegates

class LocationsAdapter : RecyclerView.Adapter<LocationsAdapter.ViewHolder>() {

    var items: List<LocationModel> by Delegates.observable(emptyList()) { _, _, _ -> notifyDataSetChanged() }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        LayoutInflater.from(parent.context)
            .inflate(R.layout.view_location_item, parent, false)
            .let(::ViewHolder)

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.size

    class ViewHolder(containerView: View) : RecyclerView.ViewHolder(containerView) {

        fun bind(location: LocationModel) {
            with(location) {
                itemView.locationCoordinates.text = coordinates
                itemView.locationDate.text = date
            }
        }
    }
}
