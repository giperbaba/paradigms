interface Enclosure {
    val animals: MutableList<Animal>
    var size: Int
    val limitSize: Int
    var foodStock: Int

    fun add(species: String): Boolean
    fun remove(species: String): Boolean
    fun getStatus()

    fun getViewOfAnimals(): String {
        return if (animals.size > 0) {
            animals[0].type
        }
        else {
            "free"
        }
    }

    interface openPart {
        val animalsOpenPart: MutableList<Animal>
        fun moveAnimals(): MutableList<Boolean>
    }

    interface closePart {
        val animalsClosePart: MutableList<Animal>
        fun moveAnimals(): MutableList<Boolean>
    }

}
