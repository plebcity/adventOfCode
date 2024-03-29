package adventofcode.y2021

import adventofcode.utils.readInput

fun main() {
    val input: List<Int> = readInput("2021/day1.txt").map { it.toInt() }

    fun List<Int>.calculateIncreasedBetweenPreviousAndCurrent(): Int {
        var increased = 0
        reduce { prev: Int, cur: Int ->
            if (prev - cur < 0) {
                increased++
            }
            cur
        }
        return increased
    }

// Part 1
    val resultPart1 = input.calculateIncreasedBetweenPreviousAndCurrent()

    println("Part 1 - Increased count: $resultPart1")


// Part 2
    val resultPart2 = input.windowed(3).map { it.sum() }.calculateIncreasedBetweenPreviousAndCurrent()

    println("Part 2 - Increased count: $resultPart2")
}
