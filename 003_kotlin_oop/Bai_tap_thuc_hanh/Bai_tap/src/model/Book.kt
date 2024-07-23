package model

class Book(
    name: String,
    price: Int,
    val genre: String,
    val author: String,
    val publisher: String,
    val year: String,
    val language: String,
    language1: String
) : Product(name, price, publisher)