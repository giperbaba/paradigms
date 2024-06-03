class Wolf : Animal("Wolf", 0, Meat(), Vodka()) {

    private companion object {
        const val WOLFVOICE = "Wolf: Уууууууу"
    }

    override fun getHungerLimit(): Int = 30

    override fun makeSound() {
        println(WOLFVOICE)
    }
}