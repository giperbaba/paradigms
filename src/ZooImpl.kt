import kotlin.random.Random

class ZooImpl(private val animals: MutableList<Animal> = mutableListOf(),
              private val visitors: MutableList<Visitor> = mutableListOf(),
              private val employees: MutableList<Employee> = mutableListOf(),
              private val enclosures: MutableList<Enclosure> = mutableListOf()) : Zoo {

    init {
        repeat(15) {
            val randomAnimalType = listOf("parrot", "wolf", "monkey").random()
            addAnimal(randomAnimalType)
        }
    }

    override fun addVisitor(visitor: Visitor) {
        visitors.add(visitor)
        println("add visitor ${visitor.name} done")
    }

    override fun addEmployee(employee: Employee) {
        employees.add(employee)
        println("add employee ${employee.name} done")
    }

    override fun addEnclosure() {
        val newEnclosure = EnclosureImpl()
        enclosures.add(newEnclosure)
        println("Добавлен новый вольер: ${newEnclosure.javaClass.simpleName}")
    }

    override fun addAnimal(animalType: String) {
        val matchingEnclosure = enclosures.firstOrNull {
            it.animals.isNotEmpty() && it.animals[0].type.equals(animalType, ignoreCase = true) && it.animals.size < it.limitSize
        }

        if (matchingEnclosure != null) {
            matchingEnclosure.add(animalType)
            println("add animal $animalType done")
        }

        else {
            val newEnclosure = EnclosureImpl()
            newEnclosure.add(animalType)
            println("add animal $animalType done")
        }
    }

    override fun checkStatusZoo(): String {
        return "В зоопарке находится ${visitors.size} посетителей, ${employees.size} сотрудников и ${animals.size} животных."
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
        for (animal in animals) {
            status.append(animal.getStatus(animal)).append("\n")
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

    override fun deleteVisitor(name: String) {
        val visitorToRemove = visitors.find { it.name == name }
        if (visitorToRemove != null) {
            visitors.remove(visitorToRemove)
            println("delete visitor ${visitorToRemove.name} done")
        } else {
            println("Посетитель с именем $name не найден в зоопарке.")
        }
    }

    override fun deleteEmployee(name: String) {
        val employeeToRemove = employees.find { it.name == name }
        if (employeeToRemove != null) {
            employees.remove(employeeToRemove)
            println("delete employee ${employeeToRemove.name} done")
        } else {
            println("Сотрудник с именем $name не найден в зоопарке.")
        }
    }

    override fun deleteAnimal(animalType: String) {
        // Находим животное для удаления
        val animalToRemove = animals.find { it.type.equals(animalType, ignoreCase = true) }
        if (animalToRemove != null) {
            // Удаляем животное из общего списка животных в зоопарке
            animals.remove(animalToRemove)
            println("delete animal ${animalToRemove.type} done")

            // Удаляем животное из всех вольеров
            for (enclosure in enclosures) {
                if (enclosure.animals.contains(animalToRemove)) {
                    enclosure.remove(animalType)
                    println("delete animal ${animalToRemove.type} done from enclosure ${enclosure.javaClass.simpleName}")
                }
            }
        } else {
            println("Животное типа $animalType не найдено в зоопарке.")
        }
    }

    override fun editEmployeeName(oldName: String, newName: String) {
        val employeeToEdit = employees.find { it.name == oldName }
        if (employeeToEdit != null) {
            employeeToEdit.editName(oldName, newName, employeeToEdit)
        } else {
            println("Сотрудник с именем $oldName не найден в зоопарке.")
        }
    }

    override fun editEmployeePosition(name: String, newPosition: String) {
        val employeeToEdit = employees.find { it.name == name }
        if (employeeToEdit != null) {
            employeeToEdit.editPosition(name, newPosition, employeeToEdit)
        } else {
            println("Сотрудник с именем $name не найден в зоопарке.")
        }
    }

    override fun editVisitorName(oldName: String, newName: String) {
        val visitorToEdit = visitors.find { it.name == oldName }
        if (visitorToEdit != null) {
            visitorToEdit.editName(oldName, newName, visitorToEdit)
        } else {
            println("Посетитель с именем $oldName не найден в зоопарке.")
        }
    }

    override fun increaseHungerLevel() {
        for (animal in animals) {
            animal.updateHungerLevel(animal)
        }
    }

    override fun fillStockEnclosure() {
        for (enclosure in enclosures) {
            if (enclosure.foodStock == 0) {
                val randomEmployee = employees.random()
                val fillStockMessage = randomEmployee.fillStockFood(enclosure)
                println(fillStockMessage)
            }
        }
    }

    override fun moveAnimals() {

        for (enclosure in enclosures) {
            val animalsInEnclosure = enclosure.animals.toList()

            // Перемещаем случайное количество животных из текущего вольера

            val numberOfAnimalsToMove = Random.nextInt(animalsInEnclosure.size)
            repeat(numberOfAnimalsToMove) {

                val randomAnimal = animalsInEnclosure.random()

                val moveResult = enclosure.moveAnimal(randomAnimal)

                println(moveResult)
            }
        }
    }

    override fun feedAnimalsInEnclosures() {
        for (enclosure in enclosures) {
            for (animal in enclosure.animals) {
                if (animal.hungerLevel >= animal.getHungerLimit()) {
                    animal.eatFromEnclosure(enclosure)
                }
            }
        }
    }
}