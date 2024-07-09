import kotlin.random.Random

const val max_element = 1000005;
fun solve(n : Int){
    if(n == 2 || n == 3){
        println("No Solution")
        return
    } else {
        for(i in 2..n step 2) print("$i ")
        for(i in 1..n step 2) print("$i ")
    }
}

fun main() {
    val n = Random.nextInt(1, max_element)
    solve(n)
}