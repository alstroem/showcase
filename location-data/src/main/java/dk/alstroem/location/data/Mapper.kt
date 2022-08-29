package dk.alstroem.location.data

import dk.alstroem.location.domain.model.Location
import dk.alstroem.rocketreserver.LocationListQuery

internal fun LocationListQuery.Locations.asDomain(): List<Location> {
    return results.mapNotNull { it?.asDomain() }
}

internal fun LocationListQuery.Result.asDomain(): Location {
    return Location(
        id ?: "",
        name ?: "",
        dimension ?: "",
        type ?: ""
    )
}
