import SnakeDirection.*

class Snake(var direction: SnakeDirection = DOWN) {
    var isAlive: Boolean = true
    var sections = arrayListOf(SnakeSection(1, 1))

    fun move() {
        fun move(dx: Int, dy: Int) {
            val head = SnakeSection(sections.first().x + dx, sections.first().y + dy)
            if (head in sections) {
                isAlive = false
                return
            }
            sections.add(0, head)
            if (head.x == game.mouse.x && head.y == game.mouse.y) eatMouse() else sections.removeLast()
        }

        when (direction) {
            UP -> move(0, -1)
            RIGHT -> move(1, 0)
            DOWN -> move(0, 1)
            LEFT -> move(-1, 0)
        }
    }

    private fun eatMouse() {
        game.createMouse()
    }
}

enum class SnakeDirection {
    UP,
    RIGHT,
    DOWN,
    LEFT
}

