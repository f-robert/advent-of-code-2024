package aoc

fun main() {
    puzzle(11) {
        part1(55312) { input ->
            val stones = input.single().toLongList().toMutableList()

            repeat(25) {
                var index = 0

                while (index < stones.size) {
                    if (stones[index] == 0L) {
                        stones[index] = 1L
                        index++
                    } else if (stones[index].toString().length % 2 == 0) {
                        val s = stones[index].toString()
                        val len = s.length / 2
                        val first = s.substring(0, len).toLong()
                        val second = s.substring(len).toLong()

                        stones[index] = first
                        stones.add(index + 1, second)

                        index += 2
                    } else {
                        stones[index] *= 2024L
                        index++
                    }
                }
            }

            stones.size
        }

        part2 { input ->
            var stones = input.single().toLongList().groupingBy { it }.eachCount()
                .mapValues { it.value.toLong() }
                .toMutableMap()
            val temp = mutableMapOf<Long, Long>()

            repeat(75) {
                temp.clear()

                for ((stone, count) in stones) {
                    if (stone == 0L) {
                        temp[1] = temp.getOrDefault(1, 0) + count
                    } else if (stone.toString().length % 2 == 0) {
                        val s = stone.toString()
                        val len = s.length / 2
                        val first = s.substring(0, len).toLong()
                        val second = s.substring(len).toLong()

                        temp[first] = temp.getOrDefault(first, 0) + count
                        temp[second] = temp.getOrDefault(second, 0) + count
                    } else {
                        temp[stone * 2024L] = temp.getOrDefault(stone * 2024L, 0) + count
                    }
                }

                stones = temp.toMutableMap()
            }

            stones.values.sum()
        }
    }
}
