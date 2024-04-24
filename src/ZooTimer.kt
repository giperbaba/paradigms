import java.util.*

class ZooTimer(private val zoo: Zoo) {
    private val timer = Timer()

    fun start() {
        timer.scheduleAtFixedRate(object : TimerTask() {
            override fun run() {
                zoo.increaseHungerLevel() // Увеличиваем уровень голода каждую секунду
                zoo.feedHungryAnimals() // Проверяем и кормим голодных животных
            }
        }, 0, 1000)
    }

    fun stop() {
        timer.cancel()
        timer.purge()
    }
}