package model

class Notebook(
    name: String,
    price: Int,
    brand: String,
    val pages: Int,
    val type: String,
    val coverColor: String,
    val paperMaterial: String,
    val size: String
) : Product(name, price, brand)