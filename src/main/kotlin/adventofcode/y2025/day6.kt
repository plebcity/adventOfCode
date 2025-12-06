package adventofcode.y2025

import adventofcode.utils.readInput
import adventofcode.utils.split
import adventofcode.utils.transpose

data class Problem(val numbers: List<Long>, val operation: String) {
    fun calculate(): Long =
        if (operation == "+") {
            numbers.sum()
        } else {
            numbers.fold(1L) { acc, number -> acc * number }
        }
}

fun main() {
    val lines = readInput("2025/day6/input.txt")
    val transposed = lines.map { line -> line.split("\\s".toRegex()).filter { it.isNotBlank() }.map { it } }.transpose()
    val worksheet = transposed.map { Problem(it.dropLast(1).map { it.toLong() }, it.last()) }
    val part1 = worksheet.sumOf { it.calculate() }
    println("Part 1: $part1")

    
    val transposedPart2 = lines
        .map { it.toCharArray().toList() }
        .transpose()
        .reversed()
        .split { it.map { it.toString() }.all { it.isBlank() } }
    val worksheetPart2 = transposedPart2.map { 
        Problem(
            it.map { it.filter { it.isDigit() }.joinToString("").toLong() }, 
            it.last().last().toString()) 
    }
    val part2 = worksheetPart2.sumOf { it.calculate() }
    println("Part 2: $part2")
    
}