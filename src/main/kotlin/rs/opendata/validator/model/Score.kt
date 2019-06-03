package rs.opendata.validator.model

import rs.opendata.validator.service.ResourceEx
import rs.opendata.validator.service.checks.CheckedHealth
import java.time.LocalDateTime

/**
 * Resource scores.
 */
@Suppress("MemberVisibilityCanBePrivate")
class Score {

    constructor(rex: ResourceEx, checkedHealthList: List<CheckedHealth>) {
        checkedHealthList.forEach { addHealth(it) }

        this.size = rex.download().size
        this.time = rex.download().downloadTime
    }

    val scores = linkedMapOf<String, Health>()
    val date = LocalDateTime.now()
    var health: Health = Health.HIGH
    val size: Int
    val time: Long

    /**
     * Updates the health if new health value is lower to existing one.
     */
    private fun addHealth(checkedHealth: CheckedHealth) {
        if (checkedHealth.health.lessThen(this.health)) {
            this.health = checkedHealth.health
        }
        scores[checkedHealth.checkId] = checkedHealth.health
    }

}
