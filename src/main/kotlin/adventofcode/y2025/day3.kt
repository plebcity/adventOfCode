package adventofcode.y2025

import adventofcode.utils.readInput

fun main() {
    val input = readInput("2025/day3/input.txt")
    val part1 = input.sumOf { line ->
        line.mapIndexed { index, ch ->
            if (index != line.length - 1) {
                ch.digitToInt() * 10 + line.substring(index + 1).max().digitToInt()
            } else {
                0
            }
        }.max()
    }
    println("Part1: $part1")

    val part2 = input.sumOf { line ->
        var startIndex = 0
        line.foldIndexed("") { index, acc, char ->
            if (startIndex <= index && acc.length != 12 && char.digitToInt() == line.substring(index, line.length - (12 - (acc.length + 1))).map { it.digitToInt() }.max()) {
                startIndex = index
                acc + char.toString()
            } else {
                acc
            }
        }.toLong()
    }
    println("Part2: $part2")
}