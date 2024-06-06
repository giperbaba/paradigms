import java.util.UUID

class Employee(
    name: String = "",
    gender: String = "",
    private var position: String = "",
) : People(name, gender) {

    fun getStatus(employee: Employee): String {
        return "Имя: ${employee.name}, Пол: ${employee.gender}, Должность: ${employee.position}"
    }

    fun editName(oldName: String, newName: String, employeeToEdit: Employee): String {
        employeeToEdit.name = newName
        return "Имя сотрудника изменено с $oldName на $newName"
    }

    fun editPosition(name: String, newPosition: String, employeeToEdit: Employee): String {
        employeeToEdit.position = newPosition
        return "Должность сотрудника $name изменена на ${employeeToEdit.position}"
    }

    fun fillStockFood(enclosure: Enclosure, food: Food) {
        val currentStock = enclosure.hashMap[food.type] ?: 0
        println("Текущий запас ${food.type}: $currentStock")
        enclosure.hashMap[food.type] = currentStock + enclosure.animals.size
        println("${this.name} пополняет запас ${food.type} в вольере. Новый запас: ${enclosure.hashMap[food.type]}")
    }
}
