fun main() {
    val input = readInputFile("input_4.txt")
    val randomNumbers = extractRandomNumbers(input.first())
    val originalBoards: List<Array<IntArray>> = input.drop(2)
        .filter { it.isNotEmpty() }
        .windowed(5, 5)
        .map { extractBingoBoard(it) }

    println(part1(randomNumbers, originalBoards))
    println(part2(randomNumbers, originalBoards))
}

private fun part1(
    randomNumbers: List<Int>,
    bingoBoards: List<Array<IntArray>>
): Int {
    val copies = bingoBoards.map { copyBingoBoard(it) }
    val (winningBoard, _, winningNumber) = part1Rec(randomNumbers, copies)
    return winningNumber * sumOfElementsInBingoBoard(winningBoard)
}

private fun part1Rec(
    randomNumbers: List<Int>,
    bingoBoards: List<Array<IntArray>>
): Triple<Array<IntArray>, Int, Int> {
    val nextRandomNumber = randomNumbers.first()
    bingoBoards.forEachIndexed { index, bingoBoard ->
        markBingoBoard(bingoBoard, nextRandomNumber)
        if (isAnyRowWinning(bingoBoard) || isAnyColumnWinning(bingoBoard)) {
            return Triple(bingoBoard, index, nextRandomNumber)
        }
    }
    return part1Rec(randomNumbers.drop(1), bingoBoards)
}

private fun part2(
    randomNumbers: List<Int>,
    bingoBoards: List<Array<IntArray>>
): Int {
    val copies = bingoBoards.map { copyBingoBoard(it) }
    val (winningBoard, _, winningNumber) = part2Rec(
        randomNumbers,
        copies.mapIndexed { i, board -> i to board }
            .associate { it.first to it.second }
            .toMutableMap()
    )
    return winningNumber * sumOfElementsInBingoBoard(winningBoard)
}

private fun part2Rec(
    randomNumbers: List<Int>,
    mapOfBingoBoards: MutableMap<Int, Array<IntArray>>
): Triple<Array<IntArray>, Int, Int> {
    val nextRandomNumber = randomNumbers.first()
    val indiciesToRemove = mutableListOf<Int>()
    mapOfBingoBoards.entries.forEach { (originalIndex, bingoBoard) ->
        markBingoBoard(bingoBoard, nextRandomNumber)
        if (isAnyRowWinning(bingoBoard) || isAnyColumnWinning(bingoBoard)) {
            if (mapOfBingoBoards.size == 1) { // the last existing board
                return Triple(bingoBoard, originalIndex, nextRandomNumber)
            } else indiciesToRemove.add(originalIndex)
        }
    }
    indiciesToRemove.forEach { mapOfBingoBoards.remove(it) }
    return part2Rec(randomNumbers.drop(1), mapOfBingoBoards)
}

private fun extractRandomNumbers(lineWithNumbers: String): List<Int> {
    return lineWithNumbers
        .split(",")
        .map { it.toInt() }
}

private fun extractBingoBoard(splittedLine: List<String>): Array<IntArray> =
    splittedLine
        .map { it.trim() }
        .map { it.replace(Regex("\\s+"), ",") }
        .map { it.split(",") }
        .map { stringNumber -> stringNumber.map { it.toInt() }.toIntArray() }
        .toTypedArray()

private fun printBingoBoard(bingoBoard: Array<IntArray>) =
    bingoBoard.forEach {
        println(it.joinToString(prefix = "[", postfix = "]"))
    }

private fun markBingoBoard(bingoBoard: Array<IntArray>, numberToMark: Int): Array<IntArray> = bingoBoard.apply {
    this.forEachIndexed { i, array ->
        array.forEachIndexed { j, elem ->
            if (elem == numberToMark) bingoBoard[i][j] = -1
        }
    }
}

private const val WINNING_ROW_OR_COLUMN = -5
private const val BOARD_LENGTH = 5

private fun isAnyRowWinning(bingoBoard: Array<IntArray>): Boolean =
    bingoBoard.any { it.sum() == WINNING_ROW_OR_COLUMN }

private fun isAnyColumnWinning(bingoBoard: Array<IntArray>): Boolean = isAnyRowWinning(transpose(bingoBoard))

private fun transpose(bingoBoard: Array<IntArray>): Array<IntArray> =
    Array(BOARD_LENGTH) { IntArray(BOARD_LENGTH) }
        .apply {
            bingoBoard.forEachIndexed { i, array ->
                array.forEachIndexed { j, number ->
                    this[j][i] = number
                }
            }
        }

private fun copyBingoBoard(bingoBoard: Array<IntArray>): Array<IntArray> =
    Array(BOARD_LENGTH) { index -> bingoBoard[index].copyOf() }

private fun sumOfElementsInBingoBoard(bingoBoard: Array<IntArray>): Int =
    bingoBoard.sumOf { array -> array.filterNot { it == -1 }.sum() }
