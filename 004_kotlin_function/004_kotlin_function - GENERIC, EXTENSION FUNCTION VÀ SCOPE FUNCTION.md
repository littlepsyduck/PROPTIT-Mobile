:memo: <span style="color:orange">MOBILE_004_KOTLIN_FUNCTION</span>

# GENERIC, EXTENSION VÀ SCOPE FUNCTION

![Picture 1](p1.webp)

Generic: Generic là gì? Dùng khi nào? Tại sao phải dùng generic?
Extension function: Khái niệm, cách tạo, sử dụng và lợi ích khi dùng Extension fuction.
Scope function: Scope function là gì? Phân biệt các loại scope function. Phân loại this và it trong scope function.

## Table of Content

- [GENERIC, EXTENSION VÀ SCOPE FUNCTION](#generic-extension-và-scope-function)
  - [Table of Content](#table-of-content)
  - [I. Các tính chất của OOP trong Kotlin](#i-các-tính-chất-của-oop-trong-kotlin)
    - [1. Encapsulation (Đóng gói)](#1-encapsulation-đóng-gói)
    - [2. Inheritance (Kế thừa)](#2-inheritance-kế-thừa)
      - [Các phương thức Overriding](#các-phương-thức-overriding)
      - [Các thuộc tính Overriding](#các-thuộc-tính-overriding)
      - [Trật tự khởi tạo Derived Class](#trật-tự-khởi-tạo-derived-class)
      - [Gọi việc triển khai Superclass](#gọi-việc-triển-khai-superclass)
      - [Quy tắc Overriding](#quy-tắc-overriding)
    - [3. Polymorphism (Đa hình)](#3-polymorphism-đa-hình)
      - [Compile-time Polymorphism](#compile-time-polymorphism)
      - [Runtime Polymorphism](#runtime-polymorphism)
    - [4. Abstraction (Trừu tượng)](#4-abstraction-trừu-tượng)
      - [Abtract Class](#abtract-class)
      - [Interface](#interface)
      - [Giải quyết xung đột Overriding](#giải-quyết-xung-đột-overriding)
  - [II. Backing field](#ii-backing-field)

## I. Các tính chất của OOP trong Kotlin
  
### 1. Encapsulation (Đóng gói)

> Bao gồm các thuộc tính và phương thức liên quan giúp thực hiện hành động trên các thuộc tính đó trong một lớp.
> *Lấy điện thoại di động làm ví dụ. Sản phẩm này bao gồm một máy ảnh, màn hình, thẻ nhớ cùng một số phần cứng và phần mềm khác. Người dùng không cần phải lo lắng về cách các thành phần bên trong kết hợp với nhau.*

- Ví dụ: Yêu cầu của ứng dụng là hiệu trưởng chỉ có thể được xem danh sách tất cả học sinh và thêm học sinh vào danh sách. Đóng gói là cách để thực hiện điều đó.

  ```kotlin
  class Principal(){
    private var studentsName = mutableListOf<String>()
    ...
    
    fun getStudentsName(): List<String>{
      return studentsName
    }
  }
  ```

  - Tên học sinh đã được ẩn đi và chỉ có thể truy cập thông qua phương thức `getStudentsName`. Cách duy nhất để chỉnh sửa nó là can thiệp vào class bằng cách sử dụng `Visibility modifiers`.

### 2. Inheritance (Kế thừa)

> Cho phép người dùng tạo lớp dựa trên đặc điểm và hành vi của các lớp khác bằng cách thiết lập mối quan hệ cha-con.
> *Ví dụ: có nhiều nhà sản xuất chế tạo ra nhiều thiết bị di động chạy Android OS nhưng giao diện người dùng cho từng thiết bị là khác nhau. Nói cách khác, nhà sản xuất kế thừa tính năng của hệ điều hành Android và tuỳ chỉnh dựa trên tính năng đó.*

- Kế thừa hỗ trợ khả năng tái sử dụng. Nó làm giảm số lượng mã mẫu cần phải viết.
- Tất cả `class` trong Kotlin có 1 `superclass` là `Any` - `superclass` mặc định cho một lớp không được khai báo `superclass`.
- `Any` thì có 3 phương thức: `equals()`, `hashCode()`, `toString()`.Các phương thức này đều được định nghĩa cho tất cả các `class` trong Kotlin.
- Trong Kotlin, mọi class mặc định là `final`. Để class có thể kế thừa, cần sử dụng từ khóa `open`:

  ```kotlin
  open class Base // Class is open for inheritance
  ```

- Để khai báo một `supertype` rõ ràng, đặt `type` sau dấu `:` trong `class header`:

  ```kotlin
  open class Base(p: Int)

  class Derived(p: Int) : Base(p)
  ```

- Nếu class có `primary constructor`, thì các subclass phải được khởi tạo bằng các tham số của `primary constructor`.

  ```kotlin
  open class Vehicle(val model: String) {
    init {
        println("Vehicle model is $model")
    }
  }
  class Car(model: String, val color: String) : Vehicle(model) {
    init {
        println("Car color is $color")
    }
  }

  fun main() {
    val myCar = Car("Toyota", "Red")
    // Output:
    // Vehicle model is Toyota
    // Car color is Red
  }
  ```

- Trong trường hợp không có `primary constructor` , mỗi hàm tạo phụ phải khởi tạo kiểu cơ sở bằng cách sử dụng từ khóa super hoặc nó phải ủy quyền cho một hàm tạo khác thực hiện. Lưu ý rằng trong trường hợp này, các hàm tạo phụ khác nhau có thể gọi các hàm tạo khác nhau của kiểu cơ sở.

  ```kotlin
  open class Vehicle {
    constructor(model: String) {
        println("Vehicle model is $model")
    }

    constructor(model: String, year: Int) {
        println("Vehicle model is $model, year is $year")
    }
  }
  class Motorcycle : Vehicle {
    constructor(model: String) : super(model) {
        println("Motorcycle model is $model")
    }

    constructor(model: String, year: Int) : super(model, year) {
        println("Motorcycle model is $model, year is $year")
    }
  }

  fun main() {
    val myMotorcycle1 = Motorcycle("Harley")
    // Output:
    // Vehicle model is Harley
    // Motorcycle model is Harley

    val myMotorcycle2 = Motorcycle("Ducati", 2020)
    // Output:
    // Vehicle model is Ducati, year is 2020
    // Motorcycle model is Ducati, year is 2020
  }

  ```

#### Các phương thức Overriding

- Kotlin yêu cầu các modifier rõ ràng cho các thành viên có thể override và các override.

  ```kotlin
  open class Shape {
    open fun draw() { /*...*/ }
    fun fill() { /*...*/ }
  }

  class Circle() : Shape() {
    override fun draw() { /*...*/ }
  }
  ```

- Nếu thiếu modifier `override` trình biên dịch sẽ báo lỗi.
- Nếu phương thức không `open`, như `Shape.fill()`, không thể khai pháp một phương thức trùng lặp trong `subclass`, dù có sử dụng cụm từ `override`.
- `open` sẽ không có tác dụng khi thêm vào các thành viên của `final class`.
- Nếu một thành viên được đánh dấu là `override` thì cũng là `open`, nên có thể được `override` trong các `subclass`. Nếu muốn cấm `override`, thêm từ khóa `final`.

  ```kotlin
  open class Rectangle() : Shape() {
    final override fun draw() { /*...*/ }
  }
  ```

#### Các thuộc tính Overriding

- Cơ chế `overriding` hoạt động trên các thuộc tính theo cách tương tự như trên các phương thức.
Các thuộc tính được khai báo trên `superclass` sau đó được khai báo lại trên `derived class` phải được bắt đầu bằng `override`, và chúng phải có một kiểu tương thích.
- Mỗi thuộc tính được khai báo có thể được ghi đè bởi một thuộc tính có một trình khởi tạo hoặc bởi một thuộc tính có một phương thức `get`.

  ```kotlin
  open class Shape {
    open val vertexCount: Int = 0
  }

  class Rectangle : Shape() {
    override val vertexCount = 4
  }
  ```

- Có thể ghi đè một thuộc tính `val` bằng một thuộc tính `var`, nhưng không được phép ngược lại.

  ```kotlin
  interface Shape {
    val vertexCount: Int
  }

  class Rectangle(override val vertexCount: Int = 4) : Shape 
  // Always has 4 vertices

  class Polygon : Shape {
    override var vertexCount: Int = 0  
    // Can be set to any number later
  }
  ```

#### Trật tự khởi tạo Derived Class

- Trong quá trình xây dựng `instance` của `derived class`, việc khởi tạo lớp cơ sở được thực hiện như bước đầu tiên (chỉ sau khi đánh giá các đối số cho hàm tạo lớp cơ sở), nghĩa là nó xảy ra trước khi logic khởi tạo của lớp dẫn xuất được chạy.

  ```kotlin
  open class Base(val name: String) {

    init { println("Initializing a base class") }

    open val size: Int =
        name.length.also { println("Initializing size in the base class: $it") }
  }

  class Derived(
    name: String,
    val lastName: String,
  ) : Base(name.replaceFirstChar { it.uppercase() }.also { println("Argument for the base class: $it") }) {

    init { println("Initializing a derived class") }

    override val size: Int =
        (super.size + lastName.length).also { println("Initializing size in the derived class: $it") }
  }

  fun main() {
    println("Constructing the derived class(\"hello\", \"world\")")
    Derived("hello", "world")
    // Constructing the derived class("hello", "world")
    //Argument for the base class: Hello
    //Initializing a base class
    //Initializing size in the base class: 5
    //Initializing a derived class
    //Initializing size in the derived class: 10
  }
  ```

- Điều này có nghĩa là khi hàm tạo lớp cơ sở được thực thi, các thuộc tính được khai báo hoặc ghi đè trong `derived class` vẫn chưa được khởi tạo.
- Do đó, khi thiết kế lớp cơ sở, bạn nên tránh sử dụng `open members` trong các hàm tạo, trình khởi tạo thuộc tính hoặc `init blocks`.

#### Gọi việc triển khai Superclass

- Trong một `derived class` có thể gọi đến hàm và truy cập thuộc tính của lớp cha bằng cách sử dụng từ khóa `super`.

  ```kotlin
  open class Rectangle {
    open fun draw() {
        println("Drawing a rectangle")
    }
    val borderColor: String
        get() = "black"
  }

  class FilledRectangle : Rectangle() {
    override fun draw() {
        super.draw()
        println("Filling the rectangle")
    }

    val fillColor: String
        get() = super.borderColor
  }

  fun main() {
    val filledRectangle = FilledRectangle()
    filledRectangle.draw()
    //Drawing a rectangle
    //Filling the rectangle
    println("Fill color: ${filledRectangle.fillColor}")
    //Fill color: black
  }
  ```

- Bên trong một `inner class`, việc truy cập `super class` của `outer class` được thực hiện bằng cách sử dụng `super` cùng với tên `outer class`: super@Outer.

  ```kotlin
  open class Rectangle {
    open fun draw() { println("Drawing a rectangle") }
    val borderColor: String get() = "black"
  }

  class FilledRectangle: Rectangle() {
    override fun draw() {
        val filler = Filler()
        filler.drawAndFill()
    }

    inner class Filler {
        fun fill() { println("Filling") }
        fun drawAndFill() {
            super@FilledRectangle.draw() // Calls Rectangle's implementation of draw()
            fill()
            println("Drawn a filled rectangle with color ${super@FilledRectangle.borderColor}") // Uses Rectangle's implementation of borderColor's get()
        }
    }
  }

  fun main() {
    val fr = FilledRectangle()
        fr.draw()
  }
  // Drawing a rectangle
  // Filling
  // Drawn a filled rectangle with color black
  ```

#### Quy tắc Overriding

- Nếu một lớp kế thừa nhiều `implementation` của cùng một thành viên từ các `superclass` của nó, thì lớp đó phải `override` thành viên này và cung cấp triển khai riêng của mình (có thể sử dụng một trong những triển khai được kế thừa).
- Để gọi đến các thành phần thuộc các class, interface cha khác nhau thì ta sử dụng `super<Base>`.

  ```kotlin
  open class Rectangle {
    open fun draw() {
        println("Drawing a rectangle")
    }
  }

  interface Polygon {
    fun draw() {
        println("Drawing a polygon")
    }
  }

  class Square : Rectangle(), Polygon {
    override fun draw() {
        super<Rectangle>.draw() // gọi đến Rectangle.draw()
        super<Polygon>.draw() // gọi đến Polygon.draw()
    }
  }

  fun main() {
    val square = Square()
    square.draw()
  }
  ```

### 3. Polymorphism (Đa hình)

> Từ này được phỏng theo và có nguồn gốc từ Hy Lạp poly-, có nghĩa là nhiều, và -morphism, là các dạng. Tính đa hình là khả năng sử dụng các đối tượng khác nhau theo một cách chung.
> *Ví dụ: khi người dùng kết nối loa Bluetooth với điện thoại di động, điện thoại chỉ cần biết có một thiết bị có thể phát âm thanh bằng Bluetooth. Tuy nhiên, có nhiều loa Bluetooth để bạn chọn và điện thoại không cần phải biết cụ thể cách làm việc với từng loa.*

- Tính đa hình được chia thành 2 loại:
  - Đa hình trong Compile-time.
  - Đa hình trong Runtime.

#### Compile-time Polymorphism

- Đa hình trong Compile-time, tên hàm vẫn giống nhau nhưng tham số hoặc kiểu trả về thì khác. Tại thời điểm biên dịch, trình biên dịch sau đó sẽ giải quyết các hàm mà chúng ta đang cố gọi dựa trên kiểu tham số và nhiều hơn nữa.

  ```kotlin
  fun main (args: Array<String>) {
    println(doubleof(4))
    println(doubleof(4.3))
    println(doubleof(4.323))
  }
 
  fun doubleof(a: Int):Int {
    return 2*a
  }
 
  fun doubleOf(a:Float):Float {
    return 2*a
  }
 
  fun doubleof(a:Double):Double {
    return 2.00*a
  }
  // 8
  // 8.6
  // 8.646
  ```

#### Runtime Polymorphism

- Đa hình trong Runtime, trình biên dịch giải quyết lệnh gọi đến các phương thức bị ghi đè/quá tải tại thời gian chạy. Chúng ta có thể đạt được đa hình thời gian chạy bằng cách ghi đè phương thức.

  ```kotlin
  fun main(args: Array<String>){
    val a = Sup()
    a.method1()
    a.method2()
   
    val b = Sum()
    b.method1()
    b.method2()
  }
 
  open class Sup{
    open fun method1(){
       println("printing method 1 from inside Sup")
    }
    fun method2(){
       println("printing method 2 from inside Sup")
    }
  }
 
  class Sum:Sup(){
    override fun method1(){
       println("printing method 1 from inside Sum")
    }  
  }
  // printing method 1 from inside Sup
  // printing method 2 from inside Sup
  // printing method 1 from inside Sum
  // printing method 2 from inside Sup
  ```

### 4. Abstraction (Trừu tượng)

> Là phần mở rộng của việc đóng gói. Với mục đích là ẩn logic triển khai bên trong càng nhiều càng tốt.
> *Ví dụ: để chụp ảnh bằng điện thoại di động, người dùng cần mở ứng dụng máy ảnh, hướng điện thoại đến nơi muốn chụp và nhấp vào nút để chụp ảnh. Họ không cần phải biết cách phát triển ứng dụng máy ảnh hoặc phần cứng máy ảnh trên điện thoại di động thực tế hoạt động như thế nào. Tóm lại, cơ chế nội bộ của ứng dụng máy ảnh và cách máy ảnh trên thiết bị di động chụp ảnh sẽ được tóm tắt để người dùng thực hiện các tác vụ cần thiết.*

- Trong Kotlin, ta có thể sử dụng `abstract class` và `interface` để đạt được tính trừu tượng.

#### Abtract Class

- Từ khóa `abstract` được sử dụng để khai báo `abstract class` trong Kotlin.

- Không thể tạo ra một đối tượng của `abstract class`. Tuy nhiên, nó có thể được kế thừa bởi các `subclass`.

- Các hàm và thuộc tính của `abtract class` là non-abstract.

- Không cần sử dụng `open`.

  ```kotlin
  abstract class Polygon {
    abstract fun draw()
  }

  class Rectangle : Polygon() {
    override fun draw() {
        // draw the rectangle
    }
  }
  ```

- Có thể override một thành viên `non-abtract open` bằng `abtract`.

  ```kotlin
  open class Polygon {
    open fun draw() {
        println("Drawing a polygon")
    }
  }

  abstract class WildShape : Polygon() {
    abstract override fun draw()
  }

  class CrazyShape : WildShape() {
    override fun draw() {
        println("Drawing a crazy shape")
    }
  }

  fun main() {
    val crazyShape = CrazyShape()
    crazyShape.draw() 
    // Output: Drawing a crazy shape
  }
  ```

#### Interface

- Một `interface` có thể chứa các phương thức trừu tượng cũng như các phương thức có nội dung.
- Một `interface` được định nghĩa bằng từ khóa `interface`.

  ```kotlin
  interface MyInterface {
    fun bar()
    fun foo() {
      // optional body
    }
  }
  class Child : MyInterface {
    override fun bar() {
        // body
    }
  }
  ```

- Có thể khai báo các thuộc tính trong `interface`, bản chất khi khai báo một thuộc tính trong `interface` là tạo ra 2 hàm getter/setter cho thuộc tính đó. Nếu là `val` thì chỉ có getter, còn `var` thì có cả getter và setter.

#### Giải quyết xung đột Overriding

- Nếu một lớp `implement` nhiều `interface`, mà những `interface` đó có hàm với tên giống nhau, thì khi gọi hàm `super` trong khi `override`, phải chỉ định hàm đó thuộc `interface` nào.

  ```kotlin
  interface A {
    fun foo() { print("A") }
    fun bar()
  }

  interface B {
    fun foo() { print("B") }
    fun bar() { print("bar") }
  }

  class C : A {
    override fun bar() { print("bar") }
  }

  class D : A, B {
    override fun foo() {
        super<A>.foo()
        super<B>.foo()
    }

    override fun bar() {
        super<B>.bar()
    }
  }

  fun main() {
    val c = C()
    c.foo()  // Output: A
    c.bar()  // Output: bar
    val d = D()
    d.foo()  // Output: AB
    d.bar()  // Output: bar
  }
  ```

## II. Backing field

- Trong Kotlin, `field` chỉ được sử dụng như một phần của thuộc tính để lưu giá trị của nó trong bộ nhớ. `field` không thể được khai báo trực tiếp. Tuy nhiên khi một thuộc tính cần `backing fiels`, Kotlin có thể tự động hỗ trợ.

  ```kotlin
  var counter = 0 // the initializer assigns the backing field directly
    set(value) {
        if (value >= 0)
            field = value
            // counter = value // ERROR StackOverflow: Using actual name 'counter' would make setter recursive
    }
  ```
