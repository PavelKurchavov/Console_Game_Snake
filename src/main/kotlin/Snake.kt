class Snake(val x: Int, val y: Int) {
    var direction: SnakeDirection? = null
    var isAlive: Boolean = true
    var sections = arrayListOf(SnakeSection(x, y))

    fun move() {
        if(!isAlive) return
        when (direction) {
            SnakeDirection.UP -> move(0, -1)
            SnakeDirection.RIGHT -> move(1, 0)
            SnakeDirection.DOWN -> move(0, 1)
            SnakeDirection.LEFT -> move(-1, 0)
        }
    }

    private fun move(dx: Int, dy: Int) {
        var head: SnakeSection = sections[0]
        head = SnakeSection(head.x + dx, head.y + dy)

        checkBorders(head)
        if(!isAlive) return

        checkBody(head)
        if(!isAlive) return

        val mouse: Mouse = game.mouse
        if(head.x == mouse.x && head.y == mouse.y) {
            sections.add(0, head)
            game.eatMouse()
        } else {
            sections.add(0, head)
            sections.removeLast()
        }
    }

    private fun checkBorders(head: SnakeSection) {
        if((head.x < 0 || head.x >= game.width) ||
           head.y < 0 || head.y >= game.height) isAlive = false
    }

    private fun checkBody(head: SnakeSection) {
        if(sections.contains(head)) isAlive = false
    }


}