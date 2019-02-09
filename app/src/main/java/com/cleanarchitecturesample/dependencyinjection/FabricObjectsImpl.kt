package com.cleanarchitecturesample.dependencyinjection

import com.cleanarchitecturesample.framework.FakeLocationSource
import com.cleanarchitecturesample.framework.InMemoryLocationPersistenceSource
import com.data.datasource.DeviceLocationSource
import com.data.datasource.LocationPersistenceSource
import com.data.featurelocation.LocationsRepositoryImpl
import com.domain.featurelocation.InteractorImpl
import com.domain.featurelocation.Interator
import com.domain.featurelocation.repository.LocationsRepository
import com.domain.featurelocation.usecases.GetLocationsUseCase
import com.domain.featurelocation.usecases.RequestNewLocationUseCase

class FabricObjectsImpl : FabricObjects {

    private val locationsRepository: LocationsRepository = LocationsRepositoryImpl(inMemoryLocationPersistenceSource(), fakeLocationSource())

    override fun interactor(): Interator {
        return InteractorImpl(GetLocationsUseCase(locationsRepository), RequestNewLocationUseCase(locationsRepository))
    }

    override fun inMemoryLocationPersistenceSource(): LocationPersistenceSource {
        return InMemoryLocationPersistenceSource()
    }

    override fun fakeLocationSource(): DeviceLocationSource {
        return FakeLocationSource()
    }

    override fun locationsRepository(): LocationsRepository {
        return locationsRepository
    }
}