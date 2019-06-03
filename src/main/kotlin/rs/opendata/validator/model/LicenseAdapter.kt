package rs.opendata.validator.model

import jodd.json.TypeJsonParser
import jodd.typeconverter.TypeConversionException

class LicenseAdapter : TypeJsonParser<License> {

    override fun parse(value: Any?): License? {
        if (value == null) {
            return null
        }
        val string = value.toString()

        if (string.isEmpty()) {
            return License.NA
        }
        try {
            return License
                    .values()
                    .first { license -> license.id().equals(string, ignoreCase = true) }
        } catch (nseex: NoSuchElementException) {
            throw TypeConversionException(nseex)
        }

    }
}
