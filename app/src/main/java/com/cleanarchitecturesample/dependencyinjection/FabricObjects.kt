package com.cleanarchitecturesample.dependencyinjection

import com.data.datasource.DeviceLocationSource
import com.data.datasource.LocationPersistenceSource
import com.domain.featurelocation.Interator
import com.domain.featurelocation.repository.LocationsRepository

interface FabricObjects {

   fun inMemoryLocationPersistenceSource(): LocationPersistenceSource

   fun fakeLocationSource(): DeviceLocationSource

   fun locationsRepository(): LocationsRepository

   fun interactor(): Interator

}