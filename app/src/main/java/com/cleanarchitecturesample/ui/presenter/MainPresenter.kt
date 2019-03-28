package com.cleanarchitecturesample.ui.presenter

import com.cleanarchitecturesample.mappers.PresentationMapperImpl
import com.cleanarchitecturesample.ui.view.MainView
import com.domain.featurelocation.Interator
import kotlinx.coroutines.*


class MainPresenter(private val mapper: PresentationMapperImpl,
                    private val interator: Interator) {

    private var view: MainView? = null
    private val job = Job()
    private val mainScope = CoroutineScope(Dispatchers.Main + job)
    private val ioScope = CoroutineScope(Dispatchers.IO + job)

    fun onCreate(view: MainView?) {
        this.view = view
        getLocations()
    }

    fun newLocationClicked() {
        requestLocation()
    }

    private fun getLocations() {
        mainScope.launch {
            //view.showLoading() // ui thread
            val task = async(ioScope.coroutineContext) {
                // background thread
                interator.getNewLocations()
            }
            view?.showLocations(mapper.transform(task.await()))// ui thread
        }
    }

    private fun requestLocation() {
        mainScope.launch {
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