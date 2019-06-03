package rs.opendata.validator.service.checks

import rs.opendata.validator.service.ResourceEx

class Checks(private val rex: ResourceEx) {

    fun createAllChecks(): List<ResourceCheck> {
        return arrayListOf(
                OnlineCheck(rex),
                HttpsCheck(rex),
                LicenseCheck(rex),
                MachineReadCheck(rex)
        )
    }
}
