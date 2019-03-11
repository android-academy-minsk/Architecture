package com.cleanarchitecturesample.domain.repository


import com.cleanarchitecturesample.domain.models.Location

interface LocationsRepository {

    fun getSavedLocations(): List<Location>

    fun requestNewLocation(): List<Location>

}