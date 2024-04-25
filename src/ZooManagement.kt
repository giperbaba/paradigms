class ZooManagement(private val employees: List<Employee>) {
    fun getResponsibleEmployee(animal: Animal): Employee? {
        return when (animal) {
            is Parrot -> getRandomEmployeeByPosition("Parrot Feeder")
            is Wolf -> getRandomEmployeeByPosition("Wolf Feeder")
            is Monkey -> getRandomEmployeeByPosition("Monkey Feeder")
            else -> null
        }
    }

    private fun getRandomEmployeeByPosition(position: String): Employee? {
        val employeesWithPosition = employees.filter { it.position == position }
        return if (employeesWithPosition.isNotEmpty()) {
            val randomIndex = employeesWithPosition.indices.random()
            employeesWithPosition[randomIndex]
        } else {
            null
        }
    }
}