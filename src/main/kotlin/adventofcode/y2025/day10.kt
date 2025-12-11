package adventofcode.y2025

import adventofcode.utils.readInput

private data class Machine(val lights: List<Boolean>, val lightDiagram: List<Boolean>, val buttons: List<List<Int>>, val joltage: List<Int>) {
    fun getNextMachines(): List<Machine> =
        buttons.map { button ->
            Machine(
                lights.mapIndexed { index, bool -> if (index in button) !bool else bool }.toList(),
                lightDiagram,
                buttons,
                joltage
            )
        }
    
    fun equalsLightDiagram(): Boolean =
        lights.foldIndexed(true) { index, acc, bool -> acc && lightDiagram[index] == bool }
}

fun main() {
    val lines = readInput("2025/day10/sample.txt")
    val machines = lines.map { line ->
        line.split(" ").let {
            Machine(
                it.first().removePrefix("[").removeSuffix("]").toCharArray().map { false }.toList(),
                it.first().removePrefix("[").removeSuffix("]").toCharArray().toList().map { it == '#' },
                it.subList(1, it.lastIndex - 1).map { it.removePrefix("(").removeSuffix(")").split(",").map { it.toInt() } },
                it.last().removePrefix("{").removeSuffix("}").split(",").map { it.toInt() }
            )
        }
    }
    
    val part1 = machines.sumOf { countButtonPressesToDiagram(it, 0, true) }
    
    println("Part1: $part1")
}

private fun countButtonPressesToDiagram(machine: Machine, buttonPresses: Long, goToNext: Boolean): Long {
    val newMachines = machine.getNextMachines()
    if (newMachines.none { it.equalsLightDiagram() }) {
        return newMachines.map {
            if (goToNext) {
                val nextButtonPresses = countButtonPressesToDiagram(it, buttonPresses + 1, false)
                if (nextButtonPresses > 0) {
                    nextButtonPresses
                } else {
                    countButtonPressesToDiagram(it, buttonPresses + 1, true)
                }
            } else {
                0
            }
        }.min()
    } else {
        return buttonPresses + 1
    }
}