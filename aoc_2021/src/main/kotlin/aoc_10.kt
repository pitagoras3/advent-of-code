fun main() {
    val input = readInputFile("input_10.txt")
    println(part1(input))
    println(part2(input))
}

private val openingChars = listOf("(", "<", "{", "[")
private val closingChars = listOf(")", ">", "}", "]")
private val correctPairs = listOf("()", "[]", "{}", "<>")
private val incorrectPairs =
    openingChars.flatMap { opening -> closingChars.map { closing -> "$opening$closing" } } - correctPairs

private fun part1(lines: List<String>): Long = lines.map { part1Rec(it) }
    .filterNot { it.isEmpty() }
    .sumOf { scoreForIncorrect(it) }

private fun part1Rec(line: String): String = if (correctPairs.any { line.contains(it) }) {
    var lineCopy = line
    correctPairs.forEach {
        lineCopy = lineCopy.replace(it, "")
    }
    part1Rec(lineCopy)
} else if (incorrectPairs.any { line.contains(it) }) {
    line.windowed(2, 1, true)
        .first { incorrectPairs.contains(it) }[1]
        .toString()
} else ""

private fun scoreForIncorrect(incorrect: String): Long = when (incorrect) {
    ")" -> 3
    "]" -> 57
    "}" -> 1197
    ">" -> 25137
    else -> throw IllegalArgumentException()
}

private fun part2(lines: List<String>): Long {
    val sortedList = lines.map { part2Rec(it) }
        .filterNot { it.isEmpty() }
        .map { scoreForClosings(it) }
        .sorted()

    return sortedList[(sortedList.size / 2)]
}

private fun scoreForClosings(closings: String): Long {
    var score = 0L
    val map = mapOf("]" to 2, ")" to 1, "}" to 3, ">" to 4)
    closings.toCharArray().map { it.toString() }.forEach {
        score = score * 5 + map[it]!!
    }
    return score
}

private fun part2Rec(line: String): String = if (correctPairs.any { line.contains(it) }) {
    var copy = line
    correctPairs.forEach {
        copy = copy.replace(it, "")
    }
    part2Rec(copy)
} else if (incorrectPairs.any { line.contains(it) }) {
    ""
} else {
    line.reversed().toCharArray().map { it.toString() }.joinToString("") {
        closingChars[openingChars.indexOf(it)]
    }
}
