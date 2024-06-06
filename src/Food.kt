abstract class Food {
    open val value: Int = 0
    abstract val type: FoodType
    open val capacity: Int = 0
}