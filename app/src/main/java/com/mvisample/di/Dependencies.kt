package com.mvisample.di

import com.mvisample.domain.SchedulersProvider
import com.mvisample.domain.datasource.DeviceLocationSource
import com.mvisample.domain.datasource.LocationPersistenceSource
import com.mvisample.domain.repository.LocationsRepository
import com.mvisample.presentation.LocationActionsProcessor
import com.mvisample.presentation.LocationViewStateReducer
import com.mvisample.presentation.mappers.IntentToActionMapper

interface Dependencies {

    fun schedulersProvider(): SchedulersProvider

    fun inMemoryLocationPersistenceSource(): LocationPersistenceSource

    fun fakeLocationSource(): DeviceLocationSource

    fun locationsRepository(): LocationsRepository

    fun intentToActionMapper(): IntentToActionMapper

    fun locationActionsProcessor(): LocationActionsProcessor

    fun reducer(): LocationViewStateReducer
}
