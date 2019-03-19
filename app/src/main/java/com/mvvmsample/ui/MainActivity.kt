package com.mvvmsample.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.mvvmsample.R
import com.mvvmsample.framework.DefaultCoroutineDispatcherProvider
import com.mvvmsample.framework.FakeLocationSource
import com.mvvmsample.framework.InMemoryLocationPersistenceSource
import com.mvvmsample.mappers.PresentationMapperImpl
import com.mvvmsample.ui.viewmodel.LocationViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: LocationViewModel

    private val viewModelFactory = object : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return when (modelClass) {
                LocationViewModel::class.java -> LocationViewModel(
                    PresentationMapperImpl(),
                    FakeLocationSource(),
                    InMemoryLocationPersistenceSource(),
                    DefaultCoroutineDispatcherProvider()
                )

                else -> throw IllegalArgumentException("Unknown view model class $modelClass")
            } as T
        }
    }

    private val locationsAdapter = LocationsAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProviders.of(this, viewModelFactory)
            .get(LocationViewModel::class.java)

        recycler.adapter = locationsAdapter
        newLocationBtn.setOnClickListener { viewModel.newLocationClicked() }

        viewModel.locations.observe(this, Observer { locationsAdapter.items = it })
        viewModel.loading.observe(this, Observer {
            val index = if (it) 1 else 0
            buttonAndProgress.displayedChild = index
            buttonAndProgress.getChildAt(index)
                .apply { alpha = 0F }
                .animate()
                .setDuration(300)
                .alpha(1F)
                .start()
        })

        lifecycle.addObserver(viewModel)
    }
}