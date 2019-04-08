package com.mvisample.ui

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import com.jakewharton.rxbinding2.view.clicks
import com.mvisample.R
import com.mvisample.di.Dependencies
import com.mvisample.di.DependenciesImpl
import com.mvisample.presentation.LocationIntent
import com.mvisample.presentation.LocationViewState
import com.mvisample.presentation.LocationsView
import com.mvisample.presentation.LocationsViewModel
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.subjects.PublishSubject
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), LocationsView {

    private val dependencies: Dependencies = DependenciesImpl()

    private val subscriptions = CompositeDisposable()
    private val clearLocationsEventsSubject = PublishSubject.create<Unit>()

    private val locationsAdapter = LocationsAdapter()

    private lateinit var viewModel: LocationsViewModel

    override fun intents(): Observable<LocationIntent> {
        return Observable.merge(
            initialIntent(),
            addLocationIntent(),
            removeLocationIntent(),
            clearLocationsIntent()
        )
    }

    private fun initialIntent() = Observable.just(LocationIntent.InitialIntent)

    private fun addLocationIntent() =
        newLocationBtn.clicks().map { LocationIntent.AddLocationIntent }

    private fun removeLocationIntent() =
        locationsAdapter.itemRemoveEvents.map { LocationIntent.RemoveLocationIntent(it) }

    private fun clearLocationsIntent() =
        clearLocationsEventsSubject.map { LocationIntent.ClearLocationsIntent }

    override fun render(state: LocationViewState) {
        if (state.loading) {
            progressView.visibility = View.VISIBLE
            newLocationBtn.visibility = View.GONE
        } else {
            progressView.visibility = View.GONE
            newLocationBtn.visibility = View.VISIBLE
        }

        locationsAdapter.items = state.locations

        state.error?.let {
            Toast.makeText(this, it.message, Toast.LENGTH_SHORT).show()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        injectDependencies()

        initViews()
    }

    override fun onStart() {
        super.onStart()

        viewModel.processIntents(intents())
        viewModel.viewStates().subscribe {
            render(it)
        }
            .addTo(subscriptions)
    }

    override fun onDestroy() {
        super.onDestroy()

        subscriptions.clear()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when (item?.itemId) {
            R.id.clear -> {
                clearLocations()
                true
            }
            else -> {
                super.onOptionsItemSelected(item)
            }
        }
    }

    private fun injectDependencies() {
        viewModel = ViewModelProviders.of(
            this,
            ViewModelFactory(dependencies)
        )[LocationsViewModel::class.java]
    }

    private fun initViews() {
        recycler.adapter = locationsAdapter
    }

    private fun clearLocations() {
        clearLocationsEventsSubject.onNext(Unit)
    }
}
