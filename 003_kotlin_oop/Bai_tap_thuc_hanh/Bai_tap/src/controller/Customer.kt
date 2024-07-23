package controller

class Customer(private val controller: Controller) {
    fun customerMenu() {
        while (true) {
            println("Chọn chức năng: 1. Tìm kiếm sản phẩm, 2. Danh sách sản phẩm, 3. Thoát")
            when (readLine()?.toIntOrNull()) {
                1 -> searchProduct()
                2 -> viewAllProducts()
                3 -> return
                else -> println("Lựa chọn không hợp lệ")
            }
        }
    }

    private fun searchProduct() {
        println("Gõ tên tìm kiếm:")
        val query = readLine() ?: return
        println("Chọn hiển thị dạng (Danh sách):")
        val displayFormat = readLine() ?: return
        controller.search(query)
        if (displayFormat.equals("Danh sách", ignoreCase = true)) {
            controller.view.displayList(controller.result)
        } else {
            println("Định dạng hiển thị không hợp lệ")
        }
    }

    private fun viewAllProducts() {
        controller.view.displayAllProducts(controller.products)
    }
}
