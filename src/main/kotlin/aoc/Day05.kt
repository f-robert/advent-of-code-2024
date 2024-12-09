package aoc

fun main() {
    puzzle(5) {
        part1(143) { input ->
            val (rules, updates) = parse(input)

            updates.sumOf { update ->
                if (update.zipWithNext().all { (a, b) -> rules[a]?.contains(b) == true }) update[update.size / 2] else 0
            }
        }

        part2(123) { input ->
            val (rules, updates) = parse(input)

            updates
                .filterNot { update ->
                    update.zipWithNext().all { (a, b) ->
                        rules[a]?.contains(b) == true
                    }
                }.sumOf { update ->
                    val correct = update.sortedWith { a, b ->
                        rules[a]?.indexOf(b) ?: -1
                    }
                    correct[correct.size / 2]
                }
        }
    }
}

private fun parse(input: List<String>): Pair<Map<Int, List<Int>>, List<List<Int>>> {
    val rules = mutableMapOf<Int, MutableList<Int>>()
    val updates = mutableListOf<List<Int>>()
    var isRules = true

    for (line in input) {
        when {
            line.isEmpty() -> isRules = false
            isRules -> {
                val (first, second) = line.split("|").map(String::toInt)
                rules.getOrPut(first) { mutableListOf() }.add(second)
            }
            else -> {
                val pages = line.split(",").map(String::toInt)
                updates.add(pages)
            }
        }
    }

    return rules to updates
}
