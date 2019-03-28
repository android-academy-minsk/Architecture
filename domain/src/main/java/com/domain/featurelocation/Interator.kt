package com.domain.featurelocation

import com.domain.featurelocation.entities.Location

interface Interator {

    fun getLocations(): List<Location>

    fun getNewLocations(): List<Location>

    fun requestNewLocation(): List<Location>

}