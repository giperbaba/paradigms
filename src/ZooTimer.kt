import java.util.Timer
import java.util.TimerTask

class ZooTimer(private val zoo: Zoo, private val timer: Timer = Timer()) {
    private var isPaused: Boolean = false

    fun start() {
        timer.scheduleAtFixedRate(object : TimerTask() {
            override fun run() {
                if (!isPaused) {
                    zoo.increaseHungerLevel() // Увеличиваем уровень голода каждую секунду
                    zoo.feedHungryAnimals() // Проверяем и кормим голодных животных
                }
            }
        }, 0, 1000)
    }

    fun pause() {
        isPaused = true
        println("Zoo timer paused")
    }

    fun resume() {
        isPaused = false
        println("Zoo timer resumed")
    }

    fun stop() {
        timer.cancel()
        timer.purge()
        println("Zoo timer stopped")
    }
}
