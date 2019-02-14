package com.cleanarchitecturesample.mappers

import com.cleanarchitecturesample.models.LocationModel
import com.cleanarchitecturesample.ui.toPrettifiedString
import com.domain.featurelocation.models.Location

class PresentationMapperImpl : PresentationMapper<Location, LocationModel> {

    override fun transform(entity: Location): LocationModel {
        return LocationModel(entity.latitude.toPrettifiedString(), entity.date.toPrettifiedString())
    }

}
