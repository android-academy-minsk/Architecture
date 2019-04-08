package com.mvisample.presentation

import com.mvisample.presentation.LocationResult.*
import com.mvisample.presentation.models.toPresentationModel
import io.reactivex.functions.BiFunction

class LocationViewStateReducer {

    val reduce: BiFunction<LocationViewState, LocationResult, LocationViewState> =
        BiFunction { prevState, result ->
            when (result) {
                is LoadLocationsResult ->
                    when (result) {
                        is LoadLocationsResult.Success -> prevState.copy(
                            loading = false,
                            locations = result.locations.map { it.toPresentationModel() }
                        )
                        is LoadLocationsResult.Failure -> prevState.copy(
                            loading = false,
                            error = result.error
                        )
                        LoadLocationsResult.InProgress -> prevState.copy(
                            loading = true
                        )
                    }
                is AddLocationResult ->
                    when (result) {
                        is AddLocationResult.Success -> prevState.copy(
                            loading = false,
                            locations = result.locations.map { it.toPresentationModel() }
                        )
                        is AddLocationResult.Failure -> prevState.copy(
                            loading = false,
                            error = result.error
                        )
                        AddLocationResult.InProgress -> prevState.copy(
                            loading = true
                        )
                    }
                is RemoveLocationResult ->
                    when (result) {
                        is RemoveLocationResult.Success -> prevState.copy(
                            loading = false,
                            locations = result.locations.map { it.toPresentationModel() }
                        )
                        is RemoveLocationResult.Failure -> prevState.copy(
                            loading = false,
                            error = result.error
                        )
                        RemoveLocationResult.InProgress -> prevState.copy(
                            loading = true
                        )
                    }
                is ClearLocationsResult ->
                    when (result) {
                        ClearLocationsResult.Success -> prevState.copy(
                            loading = false,
                            locations = emptyList()
                        )
                        is ClearLocationsResult.Failure -> prevState.copy(
                            loading = false,
                            error = result.error
                        )
                        ClearLocationsResult.InProgress -> prevState.copy(
                            loading = true
                        )
                    }
            }
        }
}
