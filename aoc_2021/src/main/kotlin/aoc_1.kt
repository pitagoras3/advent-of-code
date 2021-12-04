fun main() {
    val measurements = readInputFile("input_1.txt").map { it.toInt() }
    println(part1(measurements))
    println(part2(measurements))
}

private fun part1(measurements: List<Int>): Int =
    measurements.fold((measurements.first() to 0)) { (previousMeasure, increments), currentMeasure ->
        if (currentMeasure > previousMeasure) (currentMeasure to increments + 1)
        else (currentMeasure to increments)
    }.second

private fun part2(measurements: List<Int>): Int {
    val summed = measurements.windowed(3, 1, false)
        .map { it.sum() }

    return summed.fold((summed.first() to 0)) { (previousMeasure, increments), currentMeasure ->
        if (currentMeasure > previousMeasure) (currentMeasure to increments + 1)
        else (currentMeasure to increments)
    }.second
}
