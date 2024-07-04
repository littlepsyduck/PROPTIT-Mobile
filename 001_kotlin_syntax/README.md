# 001_kotlin_syntax

## Tìm hiểu về ngôn ngữ `Kotlin`

* Biến, kiểu dữ liệu
* Câu lệnh rẽ nhánh
* Vòng lặp
* Các Colections trong Kotlin
* Null safety

## Bài tập thực hành

* Bài 1

    Cho đoạn code dưới đây

    ``` kotlin
    import kotlin.random.Random

   const val max_element = 1000005;
    fun solve(n : Int){
        // code
    }

    fun main() {
        val n = Random.nextInt(1, max_element)
        solve(n)
    }
    ```

  Viết tiếp hàm `solve()` để chia các số từ 1,2,..,n thành 2 tập hợp con có tổng bằng nhau.
    In "NO" nếu không khả thi, ngược lại in "YES". Nếu khả thi thì in ra 2 dòng liệt kê các phần tử của từng tập hợp con.

* Bài 2

   Cho đoạn code dưới đây

    ``` kotlin
    import kotlin.random.Random

     const val max_element = 1000005;
    fun solve(n : Int){
        // code
    }

    fun main() {
        val n = Random.nextInt(1, max_element)
        solve(n)
    }
    ```

    Một hoán vị của các số nguyên 1,2,...,n được gọi là đẹp nếu không có phần tử liền kề nào có hiệu bằng 1.
    Cho n, hãy xây dựng một hoán vị đẹp và in ra từng phần tử trong hoán vị nếu tồn tại hoán vị đó. Nếu không in ra "No Solution".

## Yêu cầu nộp bài

* Nộp docs chuẩn bị kiến thức vào thư mục của mình đã gắn link trong [Notion](https://www.notion.so/tristaam/Mobile-D22-Dashboard-7ce23bd097b44deb8c30d2e2b2e909bb)
* Checkout từ nhánh main, đặt tên nhánh mới là TênHĐ (vd: DungHM, TamLT)
* Làm bài tập và nộp file .kt, commit và push vào nhánh mình.
* Chú ý: Khi làm bài code, code mẫu đã cho không được xoá, sửa, có thể thêm các hàm xử lí hoặc các đoạn code vào vị trí bất kì.
  