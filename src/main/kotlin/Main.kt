import controller.Controller
import model.Model
import view.View

fun main() {
    val view = View()
    val model = Model()
    val controller = Controller(view, model)
    controller.run()
}
