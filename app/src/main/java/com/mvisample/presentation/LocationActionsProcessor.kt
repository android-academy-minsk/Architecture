package com.mvisample.presentation

import com.mvisample.domain.interactors.ClearLocationsUseCase
import com.mvisample.domain.interactors.GetLocationsUseCase
import com.mvisample.domain.interactors.RemoveLocationUseCase
import com.mvisample.domain.interactors.RequestNewLocationUseCase
import com.mvisample.presentation.LocationAction.*
import com.mvisample.presentation.LocationResult.*
import io.reactivex.Observable
import io.reactivex.ObservableTransformer

class LocationActionsProcessor(
    private val getLocationsUseCase: GetLocationsUseCase,
    private val requestNewLocationUseCase: RequestNewLocationUseCase,
    private val removeLocationUseCase: RemoveLocationUseCase,
    private val clearLocationsUseCase: ClearLocationsUseCase
) {

    val process: ObservableTransformer<LocationAction, LocationResult> =
        ObservableTransformer { actions ->
            actions.publish { shared ->
                Observable.merge(
                    shared.ofType(LoadAction::class.java)
                        .compose(loadActionProcessor),
                    shared.ofType(AddLocationAction::class.java)
                        .compose(addActionsProcessor),
                    shared.ofType(RemoveLocationAction::class.java)
                        .compose(removeActionsProcessor),
                    shared.ofType(ClearLocationsAction::class.java)
                        .compose(clearLocationsActionsProcessor)
                )
            }
        }

    private val loadActionProcessor: ObservableTransformer<LoadAction, LocationResult> =
        ObservableTransformer { loadActions ->
            loadActions.flatMap {
                getLocationsUseCase.getLocations()
                    .toObservable()
                    .map { LoadLocationsResult.Success(it) }
                    .cast(LocationResult::class.java)
                    .onErrorReturn(LoadLocationsResult::Failure)
                    .startWith(LoadLocationsResult.InProgress)
            }
        }

    private val addActionsProcessor: ObservableTransformer<AddLocationAction, LocationResult> =
        ObservableTransformer { addActions ->
            addActions.flatMap {
                requestNewLocationUseCase.requestLocation()
                    .toObservable()
                    .map { AddLocationResult.Success(it) }
                    .cast(LocationResult::class.java)
                    .onErrorReturn(AddLocationResult::Failure)
                    .startWith(AddLocationResult.InProgress)
            }
        }

    private val removeActionsProcessor: ObservableTransformer<RemoveLocationAction, LocationResult> =
        ObservableTransformer { removeActions ->
            removeActions.flatMap { action ->
                removeLocationUseCase.removeLocation(action.location)
                    .toObservable()
                    .map { RemoveLocationResult.Success(it) }
                    .cast(LocationResult::class.java)
                    .onErrorReturn(RemoveLocationResult::Failure)
                    .startWith(RemoveLocationResult.InProgress)
            }
        }

    private val clearLocationsActionsProcessor: ObservableTransformer<ClearLocationsAction, LocationResult> =
        ObservableTransformer { clearActions ->
            clearActions.flatMap {
                clearLocationsUseCase.clearLocations()
                    .toObservable()
                    .map { ClearLocationsResult.Success }
                    .cast(LocationResult::class.java)
                    .onErrorReturn(ClearLocationsResult::Failure)
                    .startWith(ClearLocationsResult.InProgress)
            }
        }
}
