package com.cleanarchitecturesample.ui

import com.cleanarchitecturesample.ui.core.BasePresenter
import com.cleanarchitecturesample.ui.core.BaseView
import com.cleanarchitecturesample.ui.models.LocationModel

class MainContract {

    interface View : BaseView {
        fun showLocations(locations: List<LocationModel>)
    }

    interface Presenter : BasePresenter {
        fun newLocationClicked()
    }
}