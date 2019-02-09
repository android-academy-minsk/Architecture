package com.domain.featurelocation

import com.domain.featurelocation.models.Location

interface Interator {

    fun getLocations(): List<Location>

    fun requestNewLocation(): List<Location>

}