:memo: <span style="color:orange">MOBILE_001_KOTLIN_SYNTAX</span>

# TÌM HIỂU VỀ NGÔN NGỮ KOTLIN

![Picture 1](p1.webp)

## Table of Content

- [TÌM HIỂU VỀ NGÔN NGỮ KOTLIN](#tìm-hiểu-về-ngôn-ngữ-kotlin)
  - [Table of Content](#table-of-content)
  - [I. Giới thiệu Kotlin](#i-giới-thiệu-kotlin)
    - [Nhập xuất trong Kotlin](#nhập-xuất-trong-kotlin)
  - [II. Biến, kiểu dữ liệu](#ii-biến-kiểu-dữ-liệu)
    - [1. Biến](#1-biến)
      - [Khai báo biến](#khai-báo-biến)
      - [String template](#string-template)
    - [2. Kiểu dữ liệu](#2-kiểu-dữ-liệu)
      - [Ép kiểu dữ liệu](#ép-kiểu-dữ-liệu)
      - [Toán tử](#toán-tử)
  - [III. Câu lệnh rẽ nhánh](#iii-câu-lệnh-rẽ-nhánh)
    - [1. Câu lệnh If - Else](#1-câu-lệnh-if-else)
    - [2. Câu lệnh When](#2-câu-lệnh-when)
  - [IV. Vòng lặp](#iv-vòng-lặp)
    - [1. Vòng lặp For](#1-vòng-lặp-for)
    - [2. Vòng lặp While](#2-vòng-lặp-while)
    - [3. Break, Continue, Return](#3-break-continue-return)
      - [Gán nhãn](#gán-nhãn)
  - [V. Các Colections trong Kotlin](#v-các-colections-trong-kotlin)
  - [VI. Null safety](#vi-null-safety)

## I. Giới thiệu Kotlin

> Kotlin là một ngôn ngữ lập trình hiện đại nhưng đã hoàn thiện, được thiết kế để giúp các nhà phát triển hài lòng hơn. Nó ngắn gọn, an toàn, có thể tương tác với Java và các ngôn ngữ khác, đồng thời cung cấp nhiều cách để sử dụng lại mã giữa nhiều nền tảng để lập trình hiệu quả.

- Chương trình đơn giản in ra "Hello, world!":

    ```kotlin
    fun main() {
        println("Hello, world!")
        // Hello, world!
    }
    ```

  - `fun` được sử dụng để khai báo một hàm.
  - Hàm `main()` là nơi chương trình của bạn bắt đầu.
  - Phần thân của hàm được viết trong dấu ngoặc nhọn `{}`.
  - Các hàm `println()` và `print()` in các đối số.
  
### Nhập xuất trong Kotlin

- Nhập dữ liệu từ bàn phím dùng hàm `readln()` hoặc `readLine()`
- Ví dụ:

```kotlin
var x = readln()
var x = readLine()
```

- Xuất dữ liệu ra màn hình dùng hàm `print()` hoặc `println()`
- Ví dụ:

```kotlin
println(x)
```

## II. Biến, kiểu dữ liệu

### 1. Biến

> Tất cả các chương trình cần có khả năng lưu trữ dữ liệu và các biến sẽ giúp bạn thực hiện điều đó.

- Trong Kotlin, bạn có thể khai báo:
  - Biến chỉ đọc (hằng số): `val`.
  - Biến có thể thay đổi: `var`.
- Để gán một giá trị, sử dụng toán tử gán `=`.

#### Khai báo biến

- Cú pháp:

  ```kotlin
  var (val) <tên biến>: <kiểu dữ liệu> = <giá trị>
  var number: Int = 5
  
  var (val) <tên biến> = <giá trị>
  var number = 5
    ```

- Chú ý:
  - Dấu gạch dưới có thể được thêm vào các số để dễ đọc hơn: 1_000_000.
  - Khai báo kiểu float cần thêm f hoặc F: 5.0f.
  - Khai báo kiểu long cần thêm L: 5L.

#### String template

- Sử dụng **String template** để chuyển 1 kiểu dữ liệu bất kì thành string trong dấu ngoặc `" "`:
  - Bắt đầu bằng `$`.
  - Sử dụng `{}` nếu muốn thể hiện một đoạn code.
- Vi dụ:

    ```kotlin
    val customers = 10
    println("There are $customers customers")
    // There are 10 customers

    println("There are ${customers + 1} customers")
    // There are 11 customers
    ```

### 2. Kiểu dữ liệu

> :bulb: Nếu không khai báo kiểu dữ liệu, Kotlin sẽ lấy kiểu dữ liệu dựa vào giá trị truyền vào (type inference).

- Các kiểu dữ liệu cơ bản:

| <center>Phân loại</center>  | Kiểu dữ liệu cơ bản  |
|---|---|
| <center>Integers</center>  | `Byte`, `Short`, `Int`, `Long`  |
| <center>Unsigned integers</center>  | `UByte`, `UShort`, `UInt`, `ULong`  |
| <center>Floating-point numbers</center>  | `Float`, `Double`  |
| <center>Booleans</center>  | `Boolean`  |
| <center>Characters</center>  | `Char`  |
| <center>Strings</center>  | `String`  |

#### Ép kiểu dữ liệu

- Các phương thức cơ bản trong Kotlin bao gồm:

  ```kotlin
    toByte() -> Byte
    toShort() -> Short
    toInt() -> Int
    toLong() -> Long
    toFloat() -> Float
    toDouble() -> Double
    toChar() -> Char
  ```  

- Có 2 dạng ép kiểu:
  - Ép kiểu rộng: Ép kiểu từ kiểu dữ liệu bé -> lớn *không mất dữ liệu*.

  ```kotlin
    var num: Int = 5
    println(num.toDouble())
    // 5.0
  ```

  - Ép kiểu hẹp: Ép kiểu từ kiểu dữ liệu lớn -> bé *có thể xảy ra mất mát dữ liệu*.
  
  ```kotlin
    var num: Double = 5.5
    println(num.toInt())
    // 5
    ```

#### Toán tử

- Trong Kotlin, ta có thể sử dụng các Operator thuần túy và cũng có thể dùng bằng các phương thức:
  - Các toán tử số học:

    | <center>Toán tử</center> | Phương thức |
    |---------|-------------|
    | <center>+</center>       | a.plus(b)   |
    | <center>-</center>       | a.minus(b)  |
    | <center>*</center>       | a.times(b)  |
    | <center>/</center>       | a.div(b)    |
    | <center>%</center>       | a.rem(b)    |

  - Toán tử so sánh:

    | <center>Toán tử</center> | Phương thức         |
    |---------|---------------------|
    | <center>==</center>      | a.equals(b)         |
    | <center>!=</center>      | !a.equals(b)        |
    | <center><</center>       | a.compareTo(b) < 0  |
    | <center><=</center>      | a.compareTo(b) <= 0 |
    | <center>\></center>      | a.compareTo(b) > 0  |
    | <center>\>=</center>     | a.compareTo(b) >= 0 |

## III. Câu lệnh rẽ nhánh

### Câu lệnh If - Else

- Giống các ngôn ngữ đã học.
- Bên cạnh đó, trong Kotlin `if..else` hoạt động như 1 biểu thức trả về (bắt buộc có `else`):

  ```kotlin
    var a: Int = 10
    var b: Int = 15
    var max: Int
    max = if (a>b) a else b
    println(max)
    // 15
    ```

### Câu lệnh When

- Tương tự câu lệnh switch - case ở các ngôn ngữ lập trình khác.

  ```kotlin
    when(expression){ 
        <giá trị 1> -> <câu lệnh 1>
        <giá trị 2> -> <câu lệnh 2>
        ...
        else -> <câu lệnh>
    }
  ```

- Ví dụ:

  ```kotlin
    when(val x = 5){
        2 -> println("x = 2")
        3 -> println("x = 3")
        4 -> println("x = 4")
        5 -> println("x = 5")
        6 -> println("x = 6")
        else -> println("$x is the correct value of x")
    }
  ```

## IV. Vòng lặp

### 1. Vòng lặp For

- Cú pháp:
  
  ```kotlin
    for (item in collection) print(item)
  ```

- Ví dụ:

  ```kotlin
    for (item: Int in ints) {
        // ...
    }
  ```

- `for (i in a..b)`: duyệt từ a đến b (`..` = range) *tăng dần* .
- `for (i in a until b)`: duyệt từ a đến b-1 *tăng dần*.
- `for (i in a .. b step x)`: duyệt từ a đến b với bước nhảy x *tăng dần*.
- `for (i in a downTo b)`: duyệt từ a đến b *giảm dần*.
- `for (i in a downTo b step x)`: duyệt từ a đến b với bước nhảy x *giảm dần*.
- `for (item in collection)`: duyệt từng đối tượng

  ```kotlin
    val animals = arrayOf("cat", "dog", "bat")
    for (x in animals) {
        println(x)
    }

    val animals = arrayOf("cat", "dog", "bat")
    for (x in animals.indices) {
        println(animals[x])
    }
    /* 
    cat
    dog
    bat
    */
    
    val animals = arrayOf("cat", "dog", "bat")
    for ((index, value) in animals.withIndex()) {
        println("the element at $index is $value")
    }
    /* 
    the element at 0 is cat
    the element at 1 is dog
    the element at 2 is bat
    */
  ```

### 2. Vòng lặp While

- Giống với ngôn ngữ đã học.

### 3. Break, Continue, Return

- Giống với ngôn ngữ đã học.

#### Gán nhãn

## V. Các Colections trong Kotlin

![Picture 2](p2.png)

- Các Collections trong Kotlin:

    | <center>Phân loại</center> | Mô tả         |
    |-------------|-----------------|
    | <center>Lists</center>      | Collections có thứ tự của các phần tử         |
    | <center>Sets</center>      | Collections không có thứ tự duy nhất của các phần tử       |
    | <center>Maps</center>       | Tập hợp các cặp key - value trong đó các khóa là duy nhất và chỉ ánh xạ tới một giá trị   |

## VI. Null safety
