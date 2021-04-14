class Snake(x: Int, y: Int, var direction: SnakeDirection = SnakeDirection.DOWN) {
    var isAlive: Boolean = true
    var sections = arrayListOf(SnakeSection(x, y))

    fun selectMove() {
        if(!isAlive) return

        fun move(dx: Int, dy: Int) {

            fun checkAlive(head: SnakeSection) {
                if(head.x < 0 || head.x >= game.width ||
                    head.y < 0 || head.y >= game.height || head in sections) isAlive = false
            }

            var head: SnakeSection = sections[0]
            head = SnakeSection(head.x + dx, head.y + dy)
            checkAlive(head)
            if (!isAlive) return
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