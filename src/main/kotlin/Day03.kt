fun main() {
    puzzle(3) {
        part1(161) { input ->
            var result = 0
            val re = "mul\\((\\d+),(\\d+)\\)".toRegex()

            for (line in input) {
                result += re
                    .findAll(line)
                    .map {
                        val (a, b) = it.destructured
                        a.toInt() * b.toInt()
                    }
                    .sum()
            }

            result
        }

        part2 { input ->
            val re = "mul\\((\\d+),(\\d+)\\)|do\\(\\)|don't\\(\\)".toRegex()
            var result = 0
            var active = true

            for (s in input) {
                for (match in re.findAll(s)) {
                    when (match.value) {
                        "do()" -> active = true
                        "don't()" -> active = false
                        else -> if (active) {
                            val (a, b) = match.destructured
                            result += a.toInt() * b.toInt()
                        }
                    }
                }
            }

            result
        }
    }
}
