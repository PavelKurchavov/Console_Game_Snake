val game = Room(20, 20, Snake(10, 10))

fun main() {
    game.snake.direction = SnakeDirection.DOWN
    game.createMouse()
    game.run()
}