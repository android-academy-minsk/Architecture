package com.mvisample.presentation

import io.reactivex.Observable

interface LocationsView {

    fun intents(): Observable<LocationIntent>

    fun render(state: LocationViewState)
}
