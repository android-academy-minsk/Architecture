package com.cleanarchitecturesample.mappers

import com.cleanarchitecturesample.models.Location
import com.cleanarchitecturesample.ui.toPrettifiedString

class PresentationMapperImpl : PresentationMapper<com.domain.featurelocation.models.Location, Location> {

    override fun transform(entity: com.domain.featurelocation.models.Location): Location {
        return Location(entity.latitude.toPrettifiedString(), entity.date.toPrettifiedString())
    }
}
