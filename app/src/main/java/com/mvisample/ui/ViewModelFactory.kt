package com.mvisample.ui

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.mvisample.di.Dependencies
import com.mvisample.presentation.LocationsViewModel

class ViewModelFactory(
    private val dependencies: Dependencies
) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass == LocationsViewModel::class.java) {
            return LocationsViewModel(
                dependencies.intentToActionMapper(),
                dependencies.locationActionsProcessor(),
                dependencies.reducer()
            ) as T
        } else {
            throw IllegalArgumentException("unknown ViewModel class")
        }
    }
}
