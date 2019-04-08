package com.mvisample.domain.interactors

import com.mvisample.domain.SchedulersProvider
import com.mvisample.domain.models.Location
import com.mvisample.domain.repository.LocationsRepository
import io.reactivex.Single
import java.util.concurrent.TimeUnit
import kotlin.random.Random

class RequestNewLocationUseCase(
    private val locationsRepository: LocationsRepository,
    private val schedulersProvider: SchedulersProvider
) {

    private val random = Random.Default

    fun requestLocation(): Single<List<Location>> {
        return Single.fromCallable {
            locationsRepository.requestNewLocation()
        }
            .delay(
                random.nextLong(from = 300, until = 700),
                TimeUnit.MILLISECONDS,
                schedulersProvider.background()
            )
            .subscribeOn(schedulersProvider.background())
            .observeOn(schedulersProvider.ui())
    }
}
