package aoc

fun main() {
    puzzle(7) {
        part1(3749L) { input ->
            var sum = 0L

            for (line in input) {
                val longs = line.toLongList()
                val (expected, numbers) = longs.first() to longs.drop(1)
                val combinations = generateCombinations(listOf('+', '*'), numbers.size - 1)

                for (operator in combinations) {
                    var actual = numbers[0]
                    for (i in 1 until numbers.size) {
                        actual = when (operator[i - 1]) {
                            '+' -> actual + numbers[i]
                            '*' -> actual * numbers[i]
                            else -> error("Unknown operator: ${operator[i - 1]}")
                        }
                    }
                    if (actual == expected) {
                        sum += expected
                        break
                    }
                }
            }

            sum
        }

        part2(11387L) { input ->
            var sum = 0L

            for (line in input) {
                val longs = line.toLongList()
                val (expected, numbers) = longs.first() to longs.drop(1)
                val combinations = generateCombinations(listOf('+', '*', '|'), numbers.size - 1)

                for (operator in combinations) {
                    var actual = numbers[0]
                    for (i in 1 until numbers.size) {
                        actual = when (operator[i - 1]) {
                            '+' -> actual + numbers[i]
                            '*' -> actual * numbers[i]
                            '|' -> "$actual${numbers[i]}".toLong()
                            else -> error("Unknown operator: ${operator[i - 1]}")
                        }
                    }
                    if (actual == expected) {
                        sum += expected
                        break
                    }
                }
            }

            sum
        }
    }
}
