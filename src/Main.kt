import java.util.*

fun main() {
    val zoo: Zoo = ZooImpl()

    println("\nДля выполнения операций введите команды:")

    println("'add visitor 'name gender'' - добавить посетителя")
    println("'add animal 'type animal'' - добавить животное")
    println("'add employee 'name gender position'' - добавить сотрудника")

    println("'del visitor 'name'' - удалить посетителя")
    println("'del employee 'name' - удалить сотрудника")
    println("'del animal 'animalType'' - удалить животное")

    println("voice parrot")
    println("voice wolf")
    println("voice monkey")

    println("'check status zoo' - статус зоопарка")
    println("'check status visitors' - статус посетителей")
    println("'check status employee' - статус сотрудников")
    println("'check status animals' - статус животных")

    println("'edit name employee 'oldName' 'newName'' - редактировать имя сотрудника")
    println("'edit position employee 'name' 'newPosition'' - редактировать должность сотрудника")
    println("'edit name visitor 'oldName' 'newName'' - редактировать имя посетителя")

    println("Введите 'pause', чтобы поставить на паузу программу\n")
    println("Введите 'resume', чтобы возобновить программу\n")
    println("Введите 'stop', чтобы завершить программу\n")

    println("'add enclosure' - добавить вольер")
    println("'del enclosure' - удалить вольер")
    println("check status openable")

    val zooTimer = ZooTimer(zoo)

    val vet = Employee("John", "Male", "Vet")
    val security = Employee("Alice", "Female", "Security")
    val cleaner = Employee("Bob", "Male", "Cleaner")

    val visitor = Visitor("Yuri", "Male")

    zoo.addEmployee(vet)
    zoo.addEmployee(security)
    zoo.addEmployee(cleaner)

    zoo.addVisitor(visitor)

    zooTimer.start()
    val scanner = Scanner(System.`in`)

    while (true) {
        val input = scanner.nextLine().lowercase(Locale.getDefault())
        val tokens = input.split(" ")
        when (tokens[0]) {
            "add" -> {
                when (tokens[1]) {
                    "visitor" -> {
                        if (tokens.size == 4) {
                            println(zoo.addVisitor(Visitor(tokens[2], tokens[3])))
                        } else {
                            println("Некорректный формат добавления посетителя. Используйте 'add visitor 'name gender''")
                        }
                    }

                    "animal" -> {
                        if (tokens.size == 3) {
                            println(zoo.addAnimal(tokens[2]))
                        } else {
                            println("Некорректный формат добавления животного. Используйте 'add animal 'type''")
                        }
                    }

                    "employee" -> {
                        if (tokens.size == 5) {
                            val name = tokens[2]
                            val gender = tokens[3]
                            val position =
                                tokens[4].replace("monkey", "Monkey Feeder").replace("parrot", "Parrot Feeder")
                                    .replace("wolf", "Wolf Feeder")
                            val employee = Employee(name, gender, position)
                            println(zoo.addEmployee(employee))
                        } else {
                            println("Некорректный формат добавления сотрудника. Используйте 'add employee 'name gender position''")
                        }
                    }

                    "enclosure" -> {
                        println(zoo.addEnclosure())
                    }

                    else -> println("Неизвестная команда: ${tokens[1]}")
                }
            }

            "del" -> {
                when (tokens[1]) {
                    "visitor" -> {
                        if (tokens.size == 3) {
                            println(zoo.deleteVisitor(tokens[2]))
                        } else {
                            println("Некорректный формат удаления посетителя. Используйте 'del visitor 'name''")
                        }
                    }

                    "employee" -> {
                        if (tokens.size == 3) {
                            println(zoo.deleteEmployee(tokens[2]))
                        } else {
                            println("Некорректный формат удаления сотрудника. Используйте 'del employee 'name''")
                        }
                    }

                    "animal" -> {
                        if (tokens.size == 3) {
                            println(zoo.deleteAnimal(tokens[2]))
                        } else {
                            println("Некорректный формат удаления животного. Используйте 'del animal 'animalType''")
                        }
                    }
                    "enclosure" -> {
                        println("Введите индекс вольера для удаления: 0-${zoo.getEnclosureCount()-1}")
                        val index = scanner.nextInt()
                        scanner.nextLine() // Consume newline
                        println(zoo.deleteEnclosure(index))
                    }

                    else -> println("Неизвестная команда: ${tokens[1]}")
                }
            }

            "voice" -> {
                when (tokens[1]) {
                    "parrot" -> Parrot().makeSound()
                    "wolf" -> Wolf().makeSound()
                    "monkey" -> Monkey().makeSound()
                    else -> println("Неизвестный тип животного для воспроизведения звука: ${tokens[1]}")
                }
            }

            "check" -> {
                when (tokens[2]) {
                    "zoo" -> println(zoo.checkStatusZoo())
                    "visitors" -> println(zoo.checkStatusVisitors())
                    "employee" -> println(zoo.checkStatusEmployees())
                    "animals" -> println(zoo.checkStatusAnimals())
                    "openable" -> zoo.checkStatusOpenablePart()
                    else -> println("Неизвестный тип для проверки статуса: ${tokens[2]}")
                }
            }

            "edit" -> {
                when (tokens[1]) {
                    "name" -> {
                        when (tokens[2]) {
                            "employee" -> {
                                if (tokens.size == 5) {
                                    println(zoo.editEmployeeName(tokens[3], tokens[4]))
                                } else {
                                    println("Некорректный формат редактирования имени сотрудника. Используйте 'edit name employee 'oldName' 'newName''")
                                }
                            }

                            "visitor" -> {
                                if (tokens.size == 5) {
                                    println(zoo.editVisitorName(tokens[3], tokens[4]))
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
                                    println(zoo.editEmployeePosition(tokens[3], tokens[4]))
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

            "pause" -> {
                zooTimer.pause()
            }

            "resume" -> {
                zooTimer.resume()
            }

            "stop" -> {
                zooTimer.stop()
                break
            }
            else -> println("Неизвестная команда")
        }
    }
}