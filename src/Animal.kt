import java.util.UUID

abstract class Animal(
    val type: String = "",
    var hungerLevel: Int = 0,
    open val firstFoodType: Food,
    open val secondFoodType: Food,
    override val id: UUID = UUID.randomUUID()
) : BaseEntity() {

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
        val firstFoodAvailable = enclosure.hashMap[this.firstFoodType.type] ?: 0
        val secondFoodAvailable = enclosure.hashMap[this.secondFoodType.type] ?: 0

        if (firstFoodAvailable > 0 && secondFoodAvailable > 0) {
            val randomFoodType = listOf(firstFoodType, secondFoodType).random()
            enclosure.hashMap[randomFoodType.type] = enclosure.hashMap[randomFoodType.type]!! - 1
            this.hungerLevel -= randomFoodType.capacity
            if (this.hungerLevel < 0) {
                this.hungerLevel = 0
            }
            println("$type поел(а) ${randomFoodType.type} из вольера")
        }
        else if (firstFoodAvailable > 0) {
            enclosure.hashMap[firstFoodType.type] = firstFoodAvailable - 1
            this.hungerLevel -= firstFoodType.capacity
            if (this.hungerLevel < 0) {
                this.hungerLevel = 0
            }
            println("$type поел(а) ${firstFoodType.type} из вольера")
        }
        else if (secondFoodAvailable > 0) {
            enclosure.hashMap[secondFoodType.type] = secondFoodAvailable - 1
            this.hungerLevel -= secondFoodType.capacity
            if (this.hungerLevel < 0) {
                this.hungerLevel = 0
            }
            println("$type поел(а) ${secondFoodType.type} из вольера")
        }
        else {
            println("В вольере недостаточно еды для $type")
        }
    }
}
