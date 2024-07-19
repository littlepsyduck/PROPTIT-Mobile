:memo: <span style="color:orange">MOBILE_004_KOTLIN_FUNCTION</span>

# GENERIC, EXTENSION FUNCTION VÀ SCOPE FUNCTION

![Picture 1](p1.webp)

## Table of Content

- [GENERIC, EXTENSION FUNCTION VÀ SCOPE FUNCTION](#generic-extension-function-và-scope-function)
  - [Table of Content](#table-of-content)
  - [I. Generic](#i-generic)
    - [1. Quy ước trong Generics](#1-quy-ước-trong-generics)
    - [2. Declaration-site variance](#2-declaration-site-variance)
      - [Covariance](#covariance)
      - [Contravariance](#contravariance)
    - [3. Generic functions](#3-generic-functions)
    - [4. Generic constraints](#4-generic-constraints)
    - [5. Sử dụng Generic](#5-sử-dụng-generic)
  - [II. Extension Function](#ii-extension-function)
    - [1. Khai báo](#1-khai-báo)
    - [2. Tính chất](#2-tính-chất)
      - [Extension được xử lý tĩnh](#extension-được-xử-lý-tĩnh)
      - [Nullable receiver](#nullable-receiver)
    - [3. Lợi ích](#3-lợi-ích)
  - [III. Scope Function](#iii-scope-function)
    - [1. Các loại Scope Function](#1-các-loại-scope-function)
      - [Let](#let)
      - [Run](#run)
      - [With](#with)
      - [Apply](#apply)
      - [Also](#also)
    - [2. Phân loại this và it](#2-phân-loại-this-và-it)
      - [this](#this)
      - [it](#it)

## I. Generic

> **Generic** được hiểu là “tham số hoá kiểu dữ kiệu”. **Generic** giúp hỗ trợ việc tạo và sử dụng lại các `class`, `interface`, `method` với các kiểu dữ liệu khác nhau với cùng một mục đích trong khi vẫn kiểm tra được độ an toàn của kiểu trong thời gian biên dịch.

- `Class` trong Kotlin có thể có `type parameters`, giống như trong Java.

  ```kotlin
  class Box<T>(t: T) {
    var value = t
  }
  ```

- `<T>` ở đây chính là **generic**, với việc khai báo như này ta có thể truyền vào tham số `T` các kiểu dữ liệu khác nhau như `String`, `Integer`, `Double`, `Character`,… mà các thức hoạt động của nó vẫn không thay đổi.

  ```kotlin
  class Person<T>(private val data : T) {
    fun showData(){
        println(data)
    }
  }

  fun main(){
    val p1 = Person("A")
    p1.showData()
    // A
    val p2 = Person(1)
    p2.showData()
    // 1
  }
  ```

- Generic hoạt động bằng cách sử dụng `Type inference`. Nghĩa là khả năng của compiler có thể tự xác định kiểu dữ liệu của các tham số dựa trên nội dung của mã.

  ```kotlin
  class List<T> {
    val items: MutableList<T> = mutableListOf()
  }

  fun main() {
    val myList = List<String>()
  }
  ```

- Compiler có thể hiểu kiểu dữ liệu `T` của `List` là `String` nhờ việc ta khai báo `myList` là một list các `String`.

### 1. Quy ước trong Generics

- T - Type (Kiểu dữ liệu bất kỳ thuộc Wrapper class: String, Integer, Long, Float, …)
- E – Element (phần tử – được sử dụng phổ biến trong Collection Framework)
- K – Key (khóa)
- V – Value (giá trị)
- N – Number (kiểu số: Integer, Double, Float, …)

### 2. Declaration-site variance

- Là một tính năng giúp xác định `variance` của các `type parameters` trong `class`, `interface`, `members` của chúng.
- Giúp đảm bảo tính an toàn kiểu khi làm việc với `generic types`.

#### Covariance

- Được đánh dấu bằng từ khóa `out`, xác định một `type parameter` là `covariant`.
- Điều này có nghĩa là kiểu generic chỉ có thể đọc.
- `Covariant type parameters`  chỉ có thể được sử dụng ở `output position`,  ví dụ như `return types` hoặc `read-only properties`.

  ```kotlin
  interface Source<out T> {
    fun nextT(): T
  }

  fun demo(strs: Source<String>) {
    val objects: Source<Any> = strs // This is OK, since T is an out-parameter
    // ...
  }
  ```

#### Contravariance

- Được đánh dấu bằng từ khóa `in`, xác định một `type parameter` là `contravariant`.
- Điều này có nghĩa là kiểu generic chỉ có thể ghi.
- `Contravariant type parameters` chỉ có thể sử dụng ở `input position`, chẳng hạn như các tham số của hàm.

  ```kotlin
  interface Comparable<in T> {
    operator fun compareTo(other: T): Int
  }

  fun demo(x: Comparable<Number>) {
    x.compareTo(1.0) // 1.0 has type Double, which is a subtype of Number
    // Thus, you can assign x to a variable of type Comparable<Double>
    val y: Comparable<Double> = x // OK!
  }
  ```

### 3. Generic functions

- Ngoài `class`, `functions` cũng có thể có `type parameters`. `Type parameter` được đặt ở vị trí trước `class name`.

  ```kotlin
  fun <T> singletonList(item: T): List<T> {
    // ...
  }

  fun <T> T.basicToString(): String { // extension function
    // ...
  }
  ```

- Để gọi `generic function`, xác định `type argument` sau tên hàm. Các type argument có thể được bỏ qua nếu có thể tự xác định được.

  ```kotlin
  val l = singletonList<Int>(1)

  val l = singletonList(1)
  ```

- Ví dụ:

  ```koltin
  fun <T> printItem(item: T) {
    println(item)
  }

  fun main() {
    printItem("Hello") // Gọi generic function với kiểu dữ liệu là String
    printItem(1) // Gọi generic function với kiểu dữ liệu là Int
    printItem(true) // Gọi generic function với kiểu dữ liệu là Boolean
    //Hello
    //1
    //true
  }
  ```

### 4. Generic constraints

- Cho phép xác định các hạn chế về các types có thể sử dụng cho `type arguments` cho một `generic type` hoặc `function`.
- Sử dụng `upper bounds`.

  ```kotlin
  fun <T : Comparable<T>> sort(list: List<T>) {  ... }
  ```

- Kiểu được chỉ định sau dấu hai chấm là `upper bound` , cho biết rằng chỉ có `subtype` của `Comparable<T>` có thể được thay thế cho T.
  
  ```kotlin
  sort(listOf(1, 2, 3)) // OK. Int is a subtype of Comparable<Int>
  sort(listOf(HashMap<Int, String>())) // Error: HashMap<Int, String> is not a subtype of Comparable<HashMap<Int, String>>
  ```

- Upper bound mặc định nếu không được chỉ định là `Any?`.
- Trong `< >` chỉ có thể chỉ định 1 `upper bound`. Nếu cần nhiều hơn 1 `upper bound`, sử dụng where.

  ```kotlin
  fun <T> copyWhenGreater(list: List<T>, threshold: T): List<String>
    where T : CharSequence,
          T : Comparable<T> {
    return list.filter { it > threshold }.map { it.toString() }
  }
  ```

- Type được truyền vào phải thỏa mãn tất cả điều kiện của mệnh đề `where`.

### 5. Sử dụng Generic

- **Khi cần tái sử dụng code**: `Generic` cho phép viết một phần code mà có thể hoạt động với nhiều kiểu dữ liệu khác nhau. Thay vì viết lại cùng một logic cho từng kiểu dữ liệu riêng biệt, có thể sử dụng generic để viết một lần và tái sử dụng cho nhiều kiểu dữ liệu.
- **Đảm bảo kiểm tra kiểu tại compile time**: Generic cho phép xác định kiểu dữ liệu đầu vào mà một phương thức hoặc lớp sẽ hoạt động với. Điều này giúp xác định và ngăn chặn các lỗi kiểu tại compile time, giúp tăng tính chính xác và an toàn của mã.
- **Thường dùng với Collections**: cho phép lưu trữ và truy xuất các kiểu dữ liệu khác nhau một cách an toàn và hiệu quả.
- **Hạn chế kiểu dữ liệu**: Generic cung cấp các ràng buộc và giới hạn cho kiểu dữ liệu, giúp hạn chế việc sử dụng các kiểu không mong muốn và tăng tính chính xác của mã.

## II. Extension Function

> Extension Function là function được định nghĩa ngoài class, nhưng có thể gọi tới các đối tượng của class đó như một thành phần của hàm. Nói cách khác, ta có thể thêm những chức năng mới vào 1 hàm đã có mà không cần phải sửa đổi nó, tạo class mới kế thừa từ nó hay dùng design pattern như Decorator pattern.

:bulb: **Decorator pattern** là 1 design pattern cho phép các hành vi được thêm vào 1 đối tượng 1 cách độc lập, năng động mà không ảnh hưởng tới các đối tượng khác từ cùng class.

### 1. Khai báo

- Để khai báo một `extension function`, ta cần xác định class cần mở rộng, sau đó là tên function và các tham số của nó.

  ```kotlin
  fun String.uppercaseFirst(): String {
    return this.replaceFirstChar { if (it.isLowerCase()) it.uppercase() else it.toString() }
  }

  fun main() {
    val example = "hello world"
    println(example.uppercaseFirst()) // Output: Hello world

    val example2 = "Kotlin"
    println(example2.uppercaseFirst()) // Output: Kotlin
  }

  ```

- Ở đây, ta định nghĩa extension function `uppercaseFirst()` từ class String với chức năng viết hoa chữ cái đầu tiên.
- Từ khoá `this` trong extension function để tham chiếu tới receiver object (được truyền vào trước dấu “`.`”, ở trên là `String`).
- `Extension functions` cho phép mở rộng chức năng của lớp mà không cần thay đổi mã nguồn của lớp đó, giúp tăng tính linh hoạt và tái sử dụng trong Kotlin. Có thể tạo `extension functions` cho các lớp tồn tại như `String`, `List`, `Set`, và cả lớp tự định nghĩa.

  ```kotlin
  // Khai báo extension function ket hop generic
  fun <T> T.print(): T {
    return this
  }

  fun main() {
    val str = "Hello World"
    val result1 = str.print()
    println(result1) // Kết quả: "Hello World"
    val age = 1
    val result2 = age.print()
    println(result2) // Kết quả: 1
  }
  ```

### 2. Tính chất

#### Extension được xử lý tĩnh

- Extension không thực sự sửa đổi class mà chúng extend. Với định nghĩa extension. ta không phải đang thêm các thành phần mới vào 1 class mà chỉ tạo hàm mới có thể gọi với dấu “.” từ biến của kiểu đó.
- `Extension functions` được giải quyết tĩnh tại thời điểm biên dịch (compile time) trong Kotlin. Điều này có nghĩa là trình biên dịch xác định `extension function` thích hợp để gọi dựa trên kiểu khai báo của biến hoặc biểu thức tại thời điểm biên dịch (kiểu mà tham số được định nghĩa lúc xác định hàm), chứ không phải kiểu chạy(kiểu được truyền vào lúc gọi).

  ```kotlin
  open class Shape
  class Rectangle: Shape()

  fun Shape.getName() = "Shape"
  fun Rectangle.getName() = "Rectangle"

  fun printClassName(s: Shape) {
    println(s.getName())
  }

  fun main(){
    printClassName(Rectangle())
  }
  // Shape
  ```

- Dù tham số truyền vào hàm prinClassName là Rectangle() nhưng kết quả vẫn in ra *Shape* vì `extension function` được gọi phụ thuộc vào kiểu khai báo của tham số s ở đây là `Class Shape`.
- Nếu 1 class có hàm thành phần, và 1 extension function được định nghĩa cùng kiểu receiver, cùng tên và áp dụng cho đối số cho trước thì hàm thành phần luôn thắng.

  ```kotlin
  class Example {
    fun printFunctionType() {
        println("Class method")
    }
  }

  fun Example.printFunctionType() {
    println("Extension function")
  }

  fun main() {
    Example().printFunctionType() 
    // Class method
  }
  ```

- Nó có thể sử dụng để overload member function có cùng tên nhưng khác kiểu truyền vào.

  ```kotlin
  class Example {
    fun printFunctionType() {
        println("Class method")
    }
  }

  fun Example.printFunctionType(i: Int) {
    println("Extension function #$i")
  }

  fun main() {
    val example = Example()
    example.printFunctionType() // Calls the class method -> "Class method"
    example.printFunctionType(1) // Calls the extension function -> "Extension function #1"
  }
  ```

#### Nullable receiver

- Extension có thể được định nghĩa với kiểu `nullable receiver`. Những extensions này có thể được gọi trên biến object kể cả khi giá trị của nó là null, và chúng có thể kiém tra this == null trong thân hàm.

- Với cách này, ta có thể gọi toString() trong Kotlin mà không cần kiểm tra null, để kiểm tra diễn biến trong `extension function`.

  ```kotlin
  fun Any?.customToString(): String {
    if (this == null) return "null"
    // After the null check, 'this' is autocast to a non-null type, so the toString() below
    // resolves to the member function of the Any class
    return this.toString()
  }

  fun main() {
    println(null.customToString()) // -> "null"
    println("Hello".customToString()) // -> "Hello"
    println(123.customToString()) // -> "123"
  }
  ```

### 3. Lợi ích

- Extension function có thểm thêm chức năng mới cho class mà không cần thay đổi bản thân class đó. Điều này có nhiều lợi ích như:
  - Thêm các function vào class 1 các tiện lợi.
  - Mở rộng từ các thư viện thứ 3 mà ta không thể chỉnh sửa.
  - Implement Decorator pattern

## III. Scope Function

> Thư viện chuẩn của Kotlin chứa các hàm với mục đích thực thi các khối lệnh bên trong context của 1 object. Khi ta gọi các hàm này qua object với lambda expression, nó sẽ tạo ra 1 temporary scope (phạm vi tạm thời). Trong phạm vi này, ta có thể truy cập vào object không cần tới tên của nó. Các hàm như trên là scope function.

:bulb: Ngữ cảnh đối tượng (context object) là một đối tượng được sử dụng như một ngữ cảnh cho một hành động hoặc một khối mã nào đó như để cung cấp thông tin bổ sung, thêm chức năng cho hành động hoặc khối lệnh…

- Có 5 loại scope function :  `let`, `run`, `with`, `apply` và `also`.
  
### 1. Các loại Scope Function

#### Let

- `Let` scope function nhận object như đầu vào và trả về kết quả của khối lệnh được thực thi trong phạm vi của đối tượng. Object được ánh xạ vào khối lệnh bằng từ khoá `it`.

  ```kotlin
  val str = "ProPTIT"

  str.let{
    println(it.length) // it == str
  }
  // 7
  ```

#### Run

- `Run` scope function giống với `Let`, nhưng với 1 số điểm khác biệt là nó tham chiếu tới context object qua từ khoá `this` thay vì `it`.

  ```kotlin
  val str = "ProPTIT"

  str.run{
    println(this.length) // it == str
  }
  // 7
  ```

#### With

- `With` scope function trong Kotlin cũng tương đồng với `Let` và `Run`, trừ 1 số điểm khác biệt ấy là `with` sẽ truyền object làm tham số vào trong nó thay vì được gọi đến sau object với dấu “.”. Object được tham chiếu tới bằng từ khoá `this`.

  ```kotlin
  val str = "ProPTIT"

  with(str) {
        println(this.length)
  }
  // 7
  ```

#### Apply

- `Apply` scope function là một extension function dùng để tuỳ chỉnh hoặc khởi tạo một object. Nó nhận object làm input và thực thi khối lệnh, khối lệnh sau đó sẽ thay đổi giá trị của các thuộc tính object. `Apply` trả về bản thân đối tượng.

  ```kotlin
  class Person(s: String) {
    var age: Int = 0
    var date: String = ""
  }

  fun main (){
    val p1 = Person("A").apply {
        age = 1
        date = "2"
    }
    
    with(p1){
        println(this.date)
    }
  }
  // 2
  ```

#### Also

- `Also` scope function cũng tương tự như Let, nhưng mà nó sẽ trả về kết quả là bản thân đối tượng. Từ khoá ở đây là `it`.

  ```kotlin
  val str = "ProPTIT"

  str.also {
    println(it.uppercase()) 
  }
  // PROPTIT
  ```

### 2. Phân loại this và it

- Mỗi `scope function` dùng 1 trong 2 cách để tham chiếu tới context object: bằng lambda receiver (this) hay lambda argument (it). Cả 2 cách đều khả thi, mỗi cách đều có ưu và nhược điểm ở các trường hợp khác nhau. Vì vậy có các khuyến nghị cho việc sử dụng chúng.

#### this

- `run`, `with` tham chiếu tới context object như lambda receiver qua từ khoá `this`. Do đó, trong lambdas, object được available giống như ở trong chính các hàm thành phần (class function) gốc.
- Có thể lược bớt this khi truy cập vào thành phần của `receiver object`. Tuy nhiên sẽ gây ra việc khó phân biệt giữa các thành phần receiver với các object hay function bên trong. Vì vậy người ta khuyến nghị để `this` trong các hàm ẩn danh chủ yếu điều khiển các thành phần object qua gọi hàm hoặc gán giá trị cho biến.

#### it

- `let` và `also` tham chiếu tới context object như một lambda argument. Nếu tên đối số không được xác định, object sẽ được truy cập bằng tên mặc định `it`. `it` ngắn gọn hơn `this` và biểu thức với `it` cũng thường dễ đọc hơn.
- Khi gọi các hàm hay thuộc tính của object, ta không có object available hoàn toàn như `this`. Do vậy, `it` sẽ là lựa chọn tốt hơn trong trường hợp này khi mà đối tượng hầu như được dùng như 1 đối số trong hàm được gọi. `it` cũng tốt hơn nếu sử dụng đa biến trong khối lệnh.
