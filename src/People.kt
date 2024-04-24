open class People(var name: String, var gender: String)

class Visitor(name: String, gender: String) : People(name, gender)

class Employee(name: String, gender: String, var position: String) : People(name, gender)