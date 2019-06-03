package rs.opendata.validator.model

import java.time.LocalDateTime

data class Resource(
        var id: String,
        var url: String,
        var title: String,
        var description: String?,
        var format: Format,
        var license: License,
        var datasetId: String,
        var ownerId: String,
        var sourceId: String,
        var createdAt: LocalDateTime,
        var lastModified: LocalDateTime
) {
    constructor() : this("", "", "", null, Format.JSON, License.NA, "", "", "", LocalDateTime.now(), LocalDateTime.now())
}
