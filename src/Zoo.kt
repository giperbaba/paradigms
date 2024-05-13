interface Zoo {
    fun addVisitor(visitor: Visitor)
    fun addEmployee(employee: Employee)
    fun addEnclosure()
    fun addAnimal(animalType: String)

    fun checkStatusZoo(): String
    fun checkStatusVisitors(): String
    fun checkStatusEmployees(): String
    fun checkStatusAnimals(): String
    fun checkStatusEnclosure(): String

    fun deleteVisitor(name: String)
    fun deleteEmployee(name: String)
    fun deleteAnimal(animalType: String)

    fun editEmployeeName(oldName: String, newName: String)
    fun editEmployeePosition(name: String, newPosition: String)
    fun editVisitorName(oldName: String, newName: String)

    fun increaseHungerLevel()
    fun fillStockEnclosure()
    fun moveAnimals()
    fun feedAnimalsInEnclosures()
}