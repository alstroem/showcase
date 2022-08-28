package dk.alstroem.location.domain.model

data class LocationList(
    val info: ListInfo,
    val results: List<Location>
)
