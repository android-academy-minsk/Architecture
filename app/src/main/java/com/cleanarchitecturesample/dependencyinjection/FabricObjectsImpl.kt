package com.cleanarchitecturesample.dependencyinjection

import com.cleanarchitecturesample.domain.CoroutineDispatcherProvider
import com.cleanarchitecturesample.domain.framework.FakeLocationSource
import com.cleanarchitecturesample.domain.framework.InMemoryLocationPersistenceSource
import com.cleanarchitecturesample.domain.datasource.DeviceLocationSource
import com.cleanarchitecturesample.domain.datasource.LocationPersistenceSource
import com.cleanarchitecturesample.domain.repository.LocationsRepositoryImpl
import com.cleanarchitecturesample.domain.repository.LocationsRepository

class FabricObjectsImpl : FabricObjects {

    private val locationsRepository: LocationsRepository = LocationsRepositoryImpl(inMemoryLocationPersistenceSource(), fakeLocationSource())

    override fun inMemoryLocationPersistenceSource(): LocationPersistenceSource {
        return InMemoryLocationPersistenceSource()
    }

    override fun fakeLocationSource(): DeviceLocationSource {
        return FakeLocationSource()
    }

    override fun locationsRepository(): LocationsRepository {
        return locationsRepository
    }

    override fun coroutineDispatcherProvider(): CoroutineDispatcherProvider {
        return CoroutineDispatcherProvider()
    }
}