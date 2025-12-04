package adventofcode.y2025

import adventofcode.utils.getNeighboursWithDiagonals
import adventofcode.utils.readInput
import adventofcode.utils.toPointsWithCharGrid

fun main() {
    val lines = readInput("2025/day4/input.txt")
    val grid = lines.toPointsWithCharGrid()
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