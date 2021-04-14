class Snake(x: Int, y: Int, var direction: SnakeDirection = SnakeDirection.DOWN) {
    var isAlive: Boolean = true
    var sections = arrayListOf(SnakeSection(x, y))

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
            SnakeDirection.UP -> move(0, -1)
            SnakeDirection.RIGHT -> move(1, 0)
            SnakeDirection.DOWN -> move(0, 1)
            SnakeDirection.LEFT -> move(-1, 0)
        }
    }

    private fun eatMouse() {
        game.createMouse()
    }
}

