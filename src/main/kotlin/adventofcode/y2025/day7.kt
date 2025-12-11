package adventofcode.y2025

import adventofcode.utils.Direction
import adventofcode.utils.Point
import adventofcode.utils.PointWithChar
import adventofcode.utils.getNeigboursInDirection
import adventofcode.utils.readInput

fun main() {
    val lines = readInput("2025/day7/input.txt")
    val grid = lines.map { it.toCharArray().toList() }
        .mapIndexed { rowIndex, row -> row.mapIndexed { colIndex, value -> PointWithChar(Point(rowIndex.toLong(), colIndex.toLong()), value) } }
    val part1 = grid.fold(0L) { accGrid, list ->
        accGrid + list.fold(0L) { accRow, pointWithChar ->
            if (pointWithChar.value == 'S') {
                grid.getNeigboursInDirection(pointWithChar.point, listOf(Direction.DOWN)).firstOrNull()?.apply { value = '|' }
                accRow
            } else if (pointWithChar.value == '^') {
                val upperNeighbour = grid.getNeigboursInDirection(pointWithChar.point, listOf(Direction.UP)).firstOrNull()
                if (upperNeighbour?.value == '|') {
                    val rightLeftNeighbours = grid.getNeigboursInDirection(pointWithChar.point, listOf(Direction.RIGHT, Direction.LEFT))
                    rightLeftNeighbours.forEach {
                        if (it.value == '.') {
                            it.value = '|'
                            grid.getNeigboursInDirection(it.point, listOf(Direction.DOWN)).firstOrNull()?.apply { value = '|' }
                        }
                    }
                    accRow + 1
                } else {
                    accRow
                }
            } else if (pointWithChar.value == '.') {
                val upperNeighbour = grid.getNeigboursInDirection(pointWithChar.point, listOf(Direction.UP)).firstOrNull()
                if (upperNeighbour?.value == '|') { 
                    pointWithChar.value = '|'
                    grid.getNeigboursInDirection(pointWithChar.point, listOf(Direction.DOWN)).firstOrNull()?.apply { value = '|' }
                }
                accRow
            } else {
                accRow
            }
        }
    }
    println(grid.joinToString("\n") { it.map { it.value }.joinToString("") })
    println("Part 1: $part1")
    
    
}