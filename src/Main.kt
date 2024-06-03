import java.util.Scanner
import java.util.Locale

fun main() {
    val zoo = ZooImpl<BaseEntity>()

    println("\nДля выполнения операций введите команды:")

    println("1 - добавить сущность")
    println("2 - удалить сущность")

    println("3 - поставить на паузу программу")
    println("4 - возобновить программу")
    println("5 - завершить программу")

    println("6 parrot - голос попугая")
    println("7 wolf - голос волка")
    println("8 monkey - голос обезьяны")

    println("9 - статус зоопарка")
    println("10 - статус посетителей")
    println("11 - статус сотрудников")
    println("12 - статус животных")
    println("13 - статус вольеров")

    println("14 oldName newName - редактировать имя сотрудника")
    println("15 name newPosition - редактировать должность сотрудника")
    println("16 oldName newName - редактировать имя посетителя")

    val zooTimer = ZooTimer(zoo)

    val vet = Employee("Johny", "Male", "Vet")
    val security = Employee("Alice", "Female", "Security")
    val cleaner = Employee("Bob", "Male", "Cleaner")

    val visitor = Visitor("Lukas", "Male")

    zoo.addNewEntity(vet)
    zoo.addNewEntity(security)
    zoo.addNewEntity(cleaner)
    zoo.addNewEntity(visitor)

    zooTimer.start()
    val scanner = Scanner(System.`in`)

    while (true) {
        val input = scanner.nextLine().lowercase(Locale.getDefault())
        val commands = input.split(" ")
        when (commands[0]) {
            "1" -> {
                println(" 1 - посетителя\n 2 - рабочего\n 3 - животное\n 4 - вольер")
                val value = scanner.nextLine().lowercase(Locale.getDefault())
                when (value) {
                    "1" -> {
                        println("введите имя и пол")
                        val name = scanner.nextLine().lowercase(Locale.getDefault()).split(" ")
                        zoo.addNewEntity(Visitor(name[0], name[1]))
                    }
                    "2" -> {
                        println("введите имя, пол и должность")
                        val name = scanner.nextLine().lowercase(Locale.getDefault()).split(" ")
                        zoo.addNewEntity(Employee(name[0], name[1], name[2]))
                    }
                    "3" -> {
                        println("введите тип животного")
                        val type = scanner.nextLine().lowercase(Locale.getDefault())
                        zoo.addNewEntity(zoo.createAnimal(type))
                    }
                    "4" -> {
                        val newEnclosure = EnclosureImpl()
                        zoo.addNewEntity(newEnclosure)
                    }
                    else -> {
                        println("некорректный формат, выберите номер сущности, которую хотите добавить")
                    }
                }
            }

            "2" -> {
                println(" 1 - посетителя\n 2 - рабочего\n 3 - животное\n 4 - вольер")
                val value = scanner.nextLine().lowercase(Locale.getDefault())
                when (value) {
                    "1" -> {
                        val visitorList = zoo.getEntityByType<Visitor>()
                        for (i in 0 until visitorList.size) {
                            println("${i + 1} : ${visitorList[i].name}")
                        }
                        println("введите индекс посетителя, которого хотите удалить")
                        val answer = scanner.nextLine().lowercase(Locale.getDefault())
                        zoo.deleteEntityById(visitorList[answer.toInt() - 1].id)
                    }
                    "2" -> {
                        val employeeList = zoo.getEntityByType<Employee>()
                        for (i in 0 until employeeList.size) {
                            println("${i + 1} : ${employeeList[i].name}")
                        }
                        println("введите индекс рабочего, которого хотите удалить")
                        val answer = scanner.nextLine()
                        zoo.deleteEntityById(employeeList[answer.toInt() - 1].id)
                    }
                    "3" -> {
                        val animalList = zoo.getEntityByType<Animal>()
                        for (i in 0 until animalList.size) {
                            println("${i + 1} : ${animalList[i].type}")
                        }
                        println("введите индекс животного, которого хотите удалить")
                        val answer = scanner.nextLine().lowercase(Locale.getDefault())
                        zoo.deleteEntityById(animalList[answer.toInt() - 1].id)
                    }
                    "4" -> {
                        val enclosureList = zoo.getEntityByType<EnclosureImpl>()
                        println("введите номер вольера от 1 до ${enclosureList.size}")
                        val answer = scanner.nextLine().lowercase(Locale.getDefault())
                        zoo.deleteEntityById(enclosureList[answer.toInt() - 1].id)
                    }
                    else -> {
                        println("некорректный формат, выберите номер сущности, которую хотите удалить")
                    }
                }
            }

            "3" -> zooTimer.pause()
            "4" -> zooTimer.resume()
            "5" -> {
                zooTimer.stop()
                break
            }

            "6" -> Parrot().makeSound()
            "7" -> Wolf().makeSound()
            "8" -> Monkey().makeSound()

            "9" -> println(zoo.checkStatusZoo())
            "10" -> println(zoo.checkStatusVisitors())
            "11" -> println(zoo.checkStatusEmployees())
            "12" -> println(zoo.checkStatusAnimals())
            "13" -> zoo.checkStatusOpenablePart()

            "14" -> {
                if (commands.size == 3) {
                    println(zoo.editEmployeeName(commands[1], commands[2]))
                } else {
                    println("некорректный формат, используйте'14 oldName newName' для редактирования рабочего")
                }
            }

            "15" -> {
                if (commands.size == 3) {
                    println(zoo.editEmployeePosition(commands[1], commands[2]))
                } else {
                    println("некорректный формат, используйте '15 name newPosition' для редактирования должности")
                }
            }

            "16" -> {
                if (commands.size == 3) {
                    println(zoo.editVisitorName(commands[1], commands[2]))
                } else {
                    println("некорректный формат, используйте '16 oldName newName' для редактирования посетителя")
                }
            }

            else -> println("неизвестная команда")
        }
    }
}
