package rs.opendata.validator.service.checks

import rs.opendata.validator.service.ResourceEx

abstract class ResourceCheck(val rex: ResourceEx) : Function<CheckedHealth> {

    abstract fun check(): CheckedHealth
}
