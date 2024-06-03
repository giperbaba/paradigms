import java.util.UUID
import java.util.Locale
import kotlin.random.Random

class ZooImpl<T : BaseEntity> : Zoo {

    val entityList: MutableList<T> = mutableListOf()

    init {
        repeat(15) {
            val randomAnimalType = listOf("parrot", "wolf", "monkey").random()
            val newAnimal = createAnimal(randomAnimalType)
            addNewEntity(newAnimal as T)
        }
    }

    fun addNewEntity(newEntity: T) {
        when (newEntity) {
            is Animal -> {
                entityList.add(newEntity)
                val animalType = newEntity.type
                val matchingEnclosure = entityList.filterIsInstance<EnclosureImpl>().firstOrNull {
                    it.animals.isEmpty() || (it.animals[0].type.equals(animalType, ignoreCase = true)
                            && it.animals.size < it.limitSize)
                }

                if (matchingEnclosure != null) {
                    matchingEnclosure.append(newEntity)
                    println("add animal $animalType done")
                } else {
                    val newEnclosure = EnclosureImpl()
                    newEnclosure.append(newEntity)
                    newEnclosure.addEnclosureFoodType(newEntity.firstFoodType, newEntity.secondFoodType)
                    addNewEntity(newEnclosure as T)
                    println("add animal $animalType done")
                }
            }

            is Visitor -> {
                entityList.add(newEntity)
                println("add visitor ${newEntity.name} done")
            }

            is Employee -> {
                entityList.add(newEntity)
                println("add employee ${newEntity.name} done")
            }

            is Enclosure -> {
                entityList.add(newEntity)
                println("add enclosure done")
            }
        }
    }

    //помогает нам определять тип, который затирается при сборке, чтобы нам не возиться с дженериком Т,
    //мы инлайним функцию, чтобы не затерся тип U
    inline fun <reified U : T> getEntityByType(): List<U> {
        return entityList.filterIsInstance<U>()
    }

    fun deleteEntityById(id: UUID) {
        val entityToRemove = entityList.find { it.id == id }
        if (entityToRemove is Enclosure) {
            for (animal in entityToRemove.animals) {
                entityList.remove(animal as T)
            }
            entityList.remove(entityToRemove as T)
        } else {
            entityList.remove(entityToRemove)
        }
        println("remove done")
    }

    override fun getEnclosureCount(): Int {
        return entityList.filterIsInstance<EnclosureImpl>().size
    }

    override fun createAnimal(species: String): Animal {
        return when (species.lowercase(Locale.getDefault())) {
            "wolf" -> Wolf()
            "parrot" -> Parrot()
            "monkey" -> Monkey()
            "волк" -> Wolf()
            "попугай" -> Parrot()
            "обезьяна" -> Monkey()
            else -> throw IllegalArgumentException("Неподдерживаемый вид животного: $species")
        }
    }

    override fun checkStatusZoo(): String {
        val totalAnimals = entityList.filterIsInstance<Animal>().size
        val totalVisitors = entityList.filterIsInstance<Visitor>().size
        val totalEmployees = entityList.filterIsInstance<Employee>().size
        return "В зоопарке находится $totalVisitors посетителей, $totalEmployees сотрудников и $totalAnimals животных."
    }

    override fun checkStatusVisitors(): String {
        val visitors = entityList.filterIsInstance<Visitor>()
        return if (visitors.isEmpty()) {
            "Список посетителей пуст."
        } else {
            val status = StringBuilder("Список посетителей:\n")
            for (visitor in visitors) {
                status.append(visitor.getStatus(visitor)).append("\n")
            }
            status.toString()
        }
    }

    override fun checkStatusEmployees(): String {
        val employees = entityList.filterIsInstance<Employee>()
        val status = StringBuilder("Список сотрудников:\n")
        for (employee in employees) {
            status.append(employee.getStatus(employee)).append("\n")
        }
        return status.toString()
    }

    override fun checkStatusAnimals(): String {
        val enclosures = entityList.filterIsInstance<Enclosure>()
        val status = StringBuilder("Статус животных в зоопарке:\n")
        for (enclosure in enclosures) {
            for (animal in enclosure.animals) {
                status.append(animal.getStatus(animal))
            }
        }
        return status.toString()
    }

    override fun checkStatusEnclosure(): String {
        val enclosures = entityList.filterIsInstance<Enclosure>()
        val status = StringBuilder("Статус животных в вольере:\n")
        for (enclosure in enclosures) {
            status.append(enclosure.getStatus()).append("\n")
        }
        return status.toString()
    }

