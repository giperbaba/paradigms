import java.util.*

class Zoo (private val animals: MutableList<Animal> = mutableListOf<Animal>(),
           private val visitors: MutableList<Visitor> = mutableListOf<Visitor>(),
           private val employees: MutableList<Employee> = mutableListOf<Employee>(),
           private val management: ZooManagement = ZooManagement(employees)) {

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

        animals.add(animal)
        animal.getZeroHungerLevel(animal)
        println("add animal ${animal.type} done")
    }

    // Метод для проверки статуса
    fun checkStatusZoo() {
        println("В зоопарке находится ${visitors.size} посетителей, ${employees.size} сотрудников и ${animals.size} животных.")
    }

    fun checkStatusVisitors() {
        if (visitors.isEmpty()) {
            println("Список посетителей пуст.") }
        else {
            println("Список посетителей:")
            for (visitor in visitors) {
                visitor.getStatus(visitor)
            }
        }
    }

    fun checkStatusEmployees() {
        println("Список сотрудников:")
        for (employee in employees) {
            employee.getStatus(employee)
        }
    }

    fun checkStatusAnimals() {
        println("Статус животных в зоопарке:")
        for (animal in animals) {
            animal.getStatus(animal)
        }
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
        val animalToRemove = animals.find { it.type.equals(animalType, ignoreCase = true) }
        if (animalToRemove != null) {
            animals.remove(animalToRemove)
            println("delete animal ${animalToRemove.type} done")
        } else {
            println("Животное типа $animalType не найдено в зоопарке.")
        }
    }

    //Кормление
    fun increaseHungerLevel() {
        for (animal in animals) {
            animal.updateHungerLevel(animal)
        }
    }

    fun feedHungryAnimals() {
        for (animal in animals) {
            if (animal.getHungerStatus() == "Голоден") {
                val employee = management.getResponsibleEmployee(animal)
                if (employee != null) {
                    employee.feedAnimals(animal)
                }
                else {
                    println("Нет сотрудника, ответственного за ${animal.type}.")
                }
            }
        }
    }

    //Редактирование пользователей
    fun editEmployeeName(oldName: String, newName: String) {
        val employeeToEdit = employees.find { it.name == oldName }
        if (employeeToEdit != null) {
            employeeToEdit.editName(oldName, newName, employeeToEdit)
        }
        else {
            println("Сотрудник с именем $oldName не найден в зоопарке.")
        }
    }

    fun editEmployeePosition(name: String, newPosition: String) {
        val employeeToEdit = employees.find { it.name == name }
        if (employeeToEdit != null) {
            employeeToEdit.editPosition(name, newPosition, employeeToEdit)
        }
        else {
            println("Сотрудник с именем $name не найден в зоопарке.")
        }
    }

    fun editVisitorName(oldName: String, newName: String) {
        val visitorToEdit = visitors.find { it.name == oldName }
        if (visitorToEdit != null) {
            visitorToEdit.editName(oldName, newName, visitorToEdit)
        }
        else {
            println("Посетитель с именем $oldName не найден в зоопарке.")
        }
    }
}