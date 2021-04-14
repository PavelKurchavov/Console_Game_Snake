val game = Room(20, 20, Snake())

fun main() {
    game.createMouse()
    game.run()
}