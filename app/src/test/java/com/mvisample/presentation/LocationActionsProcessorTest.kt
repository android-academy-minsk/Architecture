package com.mvisample.presentation

import com.mvisample.domain.interactors.ClearLocationsUseCase
import com.mvisample.domain.interactors.GetLocationsUseCase
import com.mvisample.domain.interactors.RemoveLocationUseCase
import com.mvisample.domain.interactors.RequestNewLocationUseCase
import com.mvisample.domain.models.Location
import com.nhaarman.mockito_kotlin.doReturn
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Single
import io.reactivex.subjects.PublishSubject
import org.junit.Before
import org.junit.Test

class LocationActionsProcessorTest {

    private lateinit var getLocationsUseCase: GetLocationsUseCase
    private lateinit var requestNewLocationUseCase: RequestNewLocationUseCase
    private lateinit var removeLocationUseCase: RemoveLocationUseCase
    private lateinit var clearLocationsUseCase: ClearLocationsUseCase

    private lateinit var actionsProcessor: LocationActionsProcessor

    private val actionsSubject = PublishSubject.create<LocationAction>()

    @Before
    fun beforeEachTest() {
        getLocationsUseCase = mock()
        requestNewLocationUseCase = mock()
        removeLocationUseCase = mock()
        clearLocationsUseCase = mock()

        actionsProcessor = LocationActionsProcessor(
            getLocationsUseCase,
            requestNewLocationUseCase,
            removeLocationUseCase,
            clearLocationsUseCase
        )
    }

    @Test
    fun `process should handle LoadAction with InProgress on start and then with Success`() {
        val expectedLocations = mock<List<Location>>()
        whenever(getLocationsUseCase.getLocations())
            .thenReturn(Single.just(expectedLocations))

        val testObserver = actionsSubject.compose(actionsProcessor.process).test()

        actionsSubject.onNext(LocationAction.LoadAction)

        testObserver.assertValues(
            LocationResult.LoadLocationsResult.InProgress,
            LocationResult.LoadLocationsResult.Success(expectedLocations)
        )
    }
}
