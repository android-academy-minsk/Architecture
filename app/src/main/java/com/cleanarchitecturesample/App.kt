package com.cleanarchitecturesample

import android.app.Application
import com.cleanarchitecturesample.dependencyinjection.FabricObjects
import com.cleanarchitecturesample.dependencyinjection.FabricObjectsImpl

class App : Application() {

    private val fabricObjects: FabricObjects = FabricObjectsImpl()


    fun getFabric() = fabricObjects
}