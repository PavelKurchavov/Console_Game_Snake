import java.awt.GridBagLayout
import java.awt.event.FocusEvent
import java.awt.event.FocusListener
import java.awt.event.KeyEvent
import java.awt.event.KeyListener
import java.util.*
import java.util.concurrent.ArrayBlockingQueue
import javax.swing.JFrame
import kotlin.system.exitProcess


class KeyboardObserver : Thread() {
    private val keyEvents: Queue<KeyEvent> = ArrayBlockingQueue(100)
    private var frame = JFrame("KeyPress Tester")
    override fun run() {
        frame = JFrame("KeyPress Tester")
        frame.title = "Transparent JFrame Demo"
        frame.defaultCloseOperation = JFrame.EXIT_ON_CLOSE
        frame.isUndecorated = true
        frame.setSize(400, 400)
        frame.extendedState = JFrame.MAXIMIZED_BOTH
        frame.layout = GridBagLayout()
        frame.opacity = 0.0f
        frame.isVisible = true
        frame.addFocusListener(object : FocusListener {
            override fun focusGained(e: FocusEvent) {
                //do nothing
            }

            override fun focusLost(e: FocusEvent) {
                exitProcess(0)
            }
        })
        frame.addKeyListener(object : KeyListener {
            override fun keyTyped(e: KeyEvent?) {
                //do nothing
            }

            override fun keyReleased(e: KeyEvent?) {
                //do nothing
            }

            override fun keyPressed(e: KeyEvent?) {
                keyEvents.add(e)
            }
        })
    }

    fun hasKeyEvents(): Boolean {
        return !keyEvents.isEmpty()
    }

    val eventFromTop: KeyEvent
        get() = keyEvents.poll()
}