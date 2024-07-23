package model

open class Product(
    val name: String,
    val price: Int,
    val brand: String
) {
    fun equal(keyWord: String): Boolean {
        return name.contains(keyWord) || price.equals(keyWord) || brand.contains(keyWord)
    }
}

