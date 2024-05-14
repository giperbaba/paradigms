import java.util.Timer
import java.util.TimerTask

class ZooTimer(private val zoo: Zoo, private val timer: Timer = Timer()) {
    private var isPaused: Boolean = false

    private val moveInterval: Long = 10
    private var tickCounter: Long = 0

    fun start() {
        timer.scheduleAtFixedRate(object : TimerTask() {
            override fun run() {
                if (!isPaused) {
                    zoo.increaseHungerLevel() // Увеличиваем уровень голода каждую секунду
                    zoo.fillStockEnclosure() // Проверяем запас еды
                    zoo.feedAnimalsInEnclosures() // Голодные животные кушают

                    tickCounter++
                    if (tickCounter >= moveInterval) {
                        zoo.moveAnimals() // Перемещаем животных между открытой и закрытой частью
                        tickCounter = 0
                    }
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
