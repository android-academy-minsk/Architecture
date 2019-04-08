package com.mvisample.domain.interactors

import com.mvisample.domain.SchedulersProvider
import com.mvisample.domain.models.Location
import com.mvisample.domain.repository.LocationsRepository
import io.reactivex.Single
import java.util.concurrent.TimeUnit

class GetLocationsUseCase(
    private val locationsRepository: LocationsRepository,
    private val schedulersProvider: SchedulersProvider
) {

    fun getLocations(): Single<List<Location>> {
        return Single.fromCallable {
            locationsRepository.getSavedLocations()
        }
            .delay(1, TimeUnit.SECONDS, schedulersProvider.background())
            .subscribeOn(schedulersProvider.background())
            .observeOn(schedulersProvider.ui())
    }
}
