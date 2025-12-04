package adventofcode.y2025

import adventofcode.utils.Point
import adventofcode.utils.PointWithChar
import adventofcode.utils.getNeighboursWithDiagonals
import adventofcode.utils.readInput

fun main() {
    val lines = readInput("2025/day4/input.txt")
    val grid = lines.mapIndexed { rowIndex, row -> row.mapIndexed { columnIndex, ch -> PointWithChar(Point(rowIndex.toLong(), columnIndex.toLong()), ch) } }
    val part1 = grid.flatten().count { it.value == '@' && grid.getNeighboursWithDiagonals(it.point).count { it.value == '@' } < 4 }

    println("Part 1: $part1")

    var part2 = 0
    while (true) {
        val result = grid.flatten().count {
            if (it.value == '@' && grid.getNeighboursWithDiagonals(it.point).count { it.value == '@' } < 4) {
                it.value = '.'
                true
            } else {
                false
            }
        }
        if (result == 0) {
            break
        }
        part2 += result
    }

    println("Part 2: $part2")
}