package com.cleanarchitecturesample.ui.presenter

import com.cleanarchitecturesample.mappers.PresentationMapper
import com.cleanarchitecturesample.ui.view.MainView
import com.domain.featurelocation.Interator
import com.domain.featurelocation.models.Location
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.launch
import org.jetbrains.anko.coroutines.experimental.bg


class MainPresenter(private var view: MainView?,
                    private val mapper: PresentationMapper<Location, com.cleanarchitecturesample.models.Location>,
                    private val interator: Interator) {

    fun onCreate() = launch(UI) {
        val locations = bg { interator.getLocations() }.await()
        view?.showLocations(mapper.transform(locations))
    }

    fun newLocationClicked() = launch(UI) {
        val locations = bg { interator.requestNewLocation() }.await()
        view?.showLocations(mapper.transform(locations))
    }

    fun onDestroy() {
        view = null
    }
}