    override fun editEmployeeName(oldName: String, newName: String): String {
        val employees = entityList.filterIsInstance<Employee>()
        val employeeToEdit = employees.find { it.name.lowercase() == oldName.lowercase() }
        return if (employeeToEdit != null) {
            employeeToEdit.editName(oldName, newName, employeeToEdit)
            "Сотрудник $oldName изменил имя на $newName."
        } else {
            "Сотрудник с именем $oldName не найден в зоопарке."
        }
    }

    override fun editEmployeePosition(name: String, newPosition: String): String {
        val employees = entityList.filterIsInstance<Employee>()
        val employeeToEdit = employees.find { it.name.lowercase() == name.lowercase() }
        return if (employeeToEdit != null) {
            employeeToEdit.editPosition(name, newPosition, employeeToEdit)
            "Сотрудник $name теперь имеет должность $newPosition."
        } else {
            "Сотрудник с именем $name не найден в зоопарке."
        }
    }

    override fun editVisitorName(oldName: String, newName: String): String {
        val visitors = entityList.filterIsInstance<Visitor>()
        val visitorToEdit = visitors.find { it.name.lowercase() == oldName.lowercase() }
        return if (visitorToEdit != null) {
            visitorToEdit.editName(oldName, newName, visitorToEdit)
            "Посетитель $oldName изменил имя на $newName."
        } else {
            "Посетитель с именем $oldName не найден в зоопарке."
        }
    }

    override fun increaseHungerLevel() {
        val enclosures = entityList.filterIsInstance<Enclosure>()
        for (enclosure in enclosures) {
            enclosure.animals.forEach { it.updateHungerLevel(it) }
        }
    }

    override fun fillStockEnclosure(): String {
        val enclosures = entityList.filterIsInstance<Enclosure>()
        val employees = entityList.filterIsInstance<Employee>()
        val status = StringBuilder()

        for (enclosure in enclosures) {
            if (enclosure.hashMap[enclosure.animals[0].firstFoodType.type.toString()]!! <= 0) {
                val randomEmployee = employees.random()
                val fillStockMessage = randomEmployee.fillStockFood(enclosure, enclosure.animals[0].firstFoodType)
                status.append(fillStockMessage)
            }
            if (enclosure.hashMap[enclosure.animals[0].secondFoodType.type.toString()]!! <= 0) {
                val randomEmployee = employees.random()
                val fillStockMessage = randomEmployee.fillStockFood(enclosure, enclosure.animals[0].secondFoodType)
                status.append(fillStockMessage)
            }
        }
        return status.toString().trim()
    }

    override fun moveAnimals() {
        val enclosures = entityList.filterIsInstance<Enclosure>()
        val status = StringBuilder()
        for (enclosure in enclosures) {
            val animalsInEnclosure = enclosure.animals.toList()
            val numberOfAnimalsToMove = Random.nextInt(animalsInEnclosure.size)
            repeat(numberOfAnimalsToMove) {
                val randomIndex = animalsInEnclosure.indices.random()
                val randomAnimal = animalsInEnclosure[randomIndex]
                val moveResult = enclosure.moveAnimal(randomAnimal, randomIndex)
                status.append(moveResult).append("\n")
            }
        }
        //println(status.toString().trim())
    }

    override fun feedAnimalsInEnclosures() {
        val enclosures = entityList.filterIsInstance<Enclosure>()
        for (enclosure in enclosures) {
            for (animalIndex in 0 until enclosure.animals.size) {
                val animal = enclosure.animals[animalIndex]
                if (animal.hungerLevel >= animal.getHungerLimit()) {
                    animal.eatFromEnclosure(enclosure)
                }
            }
        }
    }

    override fun feedAnimalsByVisitor(visitorName: String, species: String): String {
        val visitors = entityList.filterIsInstance<Visitor>()
        val visitor = visitors.find { it.name == visitorName }
        if (visitor == null) {
            return "Посетитель с именем $visitorName не найден в зоопарке."
        }

        val enclosures = entityList.filterIsInstance<Enclosure>()
        for (enclosure in enclosures) {
            val animal = enclosure.getOpenablePart().find { it.type.equals(species, ignoreCase = true) }
            if (animal != null) {
                visitor.feedAnimal(animal)
                return "${visitor.name} покормил(а) ${animal.type} в открытой части вольера."
            }
        }

        return "Животное типа $species не найдено в открытой части вольера."
    }

    override fun getRandomVisitor(): Visitor? {
        val visitors = entityList.filterIsInstance<Visitor>()
        if (visitors.isNotEmpty()) {
            return visitors.random()
        }
        return null
    }

    override fun getRandomAnimal(): Animal {
        val enclosures = entityList.filterIsInstance<Enclosure>()
        return enclosures.random().animals.random()
    }

    override fun checkStatusOpenablePart() {
        val enclosures = entityList.filterIsInstance<Enclosure>()
        for (i in enclosures.indices) {
            println("$i :${enclosures[i].checkStatusOpenablePart()}")
        }
    }
}
