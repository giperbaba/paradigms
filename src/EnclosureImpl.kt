import java.util.Locale
import kotlin.random.Random

class EnclosureImpl : Enclosure, BaseEntity() {

    private companion object {
        const val LIMITWOLF = 5
        const val LIMITMONKEY = 7
        const val LIMITPARROT = 10
        const val INITIALFOOD = 150
    }

    override val animals: MutableList<Animal> = mutableListOf()
    override var limitSize: Int = -1

    private val openablePart: OpenablePart = OpenablePartImpl()
    private val closeablePart: CloseablePart = CloseablePartImpl()

    override var hashMap: MutableMap<String, Int> = mutableMapOf()

    fun <T : Food, G : Food> addEnclosureFoodType(firstFoodType: T, secondFoodType: G) {
        hashMap[firstFoodType.type.toString()] = INITIALFOOD
        hashMap[secondFoodType.type.toString()] = INITIALFOOD
    }

    override fun getOpenablePart(): List<Animal> {
        return openablePart.animalsOpenPart
    }

    override fun isFull(): Boolean {
        return animals.size == limitSize
    }

    override fun append(animal: Animal): String {
        limitSize = when (animal.type.lowercase(Locale.getDefault())) {
            "parrot" -> LIMITPARROT
            "wolf" -> LIMITWOLF
            "monkey" -> LIMITMONKEY
            else -> throw IllegalArgumentException("Неизвестный тип животного: ${animal.type}")
        }

        if (animals.size >= limitSize) {
            return "Вольер полон, невозможно добавить ${animal.type}."
        }

        animals.add(animal)
        animal.getZeroHungerLevel(animal)

        val addToOpenPart = Random.nextBoolean()
        if (addToOpenPart) {
            openablePart.animalsOpenPart.add(animal)
        } else {
            closeablePart.animalsClosePart.add(animal)
        }

        return "Добавлено животное ${animal.type}. Размер вольера: ${animals.size}"
    }

    override fun remove(species: String): String {
        val animalToRemove = animals.firstOrNull { it.type == species }
        return if (animalToRemove != null) {
            animals.remove(animalToRemove)
            "$species удалено из вольера. Размер вольера: $animals.size"
        } else {
            "$species не найдено в вольере."
        }
    }

    override fun moveAnimal(animal: Animal, animalId: Int): String {
        if (openablePart.animalsOpenPart.contains(animal)) {
            openablePart.animalsOpenPart.remove(animal)
            closeablePart.animalsClosePart.add(animal)
            return "${animal.type} id:$animalId перемещен(а) в закрытую часть вольера"
        }

        if (closeablePart.animalsClosePart.contains(animal)) {
            closeablePart.animalsClosePart.remove(animal)
            openablePart.animalsOpenPart.add(animal)
            return "${animal.type} id:$animalId перемещен(а) в открытую часть вольера"
        }

        return "${animal.type} id:$animalId не найдено в вольере"
    }


    override fun getStatus(): String {
        val status = StringBuilder()
        status.append("Статус вольера:\n")
        status.append(
            "Запас еды: ${animals[0].firstFoodType} - ${this.hashMap[animals[0].firstFoodType.type.toString()]}\n" +
                    "${animals[0].secondFoodType} - ${this.hashMap[animals[0].secondFoodType.type.toString()]}"
        )
        status.append("Животные:\n")
        animals.forEach { status.append("${it.type}\n") }
        return status.toString()
    }

    override fun checkStatusOpenablePart(): String {
        val status = StringBuilder()
        status.append("Животные в открытой части:\n")
        openablePart.animalsOpenPart.forEach { status.append(it.type) }
        return status.toString()
    }
}
