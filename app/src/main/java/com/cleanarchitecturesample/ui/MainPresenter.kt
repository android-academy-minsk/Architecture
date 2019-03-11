package com.cleanarchitecturesample.ui

import com.cleanarchitecturesample.domain.CoroutineDispatcherProvider
import com.cleanarchitecturesample.ui.models.LocationModel
import com.cleanarchitecturesample.domain.models.Location
import com.cleanarchitecturesample.domain.repository.LocationsRepository
import kotlinx.coroutines.*

class MainPresenter(private val view: MainContract.View,
                    private val repository: LocationsRepository,
                    coroutineDispatcher: CoroutineDispatcherProvider) : MainContract.Presenter {

    private val job = Job()
    private val uiScope = CoroutineScope(coroutineDispatcher.main + job)
    private val ioScope = CoroutineScope(coroutineDispatcher.io + job)

    override fun onCreate() {
        uiScope.launch {
            //view.showLoading() // ui thread
            val locations = withContext(ioScope.coroutineContext) {
                // background thread
                repository.getSavedLocations()
            }

            view.showLocations(transformLocations(locations))// ui thread
        }
    }

    override fun newLocationClicked() {
        uiScope.launch {
            //view.showLoading() // ui thread
            val locations = withContext(ioScope.coroutineContext) {
                // background thread
                repository.requestNewLocation()
            }

            view.showLocations(transformLocations(locations))// ui thread
        }
    }

    fun transformLocations(locations: List<Location>): List<LocationModel> {
        return locations.map { location -> LocationModel(
                location.latitude.toPrettifiedString() + ", " + location.longitude.toPrettifiedString(),
                location.date.toPrettifiedString()) }
    }

    override fun onDestroy() {
        job.cancel()
    }
}