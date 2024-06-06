interface OpenablePart {
    val animalsOpenPart: MutableList<Animal>
}

interface CloseablePart {
    val animalsClosePart: MutableList<Animal>
}

interface Enclosure {
    val animals: MutableList<Animal>
    val limitSize: Int
    var hashMap: MutableMap<FoodType, Int>

    fun append(animal: Animal): String
    fun remove(species: String): String

    fun getOpenablePart(): List<Animal>

    fun getStatus() : String
    fun checkStatusOpenablePart(): String
    fun isFull(): Boolean

    fun moveAnimal(animal: Animal, animalId: Int): String
}


class OpenablePartImpl : OpenablePart {
    override val animalsOpenPart: MutableList<Animal> = mutableListOf()
}

class CloseablePartImpl : CloseablePart {
    override val animalsClosePart: MutableList<Animal> = mutableListOf()
}