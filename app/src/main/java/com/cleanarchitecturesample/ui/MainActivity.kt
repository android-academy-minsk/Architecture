package com.cleanarchitecturesample.ui

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.cleanarchitecturesample.R
import com.cleanarchitecturesample.dependencyinjection.FabricObjects
import com.cleanarchitecturesample.dependencyinjection.FabricObjectsImpl
import com.cleanarchitecturesample.mappers.PresentationMapperImpl
import com.cleanarchitecturesample.models.LocationModel
import com.cleanarchitecturesample.ui.presenter.MainPresenter
import com.cleanarchitecturesample.ui.view.MainView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), MainView {


    private val presenter: MainPresenter
    private val locationsAdapter = LocationsAdapter()
    private val fabric: FabricObjects = FabricObjectsImpl()
    private val mapper = PresentationMapperImpl()


    init {
        // This would be done by a dependency injector in a complex App
        presenter = MainPresenter(this, mapper, fabric.interactor())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recycler.adapter = locationsAdapter

        newLocationBtn.setOnClickListener { presenter.newLocationClicked() }

        presenter.onCreate()
    }

    override fun onDestroy() {
        presenter.onDestroy()
        super.onDestroy()
    }

    override fun showLocations(locations: List<LocationModel>) {
        locationsAdapter.items = locations
    }
}