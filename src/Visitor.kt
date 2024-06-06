class Visitor(
    name: String = "",
    gender: String = "",
    private var cash: Int = 100,
) : People(name, gender) {

    private val food: MutableList<SpecialFood> = mutableListOf()

    fun getStatus(visitor: Visitor): String {
        return "Имя: ${visitor.name}, Пол: ${visitor.gender}"
    }

    fun editName(oldName: String, newName: String, visitorToEdit: Visitor): String {
        visitorToEdit.name = newName
        return "Имя посетителя изменено с $oldName на $newName"
    }

    private fun buySpecialFood(specialFood: SpecialFood): String {
        return if (cash >= specialFood.price) {
            cash -= specialFood.price
            food.add(specialFood)
            "Специальная еда ${specialFood.name} куплена. Остаток денег: $cash"
        } else {
            "Недостаточно средств для покупки"
        }
    }

    fun feedAnimal(animal: Animal) {
        if (animal.hungerLevel < animal.getHungerLimit()) {
            println("${animal.type}: не хочу нах")
            return
        }
        if (food.size == 0) {
            val randomFoodType = listOf(animal.firstFoodType, animal.secondFoodType).random()
            val specialFood = SpecialFood(randomFoodType.type, 10, randomFoodType.capacity)
            this.buySpecialFood(specialFood)
            food.add(specialFood)
            animal.hungerLevel -= specialFood.capacity
            food.remove(specialFood)
            println("${this.name} кормит ${animal.type} едой: ${specialFood.name}")
        }
        else {
            val foodForFeeding = food.random()
            animal.hungerLevel -= foodForFeeding.capacity
            food.remove(foodForFeeding)
            println("${this.name} кормит ${animal.type}")
        }
    }
}