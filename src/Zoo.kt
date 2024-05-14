interface Zoo {
    fun createAnimal(species: String): Animal

    fun addVisitor(visitor: Visitor):String
    fun addEmployee(employee: Employee):String
    fun addEnclosure():String
    fun addAnimal(animalType: String):String

    fun checkStatusZoo(): String
    fun checkStatusVisitors(): String
    fun checkStatusEmployees(): String
    fun checkStatusAnimals(): String
    fun checkStatusEnclosure(): String

    fun deleteVisitor(name: String):String
    fun deleteEmployee(name: String):String
    fun deleteAnimal(animalType: String):String

    fun editEmployeeName(oldName: String, newName: String):String
    fun editEmployeePosition(name: String, newPosition: String):String
    fun editVisitorName(oldName: String, newName: String):String

    fun increaseHungerLevel()
    fun fillStockEnclosure():String
    fun moveAnimals()
    fun feedAnimalsInEnclosures()
    fun feedAnimalsByVisitor(visitorName: String, species: String) : String
}