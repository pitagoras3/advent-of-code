fun main() {
    val input = readInputFile("input_6.txt")
        .map { it.split(",") }
        .flatMap { it.map { it.toInt() } }

    println(solution(input, 80)) // part1
    println(solution(input, 256)) // part2
}

private fun solution(input: List<Int>, generations: Int): Long = input.sumOf { amountOfFishAfterAllGenerations(it, generations) }

private val cache = mutableMapOf<Pair<Int, Int>, Long>()

private fun amountOfFishAfterAllGenerations(number: Int, generations: Int): Long =
    cache.getOrElse((number to generations)) {
        if (number >= generations) 1L
        else if (number == 0) { cache.getOrPut(6 to (generations - 1)) { amountOfFishAfterAllGenerations(6, generations - 1) } }
        else { cache.getOrPut(0 to (generations - number)) { amountOfFishAfterAllGenerations(0, generations - number) } +
                    cache.getOrPut(9 to (generations - number)) { amountOfFishAfterAllGenerations(9, generations - number) }
        }
    }
