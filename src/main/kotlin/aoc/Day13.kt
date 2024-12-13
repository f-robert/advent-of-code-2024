package aoc

import kotlin.math.floor

fun main() {

    val re = "^[a-zA-Z ]+: X[+=](\\d+), Y[+=](\\d+)".toRegex()

    fun Double.isInt() = this * 10 == floor(this) * 10

    fun solve(input: List<String>, offset: Long): Long {
        val lines = ArrayList(input)
        var sum = 0L

        while (lines.isNotEmpty()) {
            val (x1, y1) = re.find(lines.removeFirst())!!.destructured
                .let { (a, b) -> a.toLong() to b.toLong() }
            val (x2, y2) = re.find(lines.removeFirst())!!.destructured
                .let { (a, b) -> a.toLong() to b.toLong() }
            val (x, y) = re.find(lines.removeFirst())!!.destructured
                .let { (a, b) -> a.toLong() + offset to b.toLong() + offset }
            if (lines.isNotEmpty()) {
                lines.removeFirst()
            }

            val p = (x * y1 - y * x1).toDouble() / (x2 * y1 - x1 * y2).toDouble()
            val n = (y - p * y2) / y1.toDouble()

            if (n.isInt() && p.isInt()) {
                sum += n.toLong() * 3 + p.toLong()
            }
        }

        return sum
    }

    puzzle(13) {
        part1 { input ->
            solve(input, 0)
        }

        part2 { input ->
            solve(input, 10000000000000)
        }
    }
}
