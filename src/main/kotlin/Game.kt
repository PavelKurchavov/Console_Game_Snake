val game = Room(20, 20, Snake(10, 10))

fun main() {
    game.createMouse()
    game.run()
}