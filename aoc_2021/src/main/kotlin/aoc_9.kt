fun main() {
    val input = readInputFile("input_9.txt")
        .map { it.trim().toCharArray().map { it.digitToInt() } }

    println(part1(input))
    println(part2(input))
}

private fun lowPointTriples(input: List<List<Int>>): List<Triple<Int, Int, Int>> =
    input.flatMapIndexed { i, horizontal ->
        horizontal.mapIndexedNotNull { j, cell ->
            val isLowPoint = horizontalAndVerticalNeighbours(input, i to j).all { cell < it.first }
            if (isLowPoint) Triple(cell, i, j) else null
        }
    }

private fun part1(input: List<List<Int>>): Int = lowPointTriples(input).sumOf { it.first + 1 }

private fun part2(input: List<List<Int>>): Int =
    lowPointTriples(input).map { part2Rec(input, it.second to it.third).distinct().size }
        .sortedDescending()
        .take(3)
        .reduce { x, y -> x * y }

private fun part2Rec(
    allPoints: List<List<Int>>,
    position: Pair<Int, Int>,
    visited: Set<Pair<Int, Int>> = emptySet()
): Set<Pair<Int, Int>> {
    val basinNeighbours = horizontalAndVerticalNeighbours(allPoints, position)
        .filterNot { it.first == 9 || visited.contains(it.second to it.third) }
    return if (basinNeighbours.isEmpty()) visited + position
    else basinNeighbours.fold(visited + position) { acc: Set<Pair<Int, Int>>, current ->
        acc + part2Rec(
            allPoints,
            current.second to current.third,
            acc
        )
    }
}

private fun horizontalAndVerticalNeighbours(
    allPoints: List<List<Int>>,
    position: Pair<Int, Int>
): Set<Triple<Int, Int, Int>> {
    val left = tryGetTripleOrNull(allPoints, position.first, position.second - 1)
    val right = tryGetTripleOrNull(allPoints, position.first, position.second + 1)
    val up = tryGetTripleOrNull(allPoints, position.first - 1, position.second)
    val down = tryGetTripleOrNull(allPoints, position.first + 1, position.second)
    return setOfNotNull(left, right, up, down)
}

private fun tryGetTripleOrNull(allPoints: List<List<Int>>, i: Int, j: Int): Triple<Int, Int, Int>? =
    runCatching { allPoints[i][j] }
        .map { Triple(it, i, j) }.getOrNull()
