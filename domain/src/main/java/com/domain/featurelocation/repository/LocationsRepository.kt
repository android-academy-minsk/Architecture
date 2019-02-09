package com.domain.featurelocation.repository


import com.domain.featurelocation.models.Location

interface LocationsRepository {

    fun getSavedLocations(): List<Location>

    fun requestNewLocation(): List<Location>

}