package rs.opendata.validator.model

import rs.opendata.validator.model.LicenseConformation.*

enum class License constructor(
        private val id: String,
        private val fullName: String,
        private val conformation: LicenseConformation) {

    NA("N/A", "Not available", NON_CONFORMANT),

    PUBLIC_DOMAIN("PD", "Public domain", CONFORMANT),

    CC0("CC0", "Creative Commons CCZero", RECOMMENDED),
    PDDL("PDDL", "Open Data Commons Public Domain Dedication and Licence", RECOMMENDED),
    CC_BY_40("CC-BY-4.0", "Creative Commons Attribution 4.0", RECOMMENDED),
    ODC_BY("ODC-BY", "Open Data Commons Attribution License", RECOMMENDED),
    CC_BY_SA_40("CC-BY-SA-4.0", "Creative Commons Attribution Share-Alike 4.0", RECOMMENDED),
    ODBL("ODbL", "Open Data Commons Open Database License", RECOMMENDED);

    val isConformantAndRecommended: Boolean
        get() = conformation == RECOMMENDED

    val isConformant: Boolean
        get() = conformation == CONFORMANT || conformation == RECOMMENDED

    fun id(): String {
        return id
    }

    fun fullName(): String {
        return fullName
    }
}
