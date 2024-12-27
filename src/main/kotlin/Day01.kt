import kotlin.math.abs

fun main() {
    puzzle(1) {
        part1(11) { input ->
            val (left, right) = lists(input)

            left.sorted()
                .zip(right.sorted())
                .sumOf { (l, r) -> abs(l - r) }
        }

        part2(31) { input ->
            var distance = 0
            val (left, right) = lists(input)
            val count = left.groupingBy { it }.eachCount()

            for (i in right.indices) {
                distance += (count[right[i]] ?: 0) * right[i]
            }

            distance
        }
    }
}

private fun lists(input: List<String>): Pair<List<Int>, List<Int>> {
    val left = mutableListOf<Int>()
    val right = mutableListOf<Int>()

    for (line in input) {
        val (l, r) = line.toIntegers()

        left += l
        right += r
    }

    return left to right
}
