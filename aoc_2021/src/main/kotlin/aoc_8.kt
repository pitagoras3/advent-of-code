fun main() {
    println(part1())
    println(part2())
}

private fun part1(): Int = readInputFile("input_8.txt")
    .map { it.split("|").last() }
    .flatMap { it.split(" ") }
    .count { listOf(2, 3, 4, 7).contains(it.length) }

private fun part2(): Int {
    val res = readInputFile("input_8.txt")
        .map { it.split("|") }
        .map {
            Pair(
                it.first().split(" ")
                    .map { it.trim() }
                    .filterNot { it.isEmpty() },
                it.last().split(" ")
                    .map { it.trim() }
                    .filterNot { it.isEmpty() })
        }

    return res.sumOf { decodeLine(it) }
}

private fun decodeLine(line: Pair<List<String>, List<String>>): Int {
    val (one, four, seven, eight) = extractObviousNumbers(line.first)
    val (zero, six, nine) = extractZeroSixNine(one, four, line.first.filter { it.length == 6 })
    val (two, three, five) = extractTwoThreeFive(one, four, line.first.filter { it.length == 5 })
    val correctDigits = listOf(zero, one, two, three, four, five, six, seven, eight, nine)
        .map { it.toSortedSet().toString() }

    return line.second.map { it.toSortedSet().toString() }
        .map { correctDigits.indexOf(it) }
        .joinToString("")
        .toInt()
}

private fun extractObviousNumbers(allDigits: List<String>): NTuple4<String, String, String, String> {
    val one = allDigits.find { it.length == 2 }!!
    val four = allDigits.find { it.length == 4 }!!
    val seven = allDigits.find { it.length == 3 }!!
    val eight = allDigits.find { it.length == 7 }!!
    return NTuple4(one, four, seven, eight)
}

private fun extractZeroSixNine(one: String, four: String, allSixLongs: List<String>): Triple<String, String, String> {
    val six = allSixLongs.find { (one.toSortedSet() - it.toSortedSet()).size == 1 }!!
    val nine = allSixLongs.find { (four.toSortedSet() - it.toSortedSet()).isEmpty() }!!
    val zero = (allSixLongs - six - nine).first()
    return Triple(zero, six, nine)
}

private fun extractTwoThreeFive(one: String, four: String, allFiveLongs: List<String>): Triple<String, String, String> {
    val three = allFiveLongs.find { (one.toSortedSet() - it.toSortedSet()).isEmpty() }!!
    val two = allFiveLongs.find { (four.toSortedSet() - it.toSortedSet()).size == 2 }!!
    val five = (allFiveLongs - three - two).first()
    return Triple(two, three, five)
}

private data class NTuple4<T1, T2, T3, T4>(val t1: T1, val t2: T2, val t3: T3, val t4: T4)
