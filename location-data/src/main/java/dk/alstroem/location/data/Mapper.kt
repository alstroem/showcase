package dk.alstroem.location.data

import dk.alstroem.location.domain.model.ListInfo
import dk.alstroem.location.domain.model.Location
import dk.alstroem.location.domain.model.LocationList
import dk.alstroem.rocketreserver.LocationListQuery

internal fun LocationListQuery.Locations.asDomain(): LocationList {
    return LocationList(
        info.asDomain(),
        results.mapNotNull { it?.asDomain() }
    )
}

internal fun LocationListQuery.Info.asDomain(): ListInfo {
    return ListInfo(
        prev,
        pages,
        next,
        count
    )
}

internal fun LocationListQuery.Result.asDomain(): Location {
    return Location(
        id ?: "",
        name ?: "",
        dimension ?: "",
        type ?: ""
    )
}
