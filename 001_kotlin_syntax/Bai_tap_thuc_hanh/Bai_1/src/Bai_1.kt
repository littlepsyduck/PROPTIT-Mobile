import kotlin.random.Random

const val max_element = 1000005;
fun solve(n : Int){
    val sum: Long = n * (n + 1) / 2 * 1L
    if(sum % 2 == 1L) {
        println("NO")
        return
    } else{
        println("YES");
        val halfSum = sum / 2;
        val arr1 = mutableListOf<Int>()
        val arr2 = mutableListOf<Int>()
        var tmpSum = 0L

        for(x in n downTo 1){
            if(tmpSum + x.toLong() <= halfSum){
                arr1.add(x)
                tmpSum += x.toLong()
            }
            else{
                arr2.add(x)
            }
        }
        for(x in arr1){
            print("$x ")
        }
        println()
        for(x in arr2){
            print("$x ")
        }
    }
}

fun main() {
    val n = Random.nextInt(1, max_element)
    solve(n)
}