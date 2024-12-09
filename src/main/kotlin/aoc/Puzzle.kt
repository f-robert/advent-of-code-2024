package aoc

fun <T> puzzle(
    day: Int,
    initializer: PuzzleScope<T>.() -> Unit
) where T : Number {
    require(day in 1..25) { "Invalid day: $day" }

    val scope = PuzzleScope<T>().apply(initializer).build()
    val test = readInput("Day${day.toString().padStart(2, '0')}_test")
    val input = readInput("Day${day.toString().padStart(2, '0')}")

    scope.forEach { part ->
        val actual = part.block(test)
        part.check?.let {
            check(it == actual) { "Check failed for part ${part.id}: expected ${part.check}, got $actual" }
        }
        println("Part ${part.id}: ${part.block(input)}")
    }
}

class PuzzleScope<T> internal constructor() where T : Number {

    private val parts = mutableListOf<PartScope<T>>()

    fun part1(check: T? = null, block: (List<String>) -> T) {
        parts.add(PartScope(1, check, block))
    }

    fun part2(check: T? = null, block: (List<String>) -> T) {
        parts.add(PartScope(2, check, block))
    }

    fun build() = parts
}

class PartScope<T> internal constructor(
    val id: Int,
    val check: T? = null,
    val block: (List<String>) -> T
) where T : Number

private fun readInput(name: String) = {}::class.java.classLoader.getResource("$name.txt")
    ?.readText()
    ?.trim()
    ?.lines()
    ?: error("Input $name.txt not found")
