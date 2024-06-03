interface Zoo {
    fun createAnimal(species: String): Animal

    //fun addNewEntity(newEntity: BaseEntity)

    fun checkStatusZoo(): String
    fun checkStatusVisitors(): String
    fun checkStatusEmployees(): String
    fun checkStatusAnimals(): String
    fun checkStatusEnclosure(): String
    fun checkStatusOpenablePart()

    fun editEmployeeName(oldName: String, newName: String): String
    fun editEmployeePosition(name: String, newPosition: String): String
    fun editVisitorName(oldName: String, newName: String): String

    fun increaseHungerLevel()
    fun fillStockEnclosure(): String
    fun moveAnimals()
    fun feedAnimalsInEnclosures()
    fun feedAnimalsByVisitor(visitorName: String, species: String): String
    fun getEnclosureCount(): Int

    fun getRandomVisitor(): Visitor?
    fun getRandomAnimal(): Animal
}