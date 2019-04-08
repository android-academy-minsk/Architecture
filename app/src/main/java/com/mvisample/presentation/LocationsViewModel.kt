package com.mvisample.presentation

import android.arch.lifecycle.ViewModel
import com.mvisample.presentation.mappers.IntentToActionMapper
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject

class LocationsViewModel(
    private val intentToActionMapper: IntentToActionMapper,
    private val locationActionsProcessor: LocationActionsProcessor,
    private val reducer: LocationViewStateReducer
) : ViewModel() {

    private val intentsSubject = PublishSubject.create<LocationIntent>()
    private val statesOutput: Observable<LocationViewState> = compose()

    fun processIntents(intents: Observable<LocationIntent>) {
        intents.subscribe(intentsSubject)
    }

    fun viewStates(): Observable<LocationViewState> = statesOutput

    private fun compose(): Observable<LocationViewState> =
        intentsSubject
            .map { intentToActionMapper.map(it) }
            .compose(locationActionsProcessor.process)
            .scan<LocationViewState>(LocationViewState.idle(), reducer.reduce)
            .replay(1)
            .autoConnect(0)
}
