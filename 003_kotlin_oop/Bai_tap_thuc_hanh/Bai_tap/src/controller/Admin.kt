package controller

import model.Book
import model.Notebook
import model.Pen
import model.Pencil

class Admin(private val controller: Controller) {

    fun adminMenu() {
        while (true) {
            println("Chọn chức năng: 1. Xóa sản phẩm, 2. Tìm kiếm sản phẩm, 3. Danh sách sản phẩm , 4. Thoát")
            when (readLine()?.toIntOrNull()) {
                1 -> deleteProduct()
                2 -> searchProduct()
                3 -> viewAllProducts()
                4 -> return
                else -> println("Lựa chọn không hợp lệ")
            }
        }
    }

    private fun addProduct() {
        println("Nhập loại sản phẩm (Pencil, Pen, Book, Notebook):")
        val type = readLine() ?: return

        println("Nhập tên sản phẩm:")
        val name = readLine() ?: return

        println("Nhập giá bán:")
        val price = readLine()?.toIntOrNull() ?: return

        println("Nhập thương hiệu:")
        val brand = readLine() ?: return

        when (type.lowercase()) {
            "pencil" -> {
                println("Nhập màu sắc:")
                val color = readLine() ?: return
                println("Nhập chất liệu:")
                val material = readLine() ?: return
                println("Nhập độ cứng:")
                val hardness = readLine() ?: return
                val pencil = Pencil(name, price, brand, color, material, hardness)
                controller.addProduct(pencil)
            }
            "pen" -> {
                println("Nhập màu sắc:")
                val color = readLine() ?: return
                println("Nhập chất liệu:")
                val material = readLine() ?: return
                println("Nhập loại mực:")
                val inkType = readLine() ?: return
                println("Nhập độ mịn:")
                val tipSize = readLine() ?: return
                val pen = Pen(name, price, brand, color, material, inkType, tipSize)
                controller.addProduct(pen)
            }
            "book" -> {
                println("Nhập thể loại:")
                val genre = readLine() ?: return
                println("Nhập tác giả:")
                val author = readLine() ?: return
                println("Nhập nhà xuất bản:")
                val publisher = readLine() ?: return
                println("Nhập năm xuất bản:")
                val year = readLine()?.toIntOrNull() ?: return
                println("Nhập ngôn ngữ:")
                val language = readLine() ?: return
                val book = Book(name, price, brand, genre, author, publisher, year.toString(), language)
                controller.addProduct(book)
            }
            "notebook" -> {
                println("Nhập số trang:")
                val pages = readLine()?.toIntOrNull() ?: return
                println("Nhập loại vở:")
                val type = readLine() ?: return
                println("Nhập màu sắc bìa:")
                val coverColor = readLine() ?: return
                println("Nhập chất liệu giấy:")
                val paperMaterial = readLine() ?: return
                println("Nhập kích thước:")
                val size = readLine() ?: return
                val notebook = Notebook(name, price, brand, pages, type, coverColor, paperMaterial, size)
                controller.addProduct(notebook)
            }
            else -> println("Loại sản phẩm không hợp lệ")
        }
    }

    private fun deleteProduct() {
        println("Nhập tên sản phẩm cần xóa:")
        val name = readLine() ?: return
        val product = controller.products.find { it.name.equals(name, ignoreCase = true) }
        if (product != null) {
            controller.deleteProduct(product)
            println("Sản phẩm đã được xóa.")
        } else {
            println("Sản phẩm không tìm thấy.")
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



