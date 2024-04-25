import java.util.Timer
import java.util.TimerTask

class ZooTimer(private val zoo: Zoo, private val timer: Timer = Timer()) {

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
