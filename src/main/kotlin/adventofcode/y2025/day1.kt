package adventofcode.y2025

import adventofcode.utils.readInput

data class Dial(var currentPosition: Int = 0, var amountOfZeroes: Int = 0) {
    fun subtract(amount: Int): Dial {
        currentPosition -= amount
        if (currentPosition % 100 == -50 || currentPosition % 100 == 50) {
            amountOfZeroes++
        }
        return this
    }

    fun add(amount: Int): Dial {
        currentPosition += amount
        if (currentPosition % 100 == -50 || currentPosition % 100 == 50) {
            amountOfZeroes++
        }
        return this
    }
}

data class Dial2(var currentPosition: Int = 50, var amountOfZeroes: Int = 0) {
    fun subtract(amount: Int): Dial2 {
        val oldPosition = currentPosition
        amountOfZeroes += amount / 100
        currentPosition -= amount % 100
        currentPosition = currentPosition % 100
        if (oldPosition > 0 && currentPosition <= 0 || oldPosition < currentPosition) {
            amountOfZeroes++
        }
        return this
    }

    fun add(amount: Int): Dial2 {
        val oldPosition = currentPosition
        amountOfZeroes += amount / 100
        currentPosition += amount % 100
        currentPosition = currentPosition % 100
        if (oldPosition < 0 && currentPosition >= 0 || oldPosition > currentPosition) {
            amountOfZeroes++
        }
        return this
    }
}

fun main() {
    val input = readInput("2025/day1/input.txt")
    val part1 = input.fold(Dial()) { current, next ->
        if (next.startsWith("L")) {
            current.subtract(next.removePrefix("L").toInt())
        } else {
            current.add(next.removePrefix("R").toInt())
        }
    }.amountOfZeroes
    println("Part 1: $part1")

    val part2 = input.fold(Dial2()) { current, next ->
        if (next.startsWith("L")) {
            current.subtract(next.removePrefix("L").toInt())
        } else {
            current.add(next.removePrefix("R").toInt())
        }
    }.amountOfZeroes
    println("Part 2: $part2")
}