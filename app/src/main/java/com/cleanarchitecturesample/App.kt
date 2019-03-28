package com.cleanarchitecturesample

import android.app.Application
import com.cleanarchitecturesample.dependencyinjection.FactoryObjects
import com.cleanarchitecturesample.dependencyinjection.FactoryObjectsImpl

class App : Application() {

    private val factoryObjects: FactoryObjects = FactoryObjectsImpl()


    fun getFactory() = factoryObjects
}