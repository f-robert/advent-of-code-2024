package aoc

import java.awt.RenderingHints
import java.awt.image.BufferedImage
import java.io.File
import javax.imageio.ImageIO

fun main() {
    puzzle(14) {
        val re = "p=(\\d+),(\\d+) v=(-?\\d+),(-?\\d+)".toRegex()

        val width = 101
        val height = 103

        data class Robot(var pos: Pair<Int, Int>, val v: Pair<Int, Int>) {
            fun move() {
                pos = (pos.first + v.first).mod(width) to (pos.second + v.second).mod(height)
            }
        }

        part1 { input ->
            val robots = input.map { line ->
                val (px, py, vx, vy) = re.find(line)!!.destructured.toList().map(String::toInt)
                Robot(px to py, vx to vy)
            }

            repeat(100) {
                robots.forEach { it.move() }
            }

            val quadrants = IntArray(4)
            val tiles = robots
                .groupingBy { it.pos }
                .eachCount()
                .filterKeys { it.first != width / 2 && it.second != height / 2 }

            for ((pos, count) in tiles) {
                val qx = if (pos.first in 0..< width / 2) 0 else 1
                val qy = if (pos.second in 0..< height / 2) 0 else 1
                val q = qx + qy * 2
                quadrants[q] += count
            }

            quadrants.fold(1, Long::times)
        }

        part2 { input ->
            val robots = input.map { line ->
                val (px, py, vx, vy) = re.find(line)!!.destructured.toList().map(String::toInt)
                Robot(px to py, vx to vy)
            }

            val newWidth = width * 4
            val newHeight = height * 4
            val period = 101
            val start = 23

            repeat(start) {
                robots.forEach { it.move() }
            }

            for (iteration in 0..<100) {
                val image = BufferedImage(width, height, BufferedImage.TYPE_INT_RGB)

                robots.forEach {
                    image.setRGB(it.pos.first, it.pos.second, 0x00FF00)
                }

                val scaledImage = BufferedImage(newWidth, newHeight, image.type)
                val g2d = scaledImage.createGraphics()
                    .apply {
                        setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR)
                        setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY)
                        setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON)
                    }

                g2d.drawImage(image, 0, 0, newWidth, newHeight, null)
                g2d.dispose()
                ImageIO.write(scaledImage, "PNG", File("day14-${start + iteration * period}.png"))

                repeat(period) {
                    robots.forEach {
                        it.move()
                    }
                }
            }

            0
        }
    }
}
