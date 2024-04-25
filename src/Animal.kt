abstract class Animal(val type: String = "", var hungerLevel: Int = 0) {

    private companion object {
        const val HUNGRY = "Голоден"
        const val FULL = "Сыт"
    }

    fun getHungerStatus(): String {
        return if (hungerLevel >= getHungerLimit()) HUNGRY else FULL
    }

    fun getZeroHungerLevel (animal: Animal) {
        animal.hungerLevel = 0
    }

    fun updateHungerLevel (animal: Animal) {
        animal.hungerLevel += 1
    }

    fun getStatus(animal: Animal) {
        println("Тип: ${animal.type}, Уровень голода: ${animal.hungerLevel}")
    }

    abstract fun getHungerLimit(): Int

    abstract fun makeSound()
}