class Parrot : Animal("Parrot", 0, Apple(), Juice()) {

    private companion object {
        const val PARROT_VOICE = "Parrot: Привет, ж*па!"
    }

    override fun getHungerLimit(): Int = 20

    override fun makeSound() {
        println(PARROT_VOICE)
    }
}
