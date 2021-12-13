fun main() {

    fun part1(input: List<String>): Int  {
        val (gammaRateBinary, epsilonRateBinary) = input
            .map { line -> line.map { if (it == '1') 1 else -1 } }
            .reduce { acc, ints -> acc.zip(ints) { a, b -> a + b } }
            .map { if (it > 0) "1" to "0" else "0" to "1" }
            .unzip()
        val gammaRate = gammaRateBinary.joinToString(separator = "").toInt(radix = 2)
        val epsilonRate = epsilonRateBinary.joinToString(separator = "").toInt(radix = 2)
        return gammaRate * epsilonRate
    }

    data class OxygenGeneratorRating(
        val head: String = "",
        val tails: List<String>,
    ) {
        fun value(): Int {
            if (tails.size <= 1) return (head + tails.singleOrNull().orEmpty()).toInt(radix = 2)
            val (ones, zeros) = tails.partition { it.first() == '1' }
            return if (ones.size >= zeros.size) {
                OxygenGeneratorRating(
                    head = head + "1",
                    tails = ones.map { it.drop(1) },
                )
            } else {
                OxygenGeneratorRating(
                    head = head + "0",
                    tails = zeros.map { it.drop(1) },
                )
            }.value()
        }
    }

    data class Co2ScrubberRating(
        val head: String = "",
        val tails: List<String>,
    ) {
        fun value(): Int {
            if (tails.size <= 1) return (head + tails.singleOrNull().orEmpty()).toInt(radix = 2)
            val (ones, zeros) = tails.partition { it.first() == '1' }
            return if (ones.size < zeros.size) {
                Co2ScrubberRating(
                    head = "${head}1",
                    tails = ones.map { it.drop(1) },
                )
            } else {
                Co2ScrubberRating(
                    head = "${head}0",
                    tails = zeros.map { it.drop(1) },
                )
            }.value()
        }
    }

    fun part2(input: List<String>) =
        OxygenGeneratorRating(tails = input).value() * Co2ScrubberRating(tails = input).value()

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day03_test")
    check(part1(testInput) == 198)
    check(part2(testInput) == 230)

    val input = readInput("Day03")
    println(part1(input))
    println(part2(input))
}
