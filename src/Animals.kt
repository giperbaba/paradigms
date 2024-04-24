abstract class Animal(val type: String) {
    private companion object {
        const val HUNGRY = "Голоден"
        const val FULL = "Сыт"
    }

    var hungerLevel: Int = 0

    fun getHungerStatus(): String {
        return if (hungerLevel >= getHungerLimit()) HUNGRY else FULL
    }

    abstract fun getHungerLimit(): Int

    abstract fun makeSound()
}

class Parrot : Animal("Parrot") {
    private companion object {
        const val PARROTVOICE = "Parrot: Привет, ж*па!"
    }
    override fun getHungerLimit(): Int = 30
    override fun makeSound() {
        println(PARROTVOICE)
    }
}

class Wolf : Animal("Wolf") {
    private companion object {
        const val WOLFVOICE = "Wolf: Уууууууу"
    }
    override fun getHungerLimit(): Int = 25
    override fun makeSound() {
        println(WOLFVOICE)
    }
}

class Monkey : Animal("Monkey") {
    private companion object {
        const val MONKEYVOICE = "Monkey: У у уа у"
    }
    override fun getHungerLimit(): Int = 20
    override fun makeSound() {
        println(MONKEYVOICE)
    }
}