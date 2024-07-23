package view

import model.*

class View {

    // Hiển thị danh sách sản phẩm dưới dạng danh sách
    fun displayList(products: List<Product>) {
        if (products.isEmpty()) {
            println("Không có sản phẩm nào phù hợp.")
            return
        }

        println("Danh sách sản phẩm tìm kiếm được:")
        println("------")

        for (product in products) {
            when (product) {
                is Pencil -> {
                    println("Tên sản phẩm: ${product.name}")
                    println("Giá bán: ${product.price}")
                    println("Thương hiệu: ${product.brand}")
                    println("Màu sắc: ${product.color}")
                    println("Chất liệu: ${product.material}")
                    println("Độ cứng: ${product.hardness}")
                }
                is Pen -> {
                    println("Tên sản phẩm: ${product.name}")
                    println("Giá bán: ${product.price}")
                    println("Thương hiệu: ${product.brand}")
                    println("Màu sắc: ${product.color}")
                    println("Chất liệu: ${product.material}")
                    println("Loại mực: ${product.inkType}")
                    println("Độ mịn: ${product.tipSize}")
                }
                is Book -> {
                    println("Tên sản phẩm: ${product.name}")
                    println("Giá bán: ${product.price}")
                    println("Thương hiệu: ${product.brand}")
                    println("Thể loại: ${product.genre}")
                    println("Tác giả: ${product.author}")
                    println("Nhà xuất bản: ${product.publisher}")
                    println("Năm xuất bản: ${product.year}")
                    println("Ngôn ngữ: ${product.language}")
                }
                is Notebook -> {
                    println("Tên sản phẩm: ${product.name}")
                    println("Giá bán: ${product.price}")
                    println("Thương hiệu: ${product.brand}")
                    println("Số trang: ${product.pages}")
                    println("Loại vở: ${product.type}")
                    println("Màu sắc bìa: ${product.coverColor}")
                    println("Chất liệu giấy: ${product.paperMaterial}")
                    println("Kích thước: ${product.size}")
                }
            }
            println("------")
        }
    }

    // Hiển thị danh sách tất cả sản phẩm
    fun displayAllProducts(products: List<Product>) {
        if (products.isEmpty()) {
            println("Danh sách sản phẩm hiện tại trống.")
            return
        }

        println("Danh sách tất cả sản phẩm:")
        println("------")
        displayList(products)
    }
}

