import controller.Admin
import controller.Controller
import controller.Customer
import model.Book
import model.Pen
import model.Pencil
import view.View

fun main() {
    val products = mutableListOf(
        Pencil("Bút chì", 5000, "Thiên Long", "Đen", "Gỗ", "HB"),
        Pen("Bút mực", 10000, "Thiên Long", "Đen", "Nhựa", "Mực dầu", "0.5mm"),
        Book("Kí Ức Đen", 50000, "Kim Đồng", "Tiểu thuyết", "Nguyễn Nhật Ánh", "Kim Đồng", 2010.toString(), "Tiếng Việt"),
        Book("Đắc Nhân Vật", 100000, "NXB Trẻ", "Kỹ năng sống", "Dale Carnegie", "NXB Đồng Đen", 2010.toString(), "Tiếng Việt")
    )

    val view = View()
    val controller = Controller(products, view)

    println("Bạn là: 1. Admin, 2. Customer")
    when (readLine()?.toIntOrNull()) {
        1 -> {
            println("Nhập tài khoản admin:")
            val username = readLine()
            println("Nhập mật khẩu admin:")
            val password = readLine()
            if (username == "admin" && password == "admin") {
                Admin(controller).adminMenu()
            } else {
                println("Tài khoản hoặc mật khẩu không đúng")
            }
        }
        2 -> Customer(controller).customerMenu()
        else -> println("Lựa chọn không hợp lệ")
    }
}

