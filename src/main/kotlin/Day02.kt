import java.util.*
import kotlin.math.abs

fun main() {
    puzzle(2) {
        part1(2) { input ->
            input
                .map(String::toIntegers)
                .count { it.safe() && it.monotonic() }
        }

        part2(4) { input ->
            var number = 0

            for (line in input) {
                val l = LinkedList(line.toIntegers())

                for (i in -1..<l.size) {
                    val levels = LinkedList(l)
                    if (i >= 0) {
                        levels.removeAt(i)
                    }

                    if (levels.safe() && levels.monotonic()) {
                        number++
                        break
                    }
                }
            }

            number
        }
    }
}

private fun Iterable<Int>.safe() = zipWithNext()
    .map { (a, b) -> abs(a - b) }
    .all { it in 1..3 }

private fun Iterable<Int>.monotonic() =
    zipWithNext().all { (a, b) -> a < b } || zipWithNext().all { (a, b) -> a > b }
