class Employee(name: String = "", gender: String = "", var position: String = "") : People(name, gender) {

    fun getStatus(employee: Employee) {
        println("Имя: ${employee.name}, Пол: ${employee.gender}, Должность: ${employee.position}")
    }

    fun editName(oldName: String, newName: String, employeeToEdit: Employee) {
        employeeToEdit.name = newName
        println("Имя сотрудника изменено с $oldName на $newName")
    }

    fun editPosition(name: String, newPosition: String, employeeToEdit: Employee){
        employeeToEdit.position = newPosition.replace("monkey", "Monkey Feeder").replace("parrot", "Parrot Feeder").replace("wolf", "Wolf Feeder")
        println("Должность сотрудника $name изменена на ${employeeToEdit.position}")
    }

    fun feedAnimals(animal: Animal) {
        animal.hungerLevel = 0
        println("${this.name} кормит ${animal.type}")
    }
}