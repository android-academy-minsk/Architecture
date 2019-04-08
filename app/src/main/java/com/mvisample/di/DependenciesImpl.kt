package com.mvisample.di

import com.mvisample.domain.SchedulersProvider
import com.mvisample.domain.framework.FakeLocationSource
import com.mvisample.domain.framework.InMemoryLocationPersistenceSource
import com.mvisample.domain.datasource.DeviceLocationSource
import com.mvisample.domain.datasource.LocationPersistenceSource
import com.mvisample.domain.interactors.ClearLocationsUseCase
import com.mvisample.domain.interactors.GetLocationsUseCase
import com.mvisample.domain.interactors.RemoveLocationUseCase
import com.mvisample.domain.interactors.RequestNewLocationUseCase
import com.mvisample.domain.repository.LocationsRepositoryImpl
import com.mvisample.domain.repository.LocationsRepository
import com.mvisample.presentation.LocationActionsProcessor
import com.mvisample.presentation.LocationViewStateReducer
import com.mvisample.presentation.mappers.IntentToActionMapper

class DependenciesImpl : Dependencies {

    private val locationsRepository: LocationsRepository =
        LocationsRepositoryImpl(inMemoryLocationPersistenceSource(), fakeLocationSource())

    private val schedulersProvider: SchedulersProvider = SchedulersProvider.Impl()

    override fun schedulersProvider(): SchedulersProvider {
        return schedulersProvider
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

    override fun intentToActionMapper(): IntentToActionMapper {
        return IntentToActionMapper()
    }

    override fun locationActionsProcessor(): LocationActionsProcessor {
        return LocationActionsProcessor(
            GetLocationsUseCase(locationsRepository, schedulersProvider()),
            RequestNewLocationUseCase(locationsRepository, schedulersProvider()),
            RemoveLocationUseCase(locationsRepository, schedulersProvider()),
            ClearLocationsUseCase(locationsRepository, schedulersProvider())
        )
    }

    override fun reducer(): LocationViewStateReducer {
        return LocationViewStateReducer()
    }
}
