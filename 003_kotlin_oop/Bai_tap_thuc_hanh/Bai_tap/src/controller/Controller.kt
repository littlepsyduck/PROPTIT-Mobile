package controller

import model.*
import view.View

class Controller(val products: MutableList<Product>, val view: View) {

    // Admin functionalities

    val result = mutableListOf<Product>()

    // Thêm sản phẩm mới vào danh sách
    fun addProduct(product: Product) {
        products.add(product)
        println("Sản phẩm đã được thêm thành công.")
    }

    // Xóa sản phẩm khỏi danh sách
    fun deleteProduct(product: Product) {
        if (products.remove(product)) {
            println("Sản phẩm đã được xóa.")
        } else {
            println("Sản phẩm không tìm thấy.")
        }
    }

    fun search(keyWord: String) {
        for (product in products) {
            if (product.equal(keyWord)) {
                result.add(product)
            }
        }
    }

}


