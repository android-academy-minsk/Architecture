package com.cleanarchitecturesample.ui

import com.cleanarchitecturesample.domain.CoroutineDispatcherProvider
import com.cleanarchitecturesample.domain.models.Location
import com.cleanarchitecturesample.domain.repository.LocationsRepository
import com.cleanarchitecturesample.ui.models.LocationModel
import io.mockk.mockk
import io.mockk.verify
import org.junit.Before
import org.junit.Test
import com.google.common.truth.Truth.assertThat
import io.mockk.every
import java.text.SimpleDateFormat

class MainPresenterTest {

    private lateinit var view: MainContract.View
    private lateinit var repository: LocationsRepository
    private lateinit var presenter: MainPresenter

    private val coroutineDispatcher = CoroutineDispatcherProvider(
            main = TestCoroutineDispatcher(),
            io = TestCoroutineDispatcher())

    private val testLocations = listOf(Location(TEST_LATITUDE, TEST_LONGITUDE, TEST_DATE))
    private val expectedModels = listOf(LocationModel(TEST_COORDINATES, TEST_DATE_STR))

    @Before
    fun init() {
        view = mockk(relaxUnitFun = true)
        repository = mockk(relaxUnitFun = true)
        presenter = MainPresenter(view, repository, coroutineDispatcher)
    }

    @Test
    fun `onCreate should load and show locations`() {
        every { repository.getSavedLocations() } returns testLocations

        presenter.onCreate()

        verify(exactly = 1) { view.showLocations(
                withArg { actualModels ->
                    assertThat(actualModels).isEqualTo(expectedModels)
                })
        }
    }

    @Test
    fun `newLocationClicked should create and show new location`() {
        every { repository.requestNewLocation() } returns testLocations

        presenter.newLocationClicked()

        verify(exactly = 1) { view.showLocations(
                withArg { actualModels ->
                    assertThat(actualModels).isEqualTo(expectedModels)
                })
        }
    }

    @Test
    fun `transformLocations should transform to models`() {
        val actualModels = presenter.transformLocations(testLocations)
        assertThat(actualModels).isEqualTo(expectedModels)
    }

    companion object {

        private const val TEST_COORDINATES = "40.742, -73.989"
        private const val TEST_LATITUDE = 40.741895
        private const val TEST_LONGITUDE = -73.989308
        private const val TEST_DATE_STR = "Jan 1, 2019 12:00:00 PM"

        private val TEST_DATE = SimpleDateFormat("MMM d, yyyy hh:mm:ss aaa").parse(TEST_DATE_STR)
    }
}