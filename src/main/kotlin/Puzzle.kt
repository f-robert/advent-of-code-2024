fun <T> puzzle(
    day: Int,
    initializer: PuzzleScope<T>.() -> Unit
) where T : Number {
    require(day in 1..25) { "Invalid day: $day" }

    fun readInput(name: String) = {}::class.java.classLoader
        .getResource("$name.txt")
        ?.readText()
        ?.trim()
        ?.lines()
        ?: error("Input $name.txt not found")

    val scope = PuzzleScope<T>().apply(initializer).build()
    val d = day.toString().padStart(2, '0')
    val input = readInput("Day$d")

    scope.forEach { part ->
        part.check?.let { expected ->
            val actual = part.block(readInput("Day${d}_test"))
            check(actual == expected) {
                "Check failed for part ${part.id}: expected $expected, actual $actual"
            }
        }

        println("Part ${part.id}: ${part.block(input)}")
    }
}

class PuzzleScope<T> internal constructor() where T : Number {

    private val parts = mutableListOf<PartScope<T>>()

    fun part1(check: T? = null, block: (List<String>) -> T) {
        parts += PartScope(1, check, block)
    }

    fun part2(check: T? = null, block: (List<String>) -> T) {
        parts += PartScope(2, check, block)
    }

    internal fun build() = parts.toList()
}

class PartScope<T> internal constructor(
    val id: Int,
    val check: T? = null,
    val block: (List<String>) -> T
) where T : Number
