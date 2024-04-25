class Visitor(name: String = "", gender: String = "") : People(name, gender) {

    // Метод для редактирования имени посетителя
    fun editName(oldName: String, newName: String, visitorToEdit: Visitor) {
        visitorToEdit.name = newName
        println("Имя посетителя изменено с $oldName на $newName")
    }

    fun getStatus(visitor: Visitor) {
        println("Имя: ${visitor.name}, Пол: ${visitor.gender}")
    }
}