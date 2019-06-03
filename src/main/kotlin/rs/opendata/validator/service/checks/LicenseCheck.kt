package rs.opendata.validator.service.checks

import jodd.log.LoggerFactory
import rs.opendata.validator.model.Health
import rs.opendata.validator.service.ResourceEx

class LicenseCheck(rex: ResourceEx) : ResourceCheck(rex) {

    override fun check(): CheckedHealth {
        log.debug("check license: " + rex.resource.id)

        val license = rex.resource.license

        val health = when {
            license.isConformantAndRecommended -> Health.HIGH
            license.isConformant -> Health.MEDIUM
            else -> Health.LOW
        }

        return CheckedHealth(rex, "license", health)
    }

    companion object {
        private val log = LoggerFactory.getLogger(LicenseCheck::class.java)
    }

}
