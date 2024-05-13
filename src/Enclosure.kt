interface OpenablePart {
    val animalsOpenPart: MutableList<Animal>
}

interface CloseablePart {
    val animalsClosePart: MutableList<Animal>
}

interface Enclosure {

    val animals: MutableList<Animal>
    val limitSize: Int
    var foodStock: Int

    fun add(species: String): String
    fun remove(species: String): String

    fun getStatus() : String
    fun isFull(): Boolean

    fun moveAnimal(animal: Animal): String
}


class OpenablePartImpl : OpenablePart {
    override val animalsOpenPart: MutableList<Animal> = mutableListOf()
}

class CloseablePartImpl : CloseablePart {
    override val animalsClosePart: MutableList<Animal> = mutableListOf()
}