package day2

import java.io.File

fun main() {
    println(part1())
    println(part2())
}

private fun part1(): Int = recCalculate(0, states().applyNounAndVerb(Pair(12, 2))).first()

private fun part2(): Int {
    val nounVerbCount = (0..99).count()
    val amountOfAllNounVerbCombinations = nounVerbCount * nounVerbCount
    val allPairsToCheck = List(amountOfAllNounVerbCombinations) {
        nounToVerbFromIndex(
            it,
            nounVerbCount,
            amountOfAllNounVerbCombinations
        )
    }

    return allPairsToCheck.map { Pair(it, recCalculate(0, states().applyNounAndVerb(it)).first()) }
        .first { it.second == 19690720 }
        .let { 100 * it.first.first + it.first.second }
}

private fun recCalculate(index: Int, states: List<Int>): List<Int> {
    return when (states[index]) {
        99 -> states
        1 -> recCalculate(index + 4, opcode(index, states, 1))
        2 -> recCalculate(index + 4, opcode(index, states, 2))
        else -> throw Exception("Unknown opcode.")
    }
}

private fun opcode(index: Int, states: List<Int>, opcode: Int): List<Int> {
    val firstIndex = states[index + 1]
    val secondIndex = states[index + 2]
    val resultIndex = states[index + 3]

    return states.mapIndexed { stateIndex, value ->
        if (stateIndex == resultIndex) {
            if (opcode == 1) states[firstIndex] + states[secondIndex] else states[firstIndex] * states[secondIndex]
        } else value
    }
}

private fun List<Int>.applyNounAndVerb(nounAndVerb: Pair<Int, Int>): List<Int> = this.toMutableList().apply {
    this[1] = nounAndVerb.first
    this[2] = nounAndVerb.second
}

private fun nounToVerbFromIndex(index: Int, nounVerbCount: Int, amountOfAllNounVerbCombinations: Int) =
    Pair(index % nounVerbCount, (index / nounVerbCount) % (amountOfAllNounVerbCombinations))

private fun states() = File("src/day2/input.txt")
    .useLines { it.toList() }
    .flatMap { it.split(",") }
    .map { it.toInt() }
