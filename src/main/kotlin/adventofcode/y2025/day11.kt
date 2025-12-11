package adventofcode.y2025

import adventofcode.utils.readInput

private data class Device(val label: String, val outputs: Set<String>)

fun main() {
    val lines = readInput("2025/day11/input.txt")
    val devices = lines.map { line -> line.split(": ").let { Device(it.first(), it.last().split(" ").toSet()) } }

    val start = devices.first { it.label == "you" }
    val part1 = getPossiblePaths(start, devices, mutableMapOf())

    println("Part 1: $part1")

    val lines2 = readInput("2025/day11/input.txt")
    val devices2 = lines2.map { line -> line.split(": ").let { Device(it.first(), it.last().split(" ").toSet()) } }

    val svr = devices2.first { it.label == "svr" }
    val part2 = getPossiblePathsPart2(svr, devices2, mutableMapOf(), false, false)

    println("Part 2: $part2")

}

private fun getPossiblePaths(current: Device, devices: List<Device>, cache: MutableMap<Device, Long>): Long {
    if (current.outputs.all { it == "out" }) return 1
    val nextDevices = devices.filter { current.outputs.contains(it.label) }
    return nextDevices.sumOf { next ->
        cache.getOrPut(next) {
            getPossiblePaths(next, devices, cache)
        }
    }
}

private data class DeviceCacheKey(val device: Device, val passthroughDac: Boolean, val passthroughFft: Boolean)

private fun getPossiblePathsPart2(current: Device, devices: List<Device>, cache: MutableMap<DeviceCacheKey, Long>, passthroughDac: Boolean, passthroughFft: Boolean): Long {
    val newPassthroughDac = current.label == "dac" || passthroughDac
    val newPassthroughFft = current.label == "fft" || passthroughFft
    if (current.outputs.all { it == "out" }) return if (newPassthroughDac && newPassthroughFft) 1 else 0
    val nextDevices = devices.filter { current.outputs.contains(it.label) }
    return nextDevices.sumOf { next ->
        cache.getOrPut(DeviceCacheKey(next, newPassthroughDac, newPassthroughFft)) {
            getPossiblePathsPart2(next, devices, cache, newPassthroughDac, newPassthroughFft)
        }
    }
}