class Wolf : Animal("Wolf", 0) {

    private companion object {
        const val WOLFVOICE = "Wolf: Уууууууу"
    }

    override fun getHungerLimit(): Int = 70

    override fun makeSound() {
        println(WOLFVOICE)
    }
}