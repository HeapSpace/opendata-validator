package rs.opendata.validator.model

import rs.opendata.validator.model.FormatOpeness.*

enum class Format constructor(
        private val id: String,
        private val openess: FormatOpeness
) {

    JSON("json", OPEN),
    CSV("csv", OPEN),
    XML("xml", OPEN),
    XLSX("xlsx", OPEN),
    ODS("ods", OPEN),
    SHP("shp", OPEN),
    SHX("shx", OPEN),
    DBF("dbf", OPEN),
    KMZ("kmz", OPEN),
    KML("kml", OPEN),
    GPX("gpx", OPEN),

    TXT("txt", UNSTRUCTURED),
    ZIP("zip", UNSTRUCTURED),
    DOCX("docx", UNSTRUCTURED),
    HTML("html", UNSTRUCTURED),

    DOC("doc", CLOSED),
    XLS("xls", CLOSED),
    XLSM("xlsm", CLOSED),
    ;

    fun id(): String {
        return id
    }

}