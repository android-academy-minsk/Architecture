package com.cleanarchitecturesample.ui

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.cleanarchitecturesample.R
import com.cleanarchitecturesample.dependencyinjection.FabricObjects
import com.cleanarchitecturesample.dependencyinjection.FabricObjectsImpl
import com.cleanarchitecturesample.ui.models.LocationModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), MainContract.View {

    private lateinit var presenter: MainPresenter
    private val locationsAdapter = LocationsAdapter()
    private val fabric: FabricObjects = FabricObjectsImpl()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recycler.adapter = locationsAdapter

        presenter = MainPresenter(this,
                fabric.locationsRepository(),
                fabric.coroutineDispatcherProvider())

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