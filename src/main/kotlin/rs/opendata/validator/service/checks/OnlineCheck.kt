package rs.opendata.validator.service.checks

import jodd.log.LoggerFactory
import rs.opendata.validator.model.Health
import rs.opendata.validator.service.ResourceEx

class OnlineCheck(rex: ResourceEx) : ResourceCheck(rex) {

    override fun check(): CheckedHealth {
        log.debug("online " + rex.resource.id)

        val data = rex.download()
        val health = if (data.status >= 300) Health.LOW else Health.HIGH

        return CheckedHealth(rex, "online", health)
    }

    companion object {
        private val log = LoggerFactory.getLogger(OnlineCheck::class.java)
    }

}
