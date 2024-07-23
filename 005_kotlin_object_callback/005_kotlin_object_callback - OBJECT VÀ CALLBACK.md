:memo: <span style="color:orange">MOBILE_005_KOTLIN_OBJECT_CALLBACK</span>

# OBJECT VÀ CALLBACK

![Picture 1](p1.webp)

- Object
  - Object declaration
  - Object expression
  - Hình thái tương đương của object declaration, object expression trong Java.
- Callback
  - Higher order funtion.
  - Lambda function.
  - Callback là gì? Tại sao phải dùng callback.
  - Nêu ưu nhược điểm của callback.

## Table of Content

- [OBJECT VÀ CALLBACK](#object-và-callback)
  - [Table of Content](#table-of-content)
  - [I. Object](#i-object)
    - [1. Object expressions (Biểu thức đối tượng)](#1-object-expressions-biểu-thức-đối-tượng)
      - [Tạo anonymous object từ scratch](#tạo-anonymous-object-từ-scratch)
      - [Kế thừa anynomous object từ supertypes](#kế-thừa-anynomous-object-từ-supertypes)
      - [Sử dụng anonymous object như kiểu trả về giá trị](#sử-dụng-anonymous-object-như-kiểu-trả-về-giá-trị)
      - [Truy cập các biến từ anonymous object](#truy-cập-các-biến-từ-anonymous-object)
    - [2. Object declarations (Khai báo đối tượng)](#2-object-declarations-khai-báo-đối-tượng)
      - [Data object](#data-object)
      - [Companion object](#companion-object)
    - [3. Khác biệt giữa object expression và declaration](#3-khác-biệt-giữa-object-expression-và-declaration)
  - [II. Callback](#ii-callback)
    - [1. Lambda expression và anonymous function](#1-lambda-expression-và-anonymous-function)
    - [2. Lambda](#2-lambda)
      - [Syntax](#syntax)
      - [Passing trailing lambda](#passing-trailing-lambda)
      - [it: tên ngầm định của một tham số đơn](#it-tên-ngầm-định-của-một-tham-số-đơn)
    - [3. Anonymous function](#3-anonymous-function)
    - [4. Callback](#4-callback)

## I. Object

> Để tạo các object với 1 sự tinh chỉnh nhỏ của 1 số class mà không muốn khai báo 1 subclass mới từ class đó.

- Có 2 loại `Object`: `object expression` và `object declaration`.

### 1. Object expressions (Biểu thức đối tượng)

> Object expression tạo những object từ class ẩn danh (anonymous class) - những class không được khai báo rõ ràng với `class` declaration. Các class này hữu dụng cho mục đích dùng 1 lần. Có thể định nghĩa chúng từ scratch, kế thừa từ class đã có, hoặc implement interfaces. Thực thể của lớp ẩn danh còn gọi là `anonymous object` bởi chú được định nghĩa bằng expression, không phải tên.

#### Tạo anonymous object từ scratch

- `Object expression` bắt đầu bằng từ khoá `object`.
- Nếu chỉ cần 1 `object` không có bất kì nontrivial supertype nào, có thể viết các thành phần của nó trong dấu ngoặc nhọn sau `object`.

  ```kotlin
  fun main() {
    val helloWorld = object {
        val hello = "Hello"
        val world = "World"
        // object expressions extend Any, so `override` is required on `toString()`
        override fun toString() = "$hello $world"
    }

    print(helloWorld)
  }
  // Hello World
  ```

#### Kế thừa anynomous object từ supertypes

- Để tạo `object` của `anonymous class` mà được kế thừa từ 1 `type` hay nhiều types, cần xác định `type` đó sau `object` và dấu `:`. Sau đó implement hoặc override thành phẩn của class nếu ta kế thừa từ nó.

  ```kotlin
  window.addMouseListener(object : MouseAdapter() {
    override fun mouseClicked(e: MouseEvent) { /*...*/ }

    override fun mouseEntered(e: MouseEvent) { /*...*/ }
  })
  ```

- Nếu là 1 supertype có 1 constructor, cần ưu tiên truyền tham số constructor vào trước. Có nhiều supertypes thì có thể xác định bằng comma-delimited list sau dấu `:`.

  ```kotlin
  open class A(x: Int) {
    public open val y: Int = x
  }

  interface B { /*...*/ }

  val ab: A = object : A(1), B {
    override val y = 15
  }
  ```

#### Sử dụng anonymous object như kiểu trả về giá trị

- Khi 1 `anonymous object` được dùng như 1 kiểu của 1 khai báo `local` hoặc `private` mà không phải dạng `inline` (`function` hoặc `property`), tất cả thành phẩn của nó được truy cập thông qua `function` hay `property` đó.

  ```kotlin
  class C {
    private fun getObject() = object {
        val x: String = "x"
    }

    fun printX() {
        println(getObject().x)
    }
  }

  fun main() {
    val c = C()
    c.printX()  
    // x
  }
  ```

- Nếu là `public` hay `private inline` sẽ không truy cập được.
- Nếu function hay property là `public` hoặc `private inline`, kiểu thật sự sẽ là:
  - `Any` nếu anonymous object không có supertype được khai báo.
  - `Supertype được khai báo` nếu chỉ có đúng 1 kiểu.
  - `Kiểu được khai báo cụ thể` nếu có nhiều hơn 1 supertype được khai báo.
- Trong các TH trên, thành phần thêm vào `anonymous object` sẽ không thể được truy cập. Override các thành phần thì có thể truy cập nếu chúng được khai báo trong kiểu thật sự (actual type) của hàm hay thuộc tính.

  ```kotlin
  interface A {
    fun funFromA() {}
  }
  interface B

  class C {
    // The return type is Any; x is not accessible
    fun getObject() = object {
        val x: String = "x"
    }

    // The return type is A; x is not accessible
    fun getObjectA() = object: A {
        override fun funFromA() {}
        val x: String = "x"
    }

    // The return type is B; funFromA() and x are not accessible
    fun getObjectB(): B = object: A, B { // explicit return type is required
        override fun funFromA() {}
        val x: String = "x"
    }
  }

  fun main() {
    val c = C()

    // Accessing the anonymous object directly
    val obj = c.getObject()
    // println(obj.x) // This line would cause an error because x is not accessible

    // Accessing the anonymous object with return type A
    val objA = c.getObjectA()
    objA.funFromA() // This is accessible
    // println(objA.x) // This line would cause an error because x is not accessible

    // Accessing the anonymous object with return type B
    val objB = c.getObjectB()
    // objB.funFromA() // This line would cause an error because funFromA() is not accessible
    // println(objB.x) // This line would cause an error because x is not accessible
  }
  ```

#### Truy cập các biến từ anonymous object

- Code trong `object expession` có thể truy cập biến từ phạm vi bao quanh.

  ```kotlin
  class C {
    val x: String = "x"
    private fun getObject() = object {
        val y = "This is $x"
    }

    fun printX() {
        println(getObject().y)
    }
  }

  fun main() {
    val c = C()
    c.printX()
    // This is x
  }
  ```

### 2. Object declarations (Khai báo đối tượng)

> Object Declaration là một 1 `singleton`. `Singleton pattern` có thể có ích trong các trường hợp, và Kotlin giúp cho việc khai báo `singleton` dễ dàng hơn.

:bulb: Singleton là một class chỉ có thể có một instance (đối tượng). Điều này đảm bảo rằng class chỉ được khởi tạo một lần và có thể được truy cập từ bất kỳ đâu trong chương trình.

  ```kotlin
  object C {
    val x: String = "x"
     fun getObject(): String {
        val y = "This is $x"
         return y
    }
  }

  fun main() {
    print(C.getObject())
    // This is x
  }
  ```

- Khai báo như trên được gọi là `object declaration` (khai báo đối tượng), và nó luôn có 1 cái tên theo sau từ khoá `object`. Giống việc khai báo 1 biến, 1 `object declaration` không phải là 1 `expression`, và nó không thể dùng để gán (assignment statement).
- Sự khởi tạo của 1 object declaration là thread-safe (an toàn luồng) và được hoàn thành từ lần truy cập đầu tiên.
- Để tham chiếu tới object, ta dùng tên của nó trực tiếp.

  ```kotlin
  C.getObject()
  ```

- Các object declaration có thể có supertypes.

  ```kotlin
  object DefaultListener : MouseAdapter() {
    override fun mouseClicked(e: MouseEvent) { ... }

    override fun mouseEntered(e: MouseEvent) { ... }
  }
  ```

- `Object decalaration` không thể là local (không thể được lồng (nested) trực tiếp bên trong 1 hàm), nhưng chúng có thể được `nested` bên trong `object declaration` hoặc `non-inner classes` khác.

:bulb: `Non-inner class` là class không tham chiếu đến thành phần của lớp bên ngoài.

  ```kotlin
  object C{
    object D {
        val x: String = "x"
        fun getObject(): String {
            val y = "This is $x"
            return y
        }
    }
  }
  fun main(){
    println(C.D.getObject())
    // This is x
  }
  ```

#### Data object

- Giống `data class` , ta có thể khai báo một `data object` với từ khóa `data`. Khi đó trình biên dịch sẽ tự động tạo ra 1 số hàm:
  - `toString()` trả về tên của: `data object`
  - `equals()`/`hashCode()`: pair

  ```kotlin
  object MyObject

  fun main() {
    println(MyObject) // MyObject@1f32e575
  }
  ```

- - Khác biệt giữa `data object` và `data class`:
    - Không có `copy()` function. Vì `data object` được tạo với mục đích singleton nên nó sẽ giới hạn các thực thể của class thành 1 thực thể duy nhất, điều có thể bị phá vỡ bởi việc cho phép các bản sao chép của thực thể được tạo ra.
    - Không có `componentN()` function. 1 data object không có bất cứ data properties nào nên không có ích gì khi cố gắng phân rã (destructure) chúng.

#### Companion object

> Companion object chính là 1 loại static nest class trong Java nằm trong các class chính. Khi class chính được gọi tới lần đầu, một thể hiện của lớp này được tạo ra chứa các thuộc tính và phương thức bên trong để các thể hiện của class chính có thể gọi tới. Việc sử dụng gần như giống với static trong Java nhưng rõ ràng đó là một đối tượng thực sự chứ không phải là class static member.

- 1 `object declaration` bên trong class có thể đánh dấu với từ khóa companion.

  ```kotlin
  class MyClass {
    companion object Factory {
        fun create(): MyClass = MyClass()
    }
  }
  ```

- Tên của `companion object` có thể lược bỏ, lúc ấy tên companion sẽ được sử dụng.

  ```koltin
  class MyClass {
    companion object { }
  }

  val x = MyClass.Companion
  ```

- `Class member` có thể truy cập các `private member` của `companion object` tương ứng.
- Tên của `class` được dùng bới chính nó sẽ hoạt động giống như 1 tham chiếu tới `companion object` của `class` (dù có tên hay không).

  ```koltin
  class MyClass1 {
    companion object Named { }
  }

  val x = MyClass1

  class MyClass2 {
    companion object { }
  }

  val y = MyClass2
  ```

- Mặc dù các thành phần của `companion object` giống như thành phần static trong các ngôn ngữ khác, nhưng chúng vẫn là 1 `object` nên có thể **kế thừa** các class hay implement các interface.

  ```koltin
  interface Factory<T> {
    fun create(): T
  }

  class MyClass {
    companion object : Factory<MyClass> {
        override fun create(): MyClass = MyClass()
    }
  }

  val f: Factory<MyClass> = MyClass
  ```

### 3. Khác biệt giữa object expression và declaration

- `Object expression` được thực thi (và khởi tạo) ngay lập tức khi chúng được dùng (Khi object được khởi tạo).
- `Object declaration` được khởi tạo `lazily`(khi được gọi tới mới khởi tạo), trong lần truy cập đầu tiên.

## II. Callback

### 1. Lambda expression và anonymous function

> Lambda expression và anomyous function là các hàm ẩn danh (funtion literals), nghĩa là các hàm không được khai báo mà được truyền trực tiếp như 1 biểu thức.

  ```koltin
  max(strings, { a, b -> a.length < b.length }) 
  ```

- `max` là 1 `high-order function` vì nó nhận giá trị của 1 function làm đối số thứ 2. Đối số này là 1 `expression` mà bản thân nó là 1 `function` → funtion literal. function ấy có thể viết lại như sau.

  ```koltin
  fun compare(a: String, b: String): Boolean = a.length < b.length
  ```

:bulb: Các hàm thông thường luôn chỉ nhận vào các tham số, nhưng với **Higher Oder Function** lại có thể nhận một `funtion` khác như một tham số hoặc có thể trả về một `function`. **Higher-Order Function** có thể thực hiện hai việc sau:
    - Có thể lấy hàm làm tham số.
    - Có thể trả về một hàm.

### 2. Lambda

#### Syntax

- Syntax đầy đủ của lambda expression.

  ```koltin
  val lambda_name : Data_type = { argument_List -> code_body }

  val sum: (Int, Int) -> Int = { x: Int, y: Int -> x + y }
  ```

- 1 `lambda expression` luôn được bọc trong dấu ngoặc nhọn.
- Khai báo tham số trong dạng đầy đủ nằm trong ngoặc nhọn và có thể có optional type annotations (chú thích dữ liệu).
- Phần body sẽ nằm sau dấu “→”.
- Nếu kiểu trả về của lambda không phải unit, expression cuối sẽ được coi là kết quả trả về.
- Nếu bỏ hết các optional annotation.

  ```koltin
  fun main(){
    val sum = { x: Int, y: Int -> x + y }
    println(sum(1,2))
  }
  ```

#### Passing trailing lambda

- Nếu tham số cuối của 1 hàm là 1 hàm thì 1 biểu thức lambda được truyền vào như 1 argument có thể để bên ngoài dấu ngoặc đơn. Cách này được gọi là trailing lambda.

  ```koltin
  // thông thường
  val product = items.fold(1, { acc, e -> acc * e }) 

  // trailing lambda
  val product = items.fold(1) { acc, e -> acc * e }
  ```

:bulb: Nếu chỉ có 1 argument, ta có thể bỏ ngoặc đơn đi.
  
  ```kotlin
  run { println(”Hello”) }
  ```

#### it: tên ngầm định của một tham số đơn

- `Lambda expression` chỉ có 1 tham số là 1 chuyện phổ biến. Lúc này, trình biên dịch có thể phân tích kí hiệu mà không cần bất cứ tham số nào. Tham số không cần phải khai báo và `→` có thể được lược bỏ. Tham số khi ấy sẽ được ngầm định dưới cái tên it.

### 3. Anonymous function

- Các biểu thức lambda ở trên đều thiếu 1 thứ - khả năng xác định các kiểu trả về của function. Trong hầu hết trường hợp, điều này là không cần thiết vì kiểu trả về có thể được chỉ định tự động. Tuy nhiên. nếu bạn cần xác định nó 1 cách cụ thể, ta có thể dùng 1 loại syntax khác gọi là anonymous function.

- 1 aninymous function nhìn khá giống 1 hàm thông thường, trừ việc tên của nó bị lược bỏ và thân của nó có thể là biểu thức hoặc block.

  ```koltin
  fun(x: Int, y: Int): Int = x + y 
  // expression

  fun(x: Int, y: Int): Int {
    return x + y
  } 
  // block
  ```

- Tham số và kiểu trả về được xác định giống hàm thông thường, trừ việc kiểu tham số cũng có thể lược bỏ nếu nó có thể chỉ ra từ context.

### 4. Callback

> Trong Kotlin, một callback function là một hàm được truyền như là một argument đối với một hàm khác. Trong quá trình thực thi hàm đó, hàm callback có thể được gọi vào thời điểm thích hợp.

- `Callback function` thường được sử dụng trong `asynchronous programming` (lập trình bất đồng bộ), `event-driven programming`, hoặc xử lý việc hoàn thành các `long-running tasks`.
- Có thể truyền `callback functions` dưới dạng `lambda expression`, `anonymous function` hoặc `named function`.

  ```koltin
  // A function that uses a callback to process a number
  fun processNumber(number: Int, callback: (Int) -> Int): Int {
    return callback(number)
  }

  fun main() {
    // Define a callback for doubling the number
    val double = { x: Int -> x * 2 }

    // Define a callback for squaring the number
    val square = { x: Int -> x * x }

    // Process the number using the double callback
    val doubledResult = processNumber(5, double)
    println("Doubled Result: $doubledResult") 

    // Process the number using the square callback
    val squaredResult = processNumber(5, square)
    println("Squared Result: $squaredResult") 
  }
  // Doubled Result: 10
  // Squared Result: 25
  ```

- `Callback` có thể được sử dụng để minh họa các khái niệm lập trình hàm trong Kotlin. Trong lập trình hàm, hàm có thể được sử dụng như các biến. Chúng ta có thể gán các hàm ẩn danh vào các biến.
- Bằng cách gán hàm ẩn danh vào biến, chúng ta có thể truyền và sử dụng nó như bất kỳ biến nào khác. Điều này cho phép chúng ta áp dụng các khái niệm của lập trình hàm như truyền hàm vào như một tham số, trả về một hàm từ một hàm khác và sử dụng hàm ẩn danh để thực hiện các phép biến đổi dữ liệu.
