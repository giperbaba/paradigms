class Monkey : Animal("Monkey") {

    private companion object {
        const val MONKEYVOICE = "Monkey: У у уа у"
    }

    override fun getHungerLimit(): Int = 20

    override fun makeSound() {
        println(MONKEYVOICE)
    }
}