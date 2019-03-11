package com.cleanarchitecturesample.ui

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.cleanarchitecturesample.App
import com.cleanarchitecturesample.R
import com.cleanarchitecturesample.dependencyinjection.FabricObjects
import com.cleanarchitecturesample.models.LocationModel
import com.cleanarchitecturesample.ui.presenter.MainPresenter
import com.cleanarchitecturesample.ui.view.MainView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), MainView {


    private var fabricObjects: FabricObjects? = null
    private var locationsAdapter: LocationsAdapter? = null
    private var presenter: MainPresenter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recycler.adapter = locationsAdapter

        newLocationBtn.setOnClickListener { presenter?.newLocationClicked() }
        if (savedInstanceState == null) {
            initData()
        }

        presenter?.onCreate(this)
    }

    private fun initData() {
        fabricObjects = (application as? App)?.getFabric()
        locationsAdapter = fabricObjects?.provideLocationAdapter()
        presenter = fabricObjects?.provideMainPresenter()
    }

    override fun onDestroy() {
        presenter?.onDestroy()
        super.onDestroy()
    }

    override fun showLocations(locations: List<LocationModel>) {
        locationsAdapter?.items = locations
    }
}