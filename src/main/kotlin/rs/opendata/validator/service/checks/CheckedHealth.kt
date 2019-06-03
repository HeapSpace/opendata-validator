package rs.opendata.validator.service.checks

import rs.opendata.validator.model.Health
import rs.opendata.validator.service.ResourceEx

data class CheckedHealth(
        val rex: ResourceEx,
        val checkId: String,
        val health: Health
)
