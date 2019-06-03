package rs.opendata.validator.model

enum class Health constructor(private val value: Int) {
    LOW(1),
    MEDIUM(2),
    HIGH(3);

    fun lessThen(health: Health): Boolean {
        return this.value < health.value
    }
}
