package com.cleanarchitecturesample.ui.view

import com.cleanarchitecturesample.models.LocationModel

interface MainView {

    fun showLocations(locations: List<LocationModel>)

}