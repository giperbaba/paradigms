import java.util.UUID

abstract class Animal(
    val type: String = "",
    var hungerLevel: Int = 0,
    open val firstFoodType: Food,
    open val secondFoodType: Food,
    override val id: UUID = UUID.randomUUID()
) : BaseEntity() {


    private companion object {
        const val HUNGRY = "Голоден"
        const val FULL = "Сыт"
    }

    fun getHungerStatus(): String {
        return if (hungerLevel >= getHungerLimit()) HUNGRY else FULL
    }

    fun getZeroHungerLevel(animal: Animal) {
        animal.hungerLevel = 0
    }

    fun updateHungerLevel(animal: Animal) {
        animal.hungerLevel += 1
    }

    fun getStatus(animal: Animal): String {
        return "Тип: ${animal.type}, Уровень голода: ${animal.hungerLevel}"
    }

    abstract fun getHungerLimit(): Int

    abstract fun makeSound()

    fun eatFromEnclosure(enclosure: Enclosure) {
        val foodToEat = hungerLevel
        val firstFoodAvailable = enclosure.hashMap[this.firstFoodType.type.toString()] ?: 0
        val secondFoodAvailable = enclosure.hashMap[this.secondFoodType.type.toString()] ?: 0

        if (firstFoodAvailable >= foodToEat && secondFoodAvailable >= foodToEat) {
            val randomFoodType = listOf(firstFoodType, secondFoodType).random()
            enclosure.hashMap[randomFoodType.type.toString()] = (enclosure.hashMap[randomFoodType.type.toString()] ?: 0) - foodToEat
            this.hungerLevel = 0
            println("$type поел(а) ${randomFoodType.type} из вольера")
        }
        else if (firstFoodAvailable >= foodToEat) {
            enclosure.hashMap[firstFoodType.type.toString()] = firstFoodAvailable - foodToEat
            this.hungerLevel = 0
            println("$type поел(а) ${firstFoodType.type} из вольера")
        }
        else if (secondFoodAvailable >= foodToEat) {
            enclosure.hashMap[secondFoodType.type.toString()] = secondFoodAvailable - foodToEat
            this.hungerLevel = 0
            println("$type поел(а) ${secondFoodType.type} из вольера")
        }
        else {
            println("В вольере недостаточно еды для $type")
        }
    }
}
