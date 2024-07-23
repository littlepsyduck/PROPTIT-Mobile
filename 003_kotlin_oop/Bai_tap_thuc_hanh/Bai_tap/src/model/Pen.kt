package model

class Pen(
    name: String,
    price: Int,
    brand: String,
    val color: String,
    val material: String,
    val inkType: String,
    val tipSize: String
) : Product(name, price, brand)