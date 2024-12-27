fun main() {
    puzzle(4) {
        part1(18) { input ->
            words(input)
                .filter { it == "XMAS" }
                .count()
        }

        part2(9) { input ->
            words2(input)
                .filter { it in listOf("MSAMS", "SMASM", "MMASS", "SSAMM") }
                .count()
        }
    }
}

private fun words(input:List<String>) = sequence<String> {
    for (element in input) {
        for (x in 0..<input[0].length - 3) {
            val word = element.substring(x, x + 4)

            yield(word)
            yield(word.reversed())
        }
    }

    for (y in 0..<input.size - 3) {
        for (x in 0..<input[0].length) {
            val word = listOf(
                input[y][x],
                input[y + 1][x],
                input[y + 2][x],
                input[y + 3][x]
            ).let { String(it.toCharArray()) }

            yield(word)
            yield(word.reversed())
        }
    }

    for (y in 0..<input[0].length - 3) {
        for (x in 0..<input.size - 3) {
            val word = listOf(
                input[y][x],
                input[y + 1][x + 1],
                input[y + 2][x + 2],
                input[y + 3][x + 3]
            ).let { String(it.toCharArray()) }

            yield(word)
            yield(word.reversed())
        }
    }

    for (y in 0..<input[0].length - 3) {
        for (x in 0..<input.size - 3) {
            val word = listOf(
                input[y][x + 3],
                input[y + 1][x + 2],
                input[y + 2][x + 1],
                input[y + 3][x]
            ).let { String(it.toCharArray()) }

            yield(word)
            yield(word.reversed())
        }
    }
}

private fun words2(input:List<String>) = sequence<String> {
    for (y in 0..<input.size - 2) {
        for (x in 0..<input[0].length - 2) {
            val word = listOf(
                input[y][x],
                input[y][x + 2],
                input[y + 1][x + 1],
                input[y + 2][x],
                input[y + 2][x + 2],
            ).let { String(it.toCharArray()) }

            yield(word)
        }
    }
}
