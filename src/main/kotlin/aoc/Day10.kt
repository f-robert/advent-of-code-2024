package aoc

typealias Point = Pair<Int, Int>

fun main() {
    puzzle(10) {
        fun solve(input: List<String>): List<List<Point>> {
            val map = input.map { it.toCharArray().map(Char::digitToInt) }
            val height = map.size
            val width = map[0].size

            val heads = ArrayList<Point>()
            for (y in 0..<height) {
                for (x in 0..<width) {
                    if (map[y][x] == 0) {
                        heads += y to x
                    }
                }
            }

            fun score(head: Point): List<Point> {
                val success = mutableListOf<Point>()

                fun recurse(current: Int, position: Point) {
                    if (current == 9) {
                        success += position
                        return
                    }

                    for (dy in -1..1) {
                        for (dx in -1..1) {
                            if (dx == 0 && dy == 0) continue
                            if (dx == dy || dy == -dx) continue
                            val ny = position.first + dy
                            val nx = position.second + dx

                            if (nx !in 0..<width || ny !in 0..<height) continue
                            if (map[ny][nx] == current + 1) {
                                recurse(current + 1, ny to nx)
                            }
                        }
                    }
                }

                recurse(0, head)
                return success
            }

            return heads.map(::score)
        }

        part1(36) { input ->
            solve(input).sumOf { it.toSet().size }
        }

        part2(81) { input ->
            solve(input).sumOf { it.size }
        }
    }
}
