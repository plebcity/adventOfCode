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
        var result = ""
        (0..11).reversed().forEach {
            val stringToSearchForMax = line.substring(startIndex, line.length - it).map { it.digitToInt() }
            val maxNumber = stringToSearchForMax.max()
            result = result + maxNumber
            startIndex += (stringToSearchForMax.indexOf(maxNumber) + 1)
        }

        result.toLong()
    }
    println("Part2: $part2")
}