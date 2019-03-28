package com.cleanarchitecturesample.ui

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.cleanarchitecturesample.App
import com.cleanarchitecturesample.R
import com.cleanarchitecturesample.dependencyinjection.FactoryObjects
import com.cleanarchitecturesample.models.LocationModel
import com.cleanarchitecturesample.ui.presenter.MainPresenter
import com.cleanarchitecturesample.ui.view.MainView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), MainView {


    private var factoryObjects: FactoryObjects? = null
    private var locationsAdapter: LocationsAdapter? = null
    private var presenter: MainPresenter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        newLocationBtn.setOnClickListener { presenter?.newLocationClicked() }
        if (savedInstanceState == null) {
            initData()
        }
        recycler.apply {
            adapter = locationsAdapter
        }

        presenter?.onCreate(this)
    }

    private fun initData() {
        factoryObjects = (application as? App)?.getFactory()
        locationsAdapter = factoryObjects?.provideLocationAdapter()
        presenter = factoryObjects?.provideMainPresenter()
    }

    override fun onDestroy() {
        presenter?.onDestroy()
        super.onDestroy()
    }

    override fun showLocations(locations: List<LocationModel>) {
        locationsAdapter?.items = locations
    }
}