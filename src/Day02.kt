fun main() {

    data class Position(val horizontal: Int = 0, val depth: Int = 0)

    fun Position.result() = horizontal * depth

    fun part1(input: List<String>) = input
        .fold(initial = Position()) { position, line ->
            val (command, value) = line.split(' ')
            when (command) {
                "forward" -> position.copy(horizontal = position.horizontal + value.toInt())
                "down" -> position.copy(depth = position.depth + value.toInt())
                "up" -> position.copy(depth = position.depth - value.toInt())
                else -> error(command)
            }
        }.result()

    data class Submarine(val position: Position = Position(), val aim: Int = 0)

    fun part2(input: List<String>) = input
        .fold(initial = Submarine()) { submarine, line ->
            val (command, value) = line.split(' ')
            when (command) {
                "forward" -> submarine.copy(
                    position = Position(
                        horizontal = submarine.position.horizontal + value.toInt(),
                        depth = submarine.position.depth + value.toInt() * submarine.aim,
                    ),
                )
                "down" -> submarine.copy(aim = submarine.aim + value.toInt())
                "up" -> submarine.copy(aim = submarine.aim - value.toInt())
                else -> error(command)
            }
        }.position.result()

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day02_test")
    check(part1(testInput) == 150)
    check(part2(testInput) == 900)

    val input = readInput("Day02")
    println(part1(input))
    println(part2(input))
}
