package com.mvisample.domain.interactors

import com.mvisample.domain.SchedulersProvider
import com.mvisample.domain.models.Location
import com.mvisample.domain.repository.LocationsRepository
import io.reactivex.Single
import java.util.concurrent.TimeUnit

class RemoveLocationUseCase(
    private val locationsRepository: LocationsRepository,
    private val schedulersProvider: SchedulersProvider
) {

    fun removeLocation(location: Location): Single<List<Location>> {
        return Single.fromCallable {
            locationsRepository.removeLocation(location)
            locationsRepository.getSavedLocations()
        }
            .delay(300, TimeUnit.MILLISECONDS, schedulersProvider.background())
            .subscribeOn(schedulersProvider.background())
            .observeOn(schedulersProvider.ui())
    }
}
