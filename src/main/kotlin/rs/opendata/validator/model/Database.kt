package rs.opendata.validator.model

data class Database(
        var resources: List<Resource>
) {
    constructor() : this(listOf<Resource>())
}