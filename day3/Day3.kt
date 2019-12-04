package day3

import java.io.File
import kotlin.math.abs

val initialState = Pair(0, 0)

fun main() {
    println(part1())
    println(part2())
}

private fun part1(): Int {
    val (first, second) = firstRoadToSecondRoad()
    return first.intersect(second)
        .filter { it != initialState }
        .map { manhattanDistance(it, initialState) }
        .min()!!
}

private fun part2(): Int {
    val (first, second) = firstRoadToSecondRoad()
    return first.intersect(second)
        .filter { it != initialState }
        .map { first.indexOf(it) + second.indexOf(it) }
        .min()!!
}

private fun firstRoadToSecondRoad() = paths()
    .map { calculateCombinedRoad(it, listOf(initialState)) }
    .let { it[0] to it[1] }

private fun calculateCombinedRoad(pathElements: List<PathElement>, acc: List<Pair<Int, Int>>): List<Pair<Int, Int>> =
    when {
        pathElements.isEmpty() -> acc
        else -> calculateCombinedRoad(
            pathElements.drop(1),
            acc + calculateStraightRoad(acc.last(), pathElements.first())
        )
    }

private fun calculateStraightRoad(startingPosition: Pair<Int, Int>, pathElement: PathElement): List<Pair<Int, Int>> =
    when (pathElement.direction) {
        'R' -> List(pathElement.length) { Pair(startingPosition.first + it + 1, startingPosition.second) }
        'U' -> List(pathElement.length) { Pair(startingPosition.first, startingPosition.second + it + 1) }
        'L' -> List(pathElement.length) { Pair(startingPosition.first - it - 1, startingPosition.second) }
        'D' -> List(pathElement.length) { Pair(startingPosition.first, startingPosition.second - it - 1) }
        else -> throw Exception("Unknown direction.")
    }

private fun manhattanDistance(position1: Pair<Int, Int>, position2: Pair<Int, Int>) =
    abs(position1.first - position2.first) + abs(position1.second - position2.second)

private fun String.toPathElement() = PathElement(this.first(), this.substring(1).toInt())

private data class PathElement(
    val direction: Char,
    val length: Int
)

private fun paths() = File("src/day3/input.txt")
    .useLines { it.toList() }
    .map {
        it.split(",")
            .map { unparsedPathElement -> unparsedPathElement.toPathElement() }
    }