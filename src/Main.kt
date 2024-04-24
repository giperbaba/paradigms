import java.util.*

fun main() {
    val zoo = Zoo()

    println("\nДля выполнения операций введите команды:")
    println("'add visitor 'name gender'' - добавить посетителя")
    println("'add animal 'type animal'' - добавить животное")
    println("'add employee 'name gender position'' - добавить сотрудника")
    println("'del visitor 'name'' - удалить посетителя")
    println("'del employee 'name' - удалить сотрудника")
    println("'del animal 'animalType'' - удалить животное")
    println("'check status zoo' - статус зоопарка")
    println("'check status visitors' - статус посетителей")
    println("'check status employee' - статус сотрудников")
    println("'check status animals' - статус животных")
    println("'edit name employee 'oldName' 'newName'' - редактировать имя сотрудника")
    println("'edit position employee 'name' 'newPosition'' - редактировать должность сотрудника")
    println("'edit name visitor 'oldName' 'newName'' - редактировать имя посетителя")
    println("Введите 'stop', чтобы завершить программу\n")

    val zooTimer = ZooTimer(zoo)

    val parrotFeeder = Employee("John", "Male", "Parrot Feeder")
    val wolfFeeder = Employee("Alice", "Female", "Wolf Feeder")
    val monkeyFeeder = Employee("Bob", "Male", "Monkey Feeder")

    zoo.addEmployee(parrotFeeder)
    zoo.addEmployee(wolfFeeder)
    zoo.addEmployee(monkeyFeeder)

    zoo.addAnimal("parrot")
    zoo.addAnimal("wolf")
    zoo.addAnimal("monkey")

    zooTimer.start()
    val scanner = Scanner(System.`in`)

    while (true) {
        val input = scanner.nextLine()
        val tokens = input.split(" ")
        when (tokens[0]) {
            "add" -> {
                when (tokens[1]) {
                    "visitor" -> {
                        if (tokens.size == 4) {
                            zoo.addVisitor(Visitor(tokens[2], tokens[3]))
                        } else {
                            println("Некорректный формат добавления посетителя. Используйте 'add visitor 'name gender''")
                        }
                    }
                    "animal" -> {
                        if (tokens.size == 3) {
                            zoo.addAnimal(tokens[2])
                        } else {
                            println("Некорректный формат добавления животного. Используйте 'add animal 'type''")
                        }
                    }
                    "employee" -> {
                        if (tokens.size == 5) {
                            val name = tokens[2]
                            val gender = tokens[3]
                            val position = tokens[4].replace("monkey", "Monkey Feeder").replace("parrot", "Parrot Feeder").replace("wolf", "Wolf Feeder")
                            val employee = Employee(name, gender, position)
                            zoo.addEmployee(employee)
                        } else {
                            println("Некорректный формат добавления сотрудника. Используйте 'add employee 'name gender position''")
                        }
                    }
                    else -> println("Неизвестная команда: ${tokens[1]}")
                }
            }
            "del" -> {
                when (tokens[1]) {
                    "visitor" -> {
                        if (tokens.size == 3) {
                            zoo.deleteVisitor(tokens[2])
                        } else {
                            println("Некорректный формат удаления посетителя. Используйте 'del visitor 'name''")
                        }
                    }
                    "employee" -> {
                        if (tokens.size == 3) {
                            zoo.deleteEmployee(tokens[2])
                        } else {
                            println("Некорректный формат удаления сотрудника. Используйте 'del employee 'name''")
                        }
                    }
                    "animal" -> {
                        if (tokens.size == 3) {
                            zoo.deleteAnimal(tokens[2])
                        } else {
                            println("Некорректный формат удаления животного. Используйте 'del animal 'animalType''")
                        }
                    }
                    else -> println("Неизвестная команда: ${tokens[1]}")
                }
            }
            "voice" -> {
                when (tokens[1]) {
                    "parrot" -> zoo.makeAnimalSound(Parrot())
                    "wolf" -> zoo.makeAnimalSound(Wolf())
                    "monkey" -> zoo.makeAnimalSound(Monkey())
                    else -> println("Неизвестный тип животного для воспроизведения звука: ${tokens[1]}")
                }
            }
            "check" -> {
                when (tokens[2]) {
                    "zoo" -> zoo.checkStatus()
                    "visitors" -> zoo.checkStatusVisitors()
                    "employee" -> zoo.checkStatusEmployees()
                    "animals" -> zoo.checkStatusAnimals()
                    else -> println("Неизвестный тип для проверки статуса: ${tokens[2]}")
                }
            }
            "edit" -> {
                when (tokens[1]) {
                    "name" -> {
                        when (tokens[2]) {
                            "employee" -> {
                                if (tokens.size == 5) {
                                    zoo.editEmployeeName(tokens[3], tokens[4])
                                } else {
                                    println("Некорректный формат редактирования имени сотрудника. Используйте 'edit name employee 'oldName' 'newName''")
                                }
                            }
                            "visitor" -> {
                                if (tokens.size == 5) {
                                    zoo.editVisitorName(tokens[3], tokens[4])
                                } else {
                                    println("Некорректный формат редактирования имени посетителя. Используйте 'edit name visitor 'oldName' 'newName''")
                                }
                            }
                            else -> println("Неизвестный тип для редактирования имени: ${tokens[2]}")
                        }
                    }
                    "position" -> {
                        when (tokens[2]) {
                            "employee" -> {
                                if (tokens.size == 5) {
                                    zoo.editEmployeePosition(tokens[3], tokens[4])
                                } else {
                                    println("Некорректный формат редактирования должности сотрудника. Используйте 'edit position employee 'name' 'newPosition''")
                                }
                            }
                            else -> println("Неизвестный тип для редактирования должности: ${tokens[2]}")
                        }
                    }
                    else -> println("Неизвестная команда редактирования: ${tokens[1]}")
                }
            }
            "stop" -> {
                zooTimer.stop()
                break
            }
            else -> println("Неизвестная команда")
        }
    }
}
