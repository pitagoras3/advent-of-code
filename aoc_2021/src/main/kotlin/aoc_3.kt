fun main() {
    val input = readInputFile("input_3.txt")
    println(part1(input))
    println(part2(input))
}

private fun part1(input: List<String>): Int {
    val indexToValues = mutableMapOf<Int, MutableList<String>>()

    input.forEach {
        it.forEachIndexed { index, char ->
            if (indexToValues[index] == null) indexToValues[index] = mutableListOf()
            indexToValues[index]?.add(char.toString())
        }
    }

    val countedOccurrences: List<Map<String, Int>> =
        indexToValues.map { (_, listOfBytesPerIndex) -> listOfBytesPerIndex.groupingBy { it }.eachCount() }

    val gamma = countedOccurrences.joinToString("") { it.toList().maxByOrNull { it.second }!!.first }.toInt(2)
    val epsilon = countedOccurrences.joinToString("") { it.toList().minByOrNull { it.second }!!.first }.toInt(2)

    return gamma * epsilon
}

private fun part2(input: List<String>): Int {
    val oxygen = part2Rec(0, input, ::mostCommonBit).toInt(2)
    val co2 = part2Rec(0, input, ::leastCommonBit).toInt(2)
    return oxygen * co2
}

private fun part2Rec(index: Int, recInput: List<String>, importantBit: (List<String>) -> Int): String {
    val chosenBits: List<String> = recInput.map { it[index].toString() }
    val mostCommonValue: String = importantBit(chosenBits).toString()
    val filteredNumbers = recInput.filter { it[index].toString() == mostCommonValue }
    return if (filteredNumbers.size == 1) filteredNumbers.first() else part2Rec(
        index + 1,
        filteredNumbers,
        importantBit
    )
}

private fun mostCommonBit(bits: List<String>): Int {
    val equalCause = 1
    val sum = bits.sumOf { it.toDouble() }
    val length = bits.size

    return when {
        sum == length / 2.0 -> equalCause
        sum < (length / 2.0) -> 0
        else -> 1
    }
}

private fun leastCommonBit(bits: List<String>): Int {
    val equalCause = 0
    val sum = bits.sumOf { it.toDouble() }
    val length = bits.size

    return when {
        sum == length / 2.0 -> equalCause
        sum < (length / 2.0) -> 1
        else -> 0
    }
}
