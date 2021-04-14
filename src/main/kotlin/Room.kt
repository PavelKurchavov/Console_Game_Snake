import java.awt.event.KeyEvent


class Room(var width: Int, var height: Int, private var snake: Snake) {
    lateinit var mouse: Mouse

    fun run() {
        val keyboardObserver = KeyboardObserver()
        keyboardObserver.start()

        while (snake.isAlive) {
            if (keyboardObserver.hasKeyEvents()) {
                val event = keyboardObserver.eventFromTop

                if (event.keyChar == 'q') return

                when (event.keyCode) {
                    KeyEvent.VK_LEFT -> snake.direction = SnakeDirection.LEFT
                    KeyEvent.VK_RIGHT -> snake.direction = SnakeDirection.RIGHT
                    KeyEvent.VK_UP -> snake.direction = SnakeDirection.UP
                    KeyEvent.VK_DOWN -> snake.direction = SnakeDirection.DOWN
                }
            }
            snake.selectMove()
            print()
            sleep()
        }

        println("Game Over!")
    }

    private fun print() {
        val matrix = Array(height) { IntArray(width) }

        val sections = ArrayList(snake.sections)
        for ((x, y) in sections) {
            matrix[y][x] = 1
        }

        matrix[sections[0].y][sections[0].x] = if (snake.isAlive) 2 else 4

        matrix[mouse.y][mouse.x] = 3


        val symbols = arrayOf(" . ", " o ", " O ", "^_^", "RIP")
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

    fun createMouse() {
        val x = (Math.random() * width).toInt()
        val y = (Math.random() * height).toInt()
        mouse = Mouse(x, y)
    }

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