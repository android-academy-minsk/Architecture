package com.cleanarchitecturesample.ui.view

import com.cleanarchitecturesample.models.Location

interface MainView {

    fun showLocations(locations: List<Location>)

}