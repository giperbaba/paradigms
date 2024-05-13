class Employee(name: String = "", gender: String = "", private var position: String = "") : People(name, gender) {

    private companion object {
        const val FOODAMOUNT = 100
    }

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

    fun fillStockFood(enclosure: Enclosure): String {
        enclosure.foodStock += FOODAMOUNT
        return "${this.name} пополняет запас еды в вольере."
    }
}
