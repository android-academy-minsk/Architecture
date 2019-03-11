package com.cleanarchitecturesample.dependencyinjection

import com.cleanarchitecturesample.domain.CoroutineDispatcherProvider
import com.cleanarchitecturesample.domain.datasource.DeviceLocationSource
import com.cleanarchitecturesample.domain.datasource.LocationPersistenceSource
import com.cleanarchitecturesample.domain.repository.LocationsRepository

interface FabricObjects {

   fun inMemoryLocationPersistenceSource(): LocationPersistenceSource

   fun fakeLocationSource(): DeviceLocationSource

   fun locationsRepository(): LocationsRepository

   fun coroutineDispatcherProvider(): CoroutineDispatcherProvider
}