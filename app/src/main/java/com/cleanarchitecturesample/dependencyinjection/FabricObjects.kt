package com.cleanarchitecturesample.dependencyinjection

import com.cleanarchitecturesample.ui.LocationsAdapter
import com.cleanarchitecturesample.ui.presenter.MainPresenter
import com.data.datasource.DeviceLocationSource
import com.data.datasource.LocationPersistenceSource
import com.domain.featurelocation.repository.LocationsRepository

interface FabricObjects {

   fun inMemoryLocationPersistenceSource(): LocationPersistenceSource

   fun fakeLocationSource(): DeviceLocationSource

   fun locationsRepository(): LocationsRepository

   fun provideMainPresenter(): MainPresenter

   fun provideLocationAdapter(): LocationsAdapter

}