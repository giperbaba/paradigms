import java.util.*


class Visitor(name: String = "", gender: String = "", private var cash: Int = 100) : People(name, gender) {

    private val food: MutableList<SpecialFood> = mutableListOf()

    fun getStatus(visitor: Visitor): String {
        return "Имя: ${visitor.name}, Пол: ${visitor.gender}"
    }

    fun editName(oldName: String, newName: String, visitorToEdit: Visitor): String {
        visitorToEdit.name = newName
        return "Имя посетителя изменено с $oldName на $newName"
    }

    fun buySpecialFood(specialFood: SpecialFood): String {
        return if (cash >= specialFood.price) {
            cash -= specialFood.price
            food.add(specialFood)
            "Специальная еда ${specialFood.name} куплена. Остаток денег: $cash"
        } else {
            "Недостаточно средств для покупки"
        }
    }

    fun feedAnimal(animal: Animal) {
        if (animal.hungerLevel != 0) {
            println("${animal.type}: не хочу нах" ) //TODO: переделать слова
        }
        else if (food.size == 0) {
            println ("Приобретите специальную еду")
        }
        else {
            println("Выберете еду:")
            for (specialFood in food) {
                print(specialFood)
            }
            val scanner = Scanner(System.`in`)
            val input = scanner.nextLine().lowercase(Locale.getDefault())
            val foodForFeeding = food.find { it.name == input }
            if (foodForFeeding != null) {
                animal.hungerLevel = 0
                food.remove(foodForFeeding)
                println("${this.name} кормит ${animal.type}")
            }
        }
    }
}