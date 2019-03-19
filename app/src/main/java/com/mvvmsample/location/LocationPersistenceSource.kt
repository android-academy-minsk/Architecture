package com.mvvmsample.location

import androidx.annotation.WorkerThread

interface LocationPersistenceSource {

    @WorkerThread
    fun getPersistedLocations(): List<Location>

    @WorkerThread
    fun saveNewLocation(location: Location)

}