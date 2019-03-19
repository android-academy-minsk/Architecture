package com.mvvmsample.ui.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.mvvmsample.location.DeviceLocationSource
import com.mvvmsample.location.Location
import com.mvvmsample.location.LocationPersistenceSource
import com.mvvmsample.mappers.PresentationMapperImpl
import com.mvvmsample.model.LocationModel
import com.nhaarman.mockito_kotlin.argumentCaptor
import com.nhaarman.mockito_kotlin.doReturn
import com.nhaarman.mockito_kotlin.eq
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.times
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import java.util.Date

class LocationViewModelTest {

    /* Makes LiveData don't care about threads */
    @Rule
    @JvmField
    val rule = InstantTaskExecutorRule()

    private val mapper = PresentationMapperImpl() // use real mapper for simplicity
    private val source: DeviceLocationSource = mock()
    private val storage: LocationPersistenceSource = mock()
    private val viewModel = LocationViewModel(mapper, source, storage, TestCoroutineDispatcherProvider())

    @Test
    fun `requestLocation updates list of locations`() {
        val loadingCaptor = argumentCaptor<Boolean>()
        val loadingObserver: Observer<Boolean> = mock()
        viewModel.loading.observeForever(loadingObserver)

        val locationCaptor = argumentCaptor<List<LocationModel>>()
        val locationObserver: Observer<List<LocationModel>> = mock()
        viewModel.locations.observeForever(locationObserver)

        val loc1 = Location(55.toDouble(), 51.toDouble(), Date(200500L))
        val loc2 = Location(42.toDouble(), 5.toDouble(), Date(100500L))
        doReturn(loc2).whenever(source).getDeviceLocation()
        doReturn(listOf(loc1, loc2)).whenever(storage).getPersistedLocations()

        viewModel.requestLocation()

        verify(storage).saveNewLocation(eq(loc2))

        verify(loadingObserver, times(2)).onChanged(loadingCaptor.capture())
        assertEquals(listOf(true, false), loadingCaptor.allValues)

        verify(locationObserver).onChanged(locationCaptor.capture())
        assertEquals(listOf(
            mapper.transform(loc1),
            mapper.transform(loc2)
        ), locationCaptor.firstValue)
    }
}