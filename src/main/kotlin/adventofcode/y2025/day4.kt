package adventofcode.y2025

import adventofcode.utils.Point
import adventofcode.utils.PointWithChar
import adventofcode.utils.getNeighboursWithDiagonals
import adventofcode.utils.readInput

fun main() {
    val lines = readInput("2025/day4/input.txt")
    val grid = lines.mapIndexed { rowIndex, row -> row.mapIndexed { columnIndex, ch -> PointWithChar(Point(rowIndex.toLong(), columnIndex.toLong()), ch) } }
    val part1 = grid.flatten().count { point ->
        if (point.value == '@') {
            grid.getNeighboursWithDiagonals(point.point).count { it.value == '@' } < 4
        } else {
            false
        }
    }

    println("Part 1: $part1")

    var part2 = 0
    while (true) {
        val result = grid.flatten().count { point ->
            if (point.value == '@' && grid.getNeighboursWithDiagonals(point.point).count { it.value == '@' } < 4) {
                point.value = '.'
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