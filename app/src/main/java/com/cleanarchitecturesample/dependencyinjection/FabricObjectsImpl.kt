package com.cleanarchitecturesample.dependencyinjection

import com.cleanarchitecturesample.framework.FakeLocationSource
import com.cleanarchitecturesample.framework.InMemoryLocationPersistenceSource
import com.cleanarchitecturesample.mappers.PresentationMapperImpl
import com.cleanarchitecturesample.ui.LocationsAdapter
import com.cleanarchitecturesample.ui.presenter.MainPresenter
import com.data.datasource.DeviceLocationSource
import com.data.datasource.LocationPersistenceSource
import com.data.featurelocation.LocationsRepositoryImpl
import com.domain.featurelocation.InteractorImpl
import com.domain.featurelocation.Interator
import com.domain.featurelocation.repository.LocationsRepository
import com.domain.featurelocation.usecases.GetLocationsUseCase
import com.domain.featurelocation.usecases.RequestNewLocationUseCase

class FabricObjectsImpl : FabricObjects {

    private val locationsRepository: LocationsRepository by lazy { LocationsRepositoryImpl(inMemoryLocationPersistenceSource(), fakeLocationSource()) }
    private val locationsAdapter by lazy { LocationsAdapter() }
    private val mapper by lazy { PresentationMapperImpl() }
    private val interactor: Interator by lazy { InteractorImpl(GetLocationsUseCase(locationsRepository), RequestNewLocationUseCase(locationsRepository)) }


    override fun inMemoryLocationPersistenceSource(): LocationPersistenceSource {
        return InMemoryLocationPersistenceSource()
    }

    override fun fakeLocationSource(): DeviceLocationSource {
        return FakeLocationSource()
    }

    override fun locationsRepository(): LocationsRepository {
        return locationsRepository
    }

    override fun provideMainPresenter(): MainPresenter {
        return MainPresenter(mapper, interactor)
    }

    override fun provideLocationAdapter(): LocationsAdapter {
        return locationsAdapter
    }


}