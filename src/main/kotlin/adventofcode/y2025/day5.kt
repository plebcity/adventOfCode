package adventofcode.y2025

import adventofcode.utils.readInput
import adventofcode.utils.split

data class FreshIngredientRange(val from: Long, val to: Long) {
    fun inRange(value: Long): Boolean =
        value >= from && value <= to

    fun countNotOverlapping(previous: FreshIngredientRange): Long =
        if (from < previous.to && to <= previous.to) {
            0
        } else if (from <= previous.to) {
            (to - (previous.to + 1)) + 1
        } else {
            (to - from) + 1
        }
}

fun main() {
    val lines = readInput("2025/day5/input.txt")
    val (freshIngedrientRanges, availableIngredients) = lines
        .split { it.isBlank() }
        .let {
            it.first().map {
                it.split("-").let { FreshIngredientRange(it.first().toLong(), it.last().toLong()) }
            } to it.last().map {
                it.toLong()
            }
        }
    val part1 = availableIngredients.count { availableIngredient -> freshIngedrientRanges.any { it.inRange(availableIngredient) } }

    println("Part1: $part1")

    val sortedFreshIngredientRange = freshIngedrientRanges.sortedBy { it.from }
    val (part2, _) = sortedFreshIngredientRange.fold(0L to null as FreshIngredientRange?) { acc, range ->
        if (acc.second == null) {
            (range.to - range.from) + 1 to range
        } else {
            val result = range.countNotOverlapping(acc.second!!)
            if (result == 0L) {
                acc
            } else {
                acc.first + result to range
            }
        }
    }
    println("Part2: $part2")
}