package aoc

fun main() {
    puzzle(6) {
        part1(41) { input ->
            val map = input.map { line ->
                line.replace("[\\\\^v<>]".toRegex(), ".").toCharArray()
            }

            var x = -1
            var y = -1
            var direction = Direction.UP

            loop@ for (j in input.indices) {
                for (i in input[j].indices) {
                    if (input[j][i] in "^v<>") {
                        x = i
                        y = j
                        direction = Direction.fromSymbol(input[j][i])
                        break@loop
                    }
                }
            }

            val positions = mutableSetOf(x to y)

            while (true) {
                val (newX, newY) = direction.forward(x, y)
                if (newY in 0..<map.size && newX in 0..<map[y].size) {
                    if (map[newY][newX] == '#') {
                        direction = direction.turn()
                    } else {
                        x = newX
                        y = newY
                        positions += x to y
                    }
                } else {
                    break
                }
            }

            positions.count()
        }

        part2(6) { input ->
            var obstructionCount = 0

            for (oy in input.indices) {
                for (ox in input.indices) {
                    if (input[oy][ox] in "#^v<>") {
                        continue
                    }

                    val map = input.map { line ->
                        line.replace("[\\\\^v<>]".toRegex(), ".").toCharArray()
                    }
                    map[oy][ox] = 'O'
                    var x = -1
                    var y = -1
                    var direction = Direction.UP

                    loop@ for (j in input.indices) {
                        for (i in input[j].indices) {
                            if (input[j][i] in "^v<>") {
                                x = i
                                y = j
                                direction = Direction.fromSymbol(input[j][i])
                                break@loop
                            }
                        }
                    }

                    val obstructions = mutableSetOf(Triple(x, y, direction))
                    var loop = false

                    while (true) {
                        val (newX, newY) = direction.forward(x, y)
                        if (newY in 0..<map.size && newX in 0..<map[y].size) {
                            if (map[newY][newX] in "#O") {
                                if (Triple(newX, newY, direction) in obstructions) {
                                    loop = true
                                    break
                                } else {
                                    obstructions += Triple(newX, newY, direction)
                                }
                                direction = direction.turn()
                            } else {
                                x = newX
                                y = newY
                            }
                        } else {
                            break
                        }
                    }

                    if (loop) {
                        obstructionCount++
                    }
                }
            }

            obstructionCount
        }
    }
}

enum class Direction(val symbol: Char) {
    UP('^') {
        override fun forward(x: Int, y: Int) = x to y - 1
    },
    RIGHT('>') {
        override fun forward(x: Int, y: Int) = x + 1 to y
    },
    DOWN('v') {
        override fun forward(x: Int, y: Int) = x to y + 1
    },
    LEFT('<'){
        override fun forward(x: Int, y: Int) = x - 1 to y
    };

    companion object {
        fun fromSymbol(symbol: Char) = entries.first { it.symbol == symbol }
    }

    abstract fun forward(x: Int, y: Int): Pair<Int, Int>

    fun turn() = entries[(ordinal + 1) % entries.size]
}
