package rs.opendata.validator.service

import rs.opendata.validator.model.Database
import rs.opendata.validator.model.Score

class ResourcesValidator {

    /**
     * Creates new validator job and starts it.
     * In current implementation, this operation is synchronous and will
     * return the resulting scores right away.
     */
    fun validateDatabase(database: Database): Map<String, Score> {
        return ValidatorJob().validate(database)
    }
}
