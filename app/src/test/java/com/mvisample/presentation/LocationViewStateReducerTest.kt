package com.mvisample.presentation

import io.reactivex.subjects.PublishSubject
import org.junit.Before
import org.junit.Test

class LocationViewStateReducerTest {

    private lateinit var sut: LocationViewStateReducer

    private val resultsSubject = PublishSubject.create<LocationResult>()

    @Before
    fun beforeEachTest() {
        sut = LocationViewStateReducer()
    }

    @Test
    fun `reduce should handle LoadLocationsResult InProgress with loading state`() {
        val idleState = LocationViewState.idle()
        val progressState = LocationViewState(loading = true)

        val testObserver = resultsSubject.scan(idleState, sut.reduce).test()

        resultsSubject.onNext(LocationResult.LoadLocationsResult.InProgress)

        testObserver.assertValues(
            idleState,
            progressState
        )
    }
}
