package rs.opendata.validator.model

import jodd.json.TypeJsonParser
import jodd.typeconverter.TypeConversionException

class FormatAdapter : TypeJsonParser<Format> {

    override fun parse(value: Any?): Format? {
        if (value == null) {
            return null
        }
        val string = value.toString()
        try {
            return Format
                    .values()
                    .first { format -> format.id().equals(string, ignoreCase = true) }
        } catch (nseex: NoSuchElementException) {
            throw TypeConversionException(nseex)
        }
    }

}