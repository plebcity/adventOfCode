package adventofcode.y2025

import adventofcode.utils.readInput

fun main() {
    val input = readInput("2025/day2/input.txt")
    val ranges = input[0].split(",").flatMap { it.split("-").let { it[0].toLong()..it[1].toLong() } }

    val part1 = ranges.fold(0L) { total, next -> if (isInvalidPart1(next.toString())) total + next else total }
    println("Part 1: $part1")

    val part2 = ranges.fold(0L) { total, next -> if (isInvalidPart2(next.toString())) total + next else total }
    println("Part 2: $part2")
}

fun isInvalidPart1(number: String): Boolean {
    if (number.length % 2 != 0)
        return false
    return number.substring(number.length / 2) == number.substring(0, number.length / 2)
}

fun isInvalidPart2(number: String): Boolean {
    return (1..(number.length / 2)).any {
        val sequence = number.substring(0, it)
        val occurences = number.chunked(sequence.length).count { it == sequence }
        occurences * sequence.length == number.length
    }
}