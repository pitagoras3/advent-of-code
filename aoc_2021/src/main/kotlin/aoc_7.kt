import kotlin.math.abs

fun main() {
    val input = readInputFile("input_7.txt")
        .map { it.split(",") }
        .flatMap { it.map { it.toInt() } }

    println(solution(input, ::absoluteDifference)) // part1
    println(solution(input, ::shiftedArithmeticProgressionSum)) // part2
}

private fun solution(input: List<Int>, fuelCalculation: (start: Int, finish: Int) -> Int): Int {
    val min = input.minOrNull()!!
    val max = input.maxOrNull()!!

    val positionToNumberOfOccurs = (min..max).associateWith { 0 }.toMutableMap()
    input.forEach { crab ->
        positionToNumberOfOccurs.keys.forEach { potentialPosition ->
            positionToNumberOfOccurs[potentialPosition] =
                positionToNumberOfOccurs[potentialPosition]!! +
                        fuelCalculation(potentialPosition, crab)
        }
    }

    val minEntry = positionToNumberOfOccurs.minByOrNull { it.value }
    return minEntry!!.value
}

private fun absoluteDifference(start: Int, finish: Int): Int = abs(start - finish)
private fun shiftedArithmeticProgressionSum(start: Int, finish: Int): Int {
    val min = minOf(start, finish)
    val max = maxOf(start, finish)
    return ((((1 + (max - min)).toDouble()) / 2.0) * (max - min)).toInt()
}
