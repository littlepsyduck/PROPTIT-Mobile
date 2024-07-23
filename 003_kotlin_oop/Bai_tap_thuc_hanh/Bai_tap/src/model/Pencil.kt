package model

class Pencil(
    name: String,
    price: Int,
    brand: String,
    val color: String,
    val material: String,
    val hardness: String
) : Product(name, price, brand)