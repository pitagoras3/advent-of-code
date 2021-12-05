fun main() {
    val lines = readInputFile("input_5.txt")

    val allCoordinates: List<List<Pair<Int, Int>>> = lines.map { it.split(" -> ") }
        .map { coordinates -> coordinates.map { it.split(",") } }
        .map { coordinates -> coordinates.map { it[0].toInt() to it[1].toInt() } }

    val filteredCoordinates = allCoordinates.filter {
        val (x1, y1) = it[0]
        val (x2, y2) = it[1]
        x1 == x2 || y1 == y2 // For part1, only consider horizontal and vertical lines
    }

    // part1
    println(solution(filteredCoordinates))
    // part2
    println(solution(allCoordinates))
}

private fun solution(listOfListOfCoordinates: List<List<Pair<Int, Int>>>): Int {
    val allPoints = listOfListOfCoordinates.flatMap {
        val (x1, y1) = it[0]
        val (x2, y2) = it[1]
        when {
            x1 == x2 -> producePointsBetweenHorizontally(x1, y1, y2)
            y1 == y2 -> producePointsBetweenVertically(y1, x1, x2)
            else -> producePointsBetweenDiagonally(x1, x2, y1, y2)
        }
    }

    return allPoints.groupingBy { it }.eachCount().filter { it.value >= 2 }.size
}

private fun producePointsBetweenHorizontally(x: Int, y1: Int, y2: Int): List<Pair<Int, Int>> {
    val min = minOf(y1, y2)
    val max = maxOf(y1, y2)
    return (min..max).map { Pair(x, it) }
}

private fun producePointsBetweenVertically(y: Int, x1: Int, x2: Int): List<Pair<Int, Int>> {
    val min = minOf(x1, x2)
    val max = maxOf(x1, x2)
    return (min..max).map { Pair(it, y) }
}

private fun producePointsBetweenDiagonally(x1: Int, x2: Int, y1: Int, y2: Int): List<Pair<Int, Int>> {
    val xCollection = if (x2 > x1) (x1..x2) else (x1 downTo x2)
    val yCollection = if (y2 > y1) (y1..y2) else (y1 downTo y2)
    return xCollection.zip(yCollection)
}

