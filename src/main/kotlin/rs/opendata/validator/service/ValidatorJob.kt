package rs.opendata.validator.service

import jodd.log.LoggerFactory
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.runBlocking
import rs.opendata.validator.model.Database
import rs.opendata.validator.model.Score
import rs.opendata.validator.service.checks.Checks

suspend fun <A, B> Iterable<A>.pmap(f: suspend (A) -> B): List<B> = coroutineScope {
    map { async { f(it) } }.map { it.await() }
}

class ValidatorJob {

    /**
     * Validate database.
     */
    fun validate(database: Database): Map<String, Score> {
        log.info("Validation started")

        return runBlocking {
            database
                    .resources
                    .map { ResourceEx(it) }
                    .map { Checks(it) }
                    .map { it.createAllChecks() }
                    .flatten()
                    .pmap { it.check() }
                    .groupBy(
                            { it.rex },
                            { it }
                    )
                    .mapValues { Score(it.key, it.value) }
                    .mapKeys { it.key.resource.id }
        }
    }

    companion object {
        private val log = LoggerFactory.getLogger(ValidatorJob::class.java)
    }

}
