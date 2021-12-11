fun main() {
    val input = readInputFile("input_11.txt").map { it.toCharArray().map { it.toString().toInt() } }
    println(solution(input, 100)) // part1
    println(solution(input, 1000)) // part2
}

private fun solution(matrix: List<List<Int>>, steps: Int): Int {
    val state = matrix.map { it.toMutableList() }
    var flashes = 0

    for (step in 1..steps) {
        state.forEachIndexed { i, line ->
            line.forEachIndexed { j, _ ->
                tryToFlash(i, j, state)
            }
        }
        state.forEachIndexed { i, line ->
            line.forEachIndexed { j, _ ->
                if (state[i][j] == -1) {
                    flashes += 1
                    state[i][j] = 0
                }
            }
        }
        val areAllFlashing = state.all { it.all { it == 0 } }
        if(areAllFlashing) return step
    }

    return flashes
}

private fun tryToFlash(i: Int, j: Int, state: List<MutableList<Int>>) {
    if (state[i][j] == 9) {
        state[i][j] = -1
        allNeighbours(i, j, state).forEach { tryToFlash(it.first, it.second, state) }
    } else if (state[i][j] != -1) state[i][j] = state[i][j] + 1
}

private fun allNeighbours(i: Int, j: Int, state: List<MutableList<Int>>): List<Pair<Int, Int>> {
    return listOf(
        i - 1 to j - 1,
        i - 1 to j,
        i - 1 to j + 1,
        i to j - 1,
        i to j + 1,
        i + 1 to j - 1,
        i + 1 to j,
        i + 1 to j + 1
    ).filter { it.first >= 0 && it.first < state.size }
        .filter { it.second >= 0 && it.second < state.first().size }
}
