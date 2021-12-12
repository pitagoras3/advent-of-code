fun main() {
    val input = readInputFile("input_12.txt")
    println(solution(input, false).size) // part1
    println(solution(input, true).size) // part2
}

private fun solution(lines: List<String>, doubleSmallCaveEnabled: Boolean): List<List<String>> {
    val starts = lines.filter { it.contains("start") }
    val others = lines - starts

    return starts.map {
        pathsFromStartToEnd(
            currentCave = nextCave("start", it),
            visitedCaves = listOf("start"),
            connections = others - it,
            allPossiblePaths = emptySet(),
            doubleSmallCaveEnabled = doubleSmallCaveEnabled
        )
    }.flatten()
}

private fun pathsFromStartToEnd(
    currentCave: String,
    visitedCaves: List<String>,
    connections: List<String>,
    allPossiblePaths: Set<List<String>>,
    doubleSmallCaveEnabled: Boolean = false,
    doubleSmallCave: String? = null
): Set<List<String>> {
    return if(currentCave == "end") allPossiblePaths + listOf(visitedCaves + "end")
    else if (currentCave.isLowercase() && visitedCaves.contains(currentCave)) {
        if(doubleSmallCaveEnabled && doubleSmallCave == null) allPossiblePaths + connections.filter { it.contains(currentCave) }
            .map {
                pathsFromStartToEnd(
                    currentCave = nextCave(currentCave, it),
                    visitedCaves = visitedCaves + currentCave,
                    connections =  connections - it,
                    allPossiblePaths = allPossiblePaths,
                    doubleSmallCaveEnabled = doubleSmallCaveEnabled,
                    doubleSmallCave = currentCave
                )
            }.flatten()
        else allPossiblePaths
    }
    else {
        allPossiblePaths + connections.filter { it.split("-").any { it == currentCave } }
            .filterNot {
                val nextCave = nextCave(currentCave, it)
                if(nextCave == "end") false
                else if(nextCave.isLowercase()) checkIfSmallCaveIsDeadEnd(it, connections) else false
            }
            .map {
                pathsFromStartToEnd(
                    currentCave = nextCave(currentCave, it),
                    visitedCaves = visitedCaves + currentCave,
                    connections =  connections,
                    allPossiblePaths = allPossiblePaths,
                    doubleSmallCaveEnabled = doubleSmallCaveEnabled,
                    doubleSmallCave = doubleSmallCave
                )
            }.flatten()
    }
}

private fun checkIfSmallCaveIsDeadEnd(smallCave: String, connections: List<String>): Boolean =
    connections
        .filter { it.contains(smallCave) }
        .all { it.split("-").all { it.isUppercase() } }

private fun nextCave(currentCave: String, connection: String): String = connection.split("-").filterNot { it == currentCave }[0]
private fun String.isUppercase(): Boolean = this.toCharArray().all { it.isUpperCase() }
private fun String.isLowercase(): Boolean = this.toCharArray().all { it.isLowerCase() }
