import controller.ContactController
import view.ContactView

fun main() {
    val controller = ContactController()
    val view = ContactView(controller)
    controller.start()
}
