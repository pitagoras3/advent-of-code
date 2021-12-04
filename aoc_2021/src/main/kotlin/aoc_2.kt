fun main() {
    val courses: List<Course> = readInputFile("input_2.txt")
        .map { it.split(" ") }
        .map {
            Course(
                Course.Direction.valueOf(it.first().uppercase()),
                it.last().toInt()
            )
        }

    println(part1(courses))
    println(part2(courses))
}

private fun part1(courses: List<Course>): Int =
    courses.fold((0 to 0)) { (horizontal, depth), currentCourse ->
        when (currentCourse.direction) {
            Course.Direction.UP -> (horizontal to depth - currentCourse.amount)
            Course.Direction.DOWN -> (horizontal to depth + currentCourse.amount)
            Course.Direction.FORWARD -> (horizontal + currentCourse.amount to depth)
        }
    }.let { it.first * it.second }

private fun part2(courses: List<Course>): Int =
    courses.fold(Triple(0, 0, 0)) { (horizontal, depth, aim), currentCourse ->
        when (currentCourse.direction) {
            Course.Direction.UP -> Triple(horizontal, depth, aim - currentCourse.amount)
            Course.Direction.DOWN -> Triple(horizontal, depth, aim + currentCourse.amount)
            Course.Direction.FORWARD -> Triple(
                horizontal + currentCourse.amount,
                depth + aim * currentCourse.amount,
                aim
            )
        }
    }.let { it.first * it.second }

private data class Course(
    val direction: Direction,
    val amount: Int
) {
    enum class Direction {
        UP, DOWN, FORWARD
    }
}
