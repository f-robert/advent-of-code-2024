package aoc

private val intListRegex = "-?\\d+".toRegex()

fun String.toIntList() = intListRegex
    .findAll(this)
    .map { it.value.toInt() }
    .toList()

fun String.toLongList() = intListRegex
    .findAll(this)
    .map { it.value.toLong() }
    .toList()

fun generateCombinations(chars: List<Char>, length: Int): List<String> {
    val combinations = mutableListOf<String>()

    fun backtrack(current: StringBuilder, position: Int) {
        if (position == length) {
            combinations.add(current.toString())
            return
        }

        for (char in chars) {
            current.append(char)
            backtrack(current, position + 1)
            current.deleteCharAt(current.length - 1)
        }
    }

    backtrack(StringBuilder(), 0)
    return combinations
}
