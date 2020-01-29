package rs.opendata.validator.service.checks

import gov.nasa.worldwind.ogc.kml.KMLRoot
import gov.nasa.worldwind.ogc.kml.io.KMLInputStream
import gov.nasa.worldwind.ogc.kml.io.KMZInputStream
import io.jenetics.jpx.GPX
import jodd.io.FileUtil
import jodd.json.JsonParser
import jodd.log.LoggerFactory
import jodd.util.CsvUtil
import rs.opendata.validator.model.Format
import rs.opendata.validator.model.Health
import rs.opendata.validator.service.ResourceEx
import rs.opendata.validator.xml.ValidatingLagartoDomBuilder
import java.io.FileInputStream

class MachineReadCheck(rex: ResourceEx) : ResourceCheck(rex) {

    override fun check(): CheckedHealth {
        log.debug("mread: " + rex.resource.id)

        val health = when (rex.resource.format) {
            Format.JSON -> runOrFail { testJson() }
            Format.CSV -> runOrFail { testCsv() }
            Format.XML -> runOrFail { testXml() }
            Format.KMZ -> runOrFail { testKMZ() }
            Format.KML -> runOrFail { testKML() }
            Format.GPX -> runOrFail { testGPX() }
            else -> Health.LOW
        }

        return CheckedHealth(rex, "mread", health)
    }

    private fun runOrFail(test: () -> Health): Health {
        return try {
            test()
        } catch (e: Exception) {
            Health.LOW
        }
    }

    private fun testJson(): Health {
        val data = rex.download()
        if (data.status >= 300) {
            return Health.LOW
        }
        val content = FileUtil.readString(data.file)

        JsonParser.create().parse<String>(content)

        return Health.HIGH
    }

    private fun testCsv(): Health {
        val data = rex.download()
        if (data.status >= 300) {
            return Health.LOW
        }
        val content = FileUtil.readString(data.file)

        CsvUtil.toStringArray(content)

        return Health.HIGH
    }

    private fun testXml(): Health {
        val data = rex.download()
        if (data.status >= 300) {
            return Health.LOW
        }
        val content = FileUtil.readString(data.file)

        val parser = ValidatingLagartoDomBuilder().enableXmlMode()

        val dom = parser.parse(content)
        if (dom.errors.size > 0) {
            return Health.LOW
        }

        return Health.HIGH
    }

    private fun testKMZ(): Health {
        val data = rex.download()
        if (data.status >= 300) {
            return Health.LOW
        }
        val inputStream = FileInputStream(data.file)
        val kmlDoc = KMZInputStream(inputStream)
        val kmlRoot = KMLRoot(kmlDoc);

        return try {
            kmlRoot.parse()
            Health.HIGH
        } catch (e: java.lang.Exception) {
            Health.LOW
        }
    }

    private fun testKML(): Health {
        val data = rex.download()
        if (data.status >= 300) {
            return Health.LOW
        }
        val inputStream = FileInputStream(data.file)
        val kmlDoc = KMLInputStream(inputStream, null)
        val kmlRoot = KMLRoot(kmlDoc);

        return try {
            kmlRoot.parse()
            Health.HIGH
        } catch (e: java.lang.Exception) {
            Health.LOW
        }
    }

    private fun testGPX(): Health {
        val data = rex.download()
        if (data.status >= 300) {
            return Health.LOW
        }
        return try {
            GPX.read(FileInputStream(data.file)).tracks();
            Health.HIGH
        } catch (e: java.lang.Exception) {
            Health.LOW
        }
    }

    companion object {
        private val log = LoggerFactory.getLogger(MachineReadCheck::class.java)
    }

}
