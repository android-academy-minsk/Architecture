package com.cleanarchitecturesample.ui.presenter

import com.cleanarchitecturesample.mappers.PresentationMapperImpl
import com.cleanarchitecturesample.ui.view.MainView
import com.domain.featurelocation.Interator
import kotlinx.coroutines.*


class MainPresenter(private var view: MainView?,
                    private val mapper: PresentationMapperImpl,
                    private val interator: Interator) {

    private val job = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + job)
    private val ioScope = CoroutineScope(Dispatchers.IO + job)

    fun onCreate() {
        getLocations()
    }

    fun newLocationClicked() {
        requestLocation()
    }

    private fun getLocations() {
        uiScope.launch {
            //view.showLoading() // ui thread
            val task = async(ioScope.coroutineContext) {
                // background thread
                interator.getLocations()
            }
            view?.showLocations(mapper.transform(task.await()))// ui thread
        }
    }

    private fun requestLocation() {
        uiScope.launch {
            //view.showLoading() // ui thread
            val task = async(ioScope.coroutineContext) {
                // background thread
                interator.requestNewLocation()
            }
            view?.showLocations(mapper.transform(task.await()))// ui thread
        }
    }

    fun onDestroy() {
        job.cancel()
        view = null
    }
}