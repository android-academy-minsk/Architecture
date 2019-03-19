package com.mvvmsample.ui.viewmodel

import androidx.annotation.VisibleForTesting
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.OnLifecycleEvent
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.mvvmsample.framework.CoroutineDispatcherProvider
import com.mvvmsample.location.DeviceLocationSource
import com.mvvmsample.location.LocationPersistenceSource
import com.mvvmsample.location.Location
import com.mvvmsample.mappers.PresentationMapper
import com.mvvmsample.model.LocationModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class LocationViewModel(
    private val mapper: PresentationMapper<Location, LocationModel>,
    private val locationSource: DeviceLocationSource,
    private val locationStore: LocationPersistenceSource,
    coroutineDispatcher: CoroutineDispatcherProvider
) : ViewModel(), LifecycleObserver {

    val locations: LiveData<List<LocationModel>>
        get() = Transformations.map(locationsSource, mapper::transform)

    private val locationsSource = MutableLiveData<List<Location>>()

    val loading: LiveData<Boolean>
        get() = loadingSource
    private val loadingSource = MutableLiveData<Boolean>()

    private val job = Job()
    private val uiScope = CoroutineScope(coroutineDispatcher.main + job)
    private val ioScope = CoroutineScope(coroutineDispatcher.io + job)

    @OnLifecycleEvent(value = Lifecycle.Event.ON_START)
    fun onStart() {
        getLocations()
    }

    override fun onCleared() {
        super.onCleared()
        job.cancel()
    }

    fun newLocationClicked() {
        requestLocation()
    }

    private fun getLocations() {
        uiScope.launch {
            loadingSource.value = true
            val task = async(ioScope.coroutineContext) {
                // background thread
                locationStore.getPersistedLocations()
            }
            locationsSource.value = task.await() // ui thread
            loadingSource.value = false
        }
    }

    @VisibleForTesting
    fun requestLocation() {
        uiScope.launch {
            loadingSource.value = true
            val task = async(ioScope.coroutineContext) {
                // background thread
                val location = locationSource.getDeviceLocation()
                locationStore.saveNewLocation(location)
                locationStore.getPersistedLocations()
            }
            locationsSource.value = task.await() // ui thread
            loadingSource.value = false
        }
    }
}