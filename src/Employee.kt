class Employee(name: String = "", gender: String = "", var position: String = "") : People(name, gender) {

    // Метод кормления конкретного животного сотрудником
    fun feedAnimals(animal: Animal) {
        animal.hungerLevel = 0
        println("${this.name} кормит ${animal.type}")
    }

    fun getStatus(employee: Employee) {
        println("Имя: ${employee.name}, Пол: ${employee.gender}, Должность: ${employee.position}")
    }

    // Метод для редактирования имени сотрудника
    fun editName(oldName: String, newName: String, employeeToEdit: Employee) {
        employeeToEdit.name = newName
        println("Имя сотрудника изменено с $oldName на $newName")
    }

    // Метод для редактирования должности сотрудника
    fun editPosition(name: String, newPosition: String, employeeToEdit: Employee){
        employeeToEdit.position = newPosition.replace("monkey", "Monkey Feeder").replace("parrot", "Parrot Feeder").replace("wolf", "Wolf Feeder")
        println("Должность сотрудника $name изменена на ${employeeToEdit.position}")
    }
}