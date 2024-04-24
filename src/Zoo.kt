import java.util.*

class Zoo {
    private val visitors = mutableListOf<Visitor>()
    private val employees = mutableListOf<Employee>()
    private val animals = mutableListOf<Animal>()

    // Методы для добавления людей и животных
    fun addVisitor(visitor: Visitor) {
        visitors.add(visitor)
        println("add visitor ${visitor.name} done")
    }

    fun addEmployee(employee: Employee) {
        employees.add(employee)
        println("add employee ${employee.name} done")
    }

    fun addAnimal(animalType: String) {
        val animal: Animal = when (animalType.lowercase(Locale.getDefault())) {
            "parrot" -> Parrot()
            "wolf" -> Wolf()
            "monkey" -> Monkey()
            else -> throw IllegalArgumentException("Неизвестный тип животного: $animalType")
        }
        // Устанавливаем начальный уровень голода для добавленного животного
        animal.hungerLevel = 0
        animals.add(animal)
        println("add animal ${animal.type} done")
    }

    // Метод увеличения уровня голода у всех животных
    fun increaseHungerLevel() {
        for (animal in animals) {
            animal.hungerLevel += 1
        }
    }

    // Метод для поиска сотрудника, ответственного за кормление определенного животного
    private fun getResponsibleEmployee(animal: Animal): Employee? {
        return when (animal) {
            is Parrot -> getRandomEmployeeByPosition("Parrot Feeder")
            is Wolf -> getRandomEmployeeByPosition("Wolf Feeder")
            is Monkey -> getRandomEmployeeByPosition("Monkey Feeder")
            else -> null
        }
    }

    // Метод для выбора случайного сотрудника из списка сотрудников с заданной должностью
    private fun getRandomEmployeeByPosition(position: String): Employee? {
        val employeesWithPosition = employees.filter { it.position == position }
        return if (employeesWithPosition.isNotEmpty()) {
            val randomIndex = employeesWithPosition.indices.random()
            employeesWithPosition[randomIndex]
        } else {
            null
        }
    }

    // Метод кормления голодных животных
    fun feedHungryAnimals() {
        for (animal in animals) {
            if (animal.getHungerStatus() == "Голоден") {
                val employee = getResponsibleEmployee(animal)
                if (employee != null) {
                    feedAnimals(employee, animal)
                } else {
                    println("Нет сотрудника, ответственного за ${animal.type}.")
                }
            }
        }
    }

    // Метод кормления конкретного животного сотрудником
    private fun feedAnimals(employee: Employee, animal: Animal) {
        animal.hungerLevel = 0
        println("${employee.name} кормит ${animal.type}")
    }

    // Метод для издания звука животным
    fun makeAnimalSound(animal: Animal) {
        animal.makeSound()
    }

    // Методы для удаления людей и животных
    fun deleteVisitor(name: String) {
        val visitorToRemove = visitors.find { it.name == name }
        if (visitorToRemove != null) {
            visitors.remove(visitorToRemove)
            println("delete visitor ${visitorToRemove.name} done")
        } else {
            println("Посетитель с именем $name не найден в зоопарке.")
        }
    }

    fun deleteEmployee(name: String) {
        val employeeToRemove = employees.find { it.name == name }
        if (employeeToRemove != null) {
            employees.remove(employeeToRemove)
            println("delete employee ${employeeToRemove.name} done")
        } else {
            println("Сотрудник с именем $name не найден в зоопарке.")
        }
    }

    fun deleteAnimal(animalType: String) {
        val animal: Animal = when (animalType.lowercase(Locale.getDefault())) {
            "parrot" -> Parrot()
            "wolf" -> Wolf()
            "monkey" -> Monkey()
            else -> throw IllegalArgumentException("Неизвестный тип животного: $animalType")
        }
        animals.remove(animal)
        println("delete animal ${animal.type} done")
    }

    // Метод для проверки статуса зоопарка
    fun checkStatus() {
        val visitorsCount = visitors.size
        val employeesCount = employees.size
        val animalsCount = animals.size
        println("В зоопарке находится $visitorsCount посетителей, $employeesCount сотрудников и $animalsCount животных.")
    }

    // Метод для проверки статуса посетителей
    fun checkStatusVisitors() {
        if (visitors.isEmpty()) {
            println("Список посетителей пуст.")
        } else {
            println("Список посетителей:")
            for (visitor in visitors) {
                println("Имя: ${visitor.name}, Пол: ${visitor.gender}")
            }
        }
    }

    // Метод для проверки статуса сотрудников
    fun checkStatusEmployees() {
        println("Список сотрудников:")
        for (employee in employees) {
            println("Имя: ${employee.name}, Пол: ${employee.gender}, Должность: ${employee.position}")
        }
    }

    // Метод для проверки статуса животных
    fun checkStatusAnimals() {
        println("Список животных и их уровень голода:")
        for (animal in animals) {
            println("Тип: ${animal.type}, Уровень голода: ${animal.hungerLevel}")
        }
    }

    // Метод для редактирования имени посетителя
    fun editVisitorName(oldName: String, newName: String) {
        val visitorToEdit = visitors.find { it.name == oldName }
        if (visitorToEdit != null) {
            visitorToEdit.name = newName
            println("Имя посетителя изменено с $oldName на $newName")
        } else {
            println("Посетитель с именем $oldName не найден в зоопарке.")
        }
    }

    // Метод для редактирования имени сотрудника
    fun editEmployeeName(oldName: String, newName: String) {
        val employeeToEdit = employees.find { it.name == oldName }
        if (employeeToEdit != null) {
            employeeToEdit.name = newName
            println("Имя сотрудника изменено с $oldName на $newName")
        } else {
            println("Сотрудник с именем $oldName не найден в зоопарке.")
        }
    }

    // Метод для редактирования должности сотрудника
    fun editEmployeePosition(name: String, newPosition: String) {
        val employeeToEdit = employees.find { it.name == name }
        if (employeeToEdit != null) {
            employeeToEdit.position = newPosition.replace("monkey", "Monkey Feeder").replace("parrot", "Parrot Feeder").replace("wolf", "Wolf Feeder")
            println("Должность сотрудника $name изменена на ${employeeToEdit.position}")
        } else {
            println("Сотрудник с именем $name не найден в зоопарке.")
        }
    }
}