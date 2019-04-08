package com.mvisample.ui

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.mvisample.R
import com.mvisample.presentation.models.LocationModel
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.view_location_item.*
import kotlinx.android.synthetic.main.view_location_item.view.*
import kotlin.properties.Delegates

class LocationsAdapter : RecyclerView.Adapter<LocationsAdapter.ViewHolder>() {

    var items: List<LocationModel> by Delegates.observable(emptyList()) { _, _, _ -> notifyDataSetChanged() }

    private val itemRemoveEventsSubject = PublishSubject.create<LocationModel>()
    val itemRemoveEvents: Observable<LocationModel> = itemRemoveEventsSubject.hide()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(parent.inflate(R.layout.view_location_item))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position]) {
            itemRemoveEventsSubject.onNext(it)
        }
    }

    override fun getItemCount(): Int = items.size

    class ViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView),
        LayoutContainer {

        fun bind(location: LocationModel, itemRemoveListener: (LocationModel) -> Unit) {
            with(location) {
                locationCoordinates.text = coordinates
                locationDate.text = date
            }
            containerView.remove.setOnClickListener {
                itemRemoveListener.invoke(location)
            }
        }
    }
}
