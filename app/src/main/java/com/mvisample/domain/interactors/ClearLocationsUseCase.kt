package com.mvisample.domain.interactors

import com.mvisample.domain.SchedulersProvider
import com.mvisample.domain.repository.LocationsRepository
import io.reactivex.Single
import java.util.concurrent.TimeUnit

class ClearLocationsUseCase(
    private val locationsRepository: LocationsRepository,
    private val schedulersProvider: SchedulersProvider
) {

    fun clearLocations(): Single<Unit> {
        return Single.fromCallable {
            locationsRepository.clearLocations()
            Unit
        }
            .delay(200, TimeUnit.MILLISECONDS, schedulersProvider.background())
            .subscribeOn(schedulersProvider.background())
            .observeOn(schedulersProvider.ui())
    }
}
