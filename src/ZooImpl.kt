import java.util.*
import kotlin.random.Random

class ZooImpl(
    private val visitors: MutableList<Visitor> = mutableListOf(),
    private val employees: MutableList<Employee> = mutableListOf(),
    private val enclosures: MutableList<Enclosure> = mutableListOf()
) : Zoo {

    init {
        repeat(15) {
            val randomAnimalType = listOf("parrot", "wolf", "monkey").random()
            addAnimal(randomAnimalType)
        }
    }

    override fun getEnclosureCount(): Int {
        return enclosures.size
    }


    override fun createAnimal(species: String): Animal {
        return when (species.lowercase(Locale.getDefault())) {
            "wolf" -> Wolf()
            "parrot" -> Parrot()
            "monkey" -> Monkey()
            else -> throw IllegalArgumentException("Неподдерживаемый вид животного: $species")
        }
    }

    override fun addVisitor(visitor: Visitor): String {
        visitors.add(visitor)
        return "add visitor ${visitor.name} done"
    }

    override fun addEmployee(employee: Employee): String {
        employees.add(employee)
        return "add employee ${employee.name} done"
    }

    override fun addEnclosure(): String {
        val newEnclosure = EnclosureImpl()
        enclosures.add(newEnclosure)
        return "Добавлен новый вольер: ${newEnclosure.javaClass.simpleName}"
    }

    override fun addAnimal(animalType: String): String {
        val animal = createAnimal(animalType)
        val matchingEnclosure = enclosures.firstOrNull {
            it.animals.isNotEmpty() && it.animals[0].type.equals(
                animalType,
                ignoreCase = true
            ) && it.animals.size < it.limitSize
        }

        if (matchingEnclosure != null) {
            matchingEnclosure.append(animal)
            return "add animal $animalType done"
        } else {
            val newEnclosure = EnclosureImpl()
            newEnclosure.append(animal)
            enclosures.add(newEnclosure) // Add the new enclosure to the list
            return "add animal $animalType done"
        }
    }

    override fun checkStatusZoo(): String {
        val totalAnimals = enclosures.sumOf { it.animals.size }
        return "В зоопарке находится ${visitors.size} посетителей, ${employees.size} сотрудников и $totalAnimals животных."
    }

    override fun checkStatusVisitors(): String {
        return if (visitors.isEmpty()) {
            "Список посетителей пуст."
        } else {
            val status = StringBuilder("Список посетителей:\n")
            for (visitor in visitors) {
                status.append(visitor.getStatus(visitor)).append("\n")
            }
            status.toString()
        }
    }

    override fun checkStatusEmployees(): String {
        val status = StringBuilder("Список сотрудников:\n")
        for (employee in employees) {
            status.append(employee.getStatus(employee)).append("\n")
        }
        return status.toString()
    }

    override fun checkStatusAnimals(): String {
        val status = StringBuilder("Статус животных в зоопарке:\n")
        for (enclosure in enclosures) {
            for (animal in enclosure.animals) {
                status.append(animal.getStatus(animal)).append("\n")
            }
        }
        return status.toString()
    }

    override fun checkStatusEnclosure(): String {
        val status = StringBuilder("Статус животных в вольере:\n")
        for (enclosure in enclosures) {
            status.append(enclosure.getStatus()).append("\n")
        }
        return status.toString()
    }

    override fun deleteVisitor(name: String): String {
        val visitorToRemove = visitors.find { it.name == name }
        return if (visitorToRemove != null) {
            visitors.remove(visitorToRemove)
            "delete visitor ${visitorToRemove.name} done"
        } else {
            "Посетитель с именем $name не найден в зоопарке."
        }
    }

    override fun deleteEmployee(name: String): String {
        val employeeToRemove = employees.find { it.name == name }
        return if (employeeToRemove != null) {
            employees.remove(employeeToRemove)
            "delete employee ${employeeToRemove.name} done"
        } else {
            "Сотрудник с именем $name не найден в зоопарке."
        }
    }

    override fun deleteAnimal(animalType: String): String {
        var result = StringBuilder()
        for (enclosure in enclosures) {
            val animalToRemove = enclosure.animals.find { it.type.equals(animalType, ignoreCase = true) }
            if (animalToRemove != null) {
                enclosure.animals.remove(animalToRemove)
                result.append("delete animal ${animalToRemove.type} done from enclosure ${enclosure.javaClass.simpleName}\n")
            }
        }
        return if (result.isEmpty()) {
            "Животное типа $animalType не найдено в зоопарке."
        } else {
            result.toString().trim()
        }
    }

    override fun deleteEnclosure(index: Int): String {
        return if (index in enclosures.indices) {
            val enclosureToRemove = enclosures[index]
            enclosures.removeAt(index)
            "delete enclosure at index $index done, removed ${enclosureToRemove.animals.size} animals"
        } else {
            "Вольер с индексом $index не найден в зоопарке."
        }
    }

    override fun editEmployeeName(oldName: String, newName: String): String {
        val employeeToEdit = employees.find { it.name == oldName }
        return if (employeeToEdit != null) {
            employeeToEdit.editName(oldName, newName, employeeToEdit)
            "Сотрудник $oldName изменил имя на $newName."
        } else {
            "Сотрудник с именем $oldName не найден в зоопарке."
        }
    }

    override fun editEmployeePosition(name: String, newPosition: String): String {
        val employeeToEdit = employees.find { it.name == name }
        return if (employeeToEdit != null) {
            employeeToEdit.editPosition(name, newPosition, employeeToEdit)
            "Сотрудник $name теперь имеет должность $newPosition."
        } else {
            "Сотрудник с именем $name не найден в зоопарке."
        }
    }

    override fun editVisitorName(oldName: String, newName: String): String {
        val visitorToEdit = visitors.find { it.name == oldName }
        return if (visitorToEdit != null) {
            visitorToEdit.editName(oldName, newName, visitorToEdit)
            "Посетитель $oldName изменил имя на $newName."
        } else {
            "Посетитель с именем $oldName не найден в зоопарке."
        }
    }

    override fun increaseHungerLevel() {
        for (enclosure in enclosures) {
            enclosure.animals.forEach { it.updateHungerLevel(it) }
        }
    }

    override fun fillStockEnclosure(): String {
        val status = StringBuilder()
        for (enclosure in enclosures) {
            if (enclosure.foodStock <= 0) {
                val randomEmployee = employees.random()
                val fillStockMessage = randomEmployee.fillStockFood(enclosure)
                status.append(fillStockMessage)
            }
        }
        return status.toString().trim()
    }

    override fun moveAnimals() {
        val status = StringBuilder()
        for (enclosure in enclosures) {
            val animalsInEnclosure = enclosure.animals.toList()
            val numberOfAnimalsToMove = Random.nextInt(animalsInEnclosure.size)
            repeat(numberOfAnimalsToMove) {
                // Select a random index for the animal
                val randomIndex = animalsInEnclosure.indices.random()
                val randomAnimal = animalsInEnclosure[randomIndex]
                // Call moveAnimal with the animal and its index
                val moveResult = enclosure.moveAnimal(randomAnimal, randomIndex)
                status.append(moveResult).append("\n")
            }
        }
        println(status.toString().trim())
    }

    override fun feedAnimalsInEnclosures() {
        val status = StringBuilder()
        for (enclosure in enclosures) {
            for (animalIndex in 0 until enclosure.animals.size) {
                val animal = enclosure.animals[animalIndex]
                if (animal.hungerLevel >= animal.getHungerLimit()) {
                    animal.eatFromEnclosure(enclosure)
                    animal.hungerLevel = 0
                    status.append("${animal.type} id:$animalIndex has been fed\n")
                }
            }
        }
        val result = status.toString().trim()
        if (result.isNotEmpty()) {
            println(result)
        }
    }

    override fun feedAnimalsByVisitor(visitorName: String, species: String): String {
        val visitor = visitors.find { it.name == visitorName }
        if (visitor == null) {
            return "Посетитель с именем $visitorName не найден в зоопарке."
        }

        for (enclosure in enclosures) {
            val animal = enclosure.getOpenablePart().find { it.type.equals(species, ignoreCase = true) }
            if (animal != null) {
                visitor.feedAnimal(animal)
                return "${visitor.name} покормил(а) ${animal.type} в открытой части вольера."
            }
        }

        return "Животное типа $species не найдено в открытой части вольера."
    }

    override fun getRandomVisitor(): Visitor? {
        if (visitors.isNotEmpty()) {
            return visitors.random()
        }
        return null
    }

    override fun getRandomAnimal(): Animal {
        return enclosures.random().animals.random()
    }

    override fun checkStatusOpenablePart() {
        for (i in 0..<enclosures.size) {
            println("$i:${enclosures[i].checkStatusOpenablePart()}")
        }
    }


}
