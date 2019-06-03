package rs.opendata.validator.service.checks

import jodd.log.LoggerFactory
import rs.opendata.validator.model.Health
import rs.opendata.validator.service.ResourceEx

class HttpsCheck(rex: ResourceEx) : ResourceCheck(rex) {

    override fun check(): CheckedHealth {
        log.debug("check https: " + rex.resource.id)

        var url = rex.resource.url

        if (!url.startsWith("https:")) {
            val dotIndex = url.indexOf(':')
            url = "https" + url.substring(dotIndex)
        }

        val data = rex.download(url)

        val health = if (data.status >= 300) Health.MEDIUM else Health.HIGH

        return CheckedHealth(rex, "https", health)
    }

    companion object {
        private val log = LoggerFactory.getLogger(HttpsCheck::class.java)
    }
}
