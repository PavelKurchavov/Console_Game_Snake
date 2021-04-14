import java.awt.event.KeyEvent
import SnakeDirection.*

class Room(var width: Int, var height: Int, private var snake: Snake) {
    lateinit var mouse: Mouse

    fun run() {
        try {
            val keyboardObserver = KeyboardObserver()
            keyboardObserver.start()

            while (snake.isAlive) {
                if (keyboardObserver.hasKeyEvents()) {
                    val event = keyboardObserver.eventFromTop

                    if (event.keyChar == 'q') return

                    with(snake) {
                        when (event.keyCode) {
                            KeyEvent.VK_LEFT -> direction = LEFT
                            KeyEvent.VK_RIGHT -> direction = RIGHT
                            KeyEvent.VK_UP -> direction = UP
                            KeyEvent.VK_DOWN -> direction = DOWN
                        }
                    }
                }
                snake.move()
                print()
                sleep()
            }
        } catch (e: ArrayIndexOutOfBoundsException) { snake.isAlive = false }
        println("Game Over!")
    }

    private fun print() {
        val matrix = Array(height) { IntArray(width) }
        val sections = snake.sections
        val symbols = arrayOf(" . ", " o ", " O ", "^_^", "RIP")

        for ((x, y) in sections) {
            matrix[y][x] = 1
        }

        matrix[sections.first().y][sections.first().x] = if (snake.isAlive) 2 else 4

        matrix[mouse.y][mouse.x] = 3

        for (y in 0 until height) {
            for (x in 0 until width) {
                print(symbols[matrix[y][x]])
            }
            println()
        }
        println()
        println()
        println()
    }

    fun createMouse() = Mouse((Math.random() * width).toInt(), (Math.random() * height).toInt()).also { mouse = it }

    private fun sleep() {
        try {
            val level = snake.sections.size
            val delayStep = 20
            val initialDelay = 520
            val delay = if (level < 15) initialDelay - delayStep * level else 200
            Thread.sleep(delay.toLong())
        } catch (ignored: InterruptedException) {
        }
    }

}


