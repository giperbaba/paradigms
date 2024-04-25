class Parrot : Animal("Parrot") {
    private companion object {
        const val PARROT_VOICE = "Parrot: Привет, ж*па!"
    }
    override fun getHungerLimit(): Int = 30

    override fun makeSound() {
        println(PARROT_VOICE)
    }
}
