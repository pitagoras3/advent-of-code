package day1

import java.io.File
import kotlin.math.floor

fun main() {
    println(part1())
    println(part2())
}

private fun part1(): Int = modules()
    .map { calculateFuel(it) }
    .map { it.toInt() }
    .sum()

private fun part2(): Int = modules()
    .map { calculateFuel(it) }
    .map { recCalculateFuel(it) }
    .map { it.toInt() }
    .sum()

private fun calculateFuel(mass: Double): Double =
    (floor(mass / 3) - 2)

private fun recCalculateFuel(mass: Double): Double =
    calculateFuel(mass).let {
        if (it <= 0) mass
        else mass + recCalculateFuel(it)
    }

private fun modules() = File("src/day1/input.txt")
    .useLines { it.toList() }
    .asSequence()
    .map { it.toDouble() }
