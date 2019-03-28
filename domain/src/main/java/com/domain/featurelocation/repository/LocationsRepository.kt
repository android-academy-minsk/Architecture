package com.domain.featurelocation.repository


import com.domain.featurelocation.entities.Location

interface LocationsRepository {

    fun getSavedLocations(): List<Location>

    fun getNewLocations(): List<Location>

    fun requestNewLocation(): List<Location>

}