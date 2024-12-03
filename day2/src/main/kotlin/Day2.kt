import kotlin.math.absoluteValue

fun main() {
    println(part1())
    println(part2())
}

fun part1(): Int {
    return input.map { line -> line.split(" ").map { it.toInt() }.asSequence() }.filter { isSafe(it) }.size
}

fun part2(): Int {
    return input.map { line -> line.split(" ").map { it.toInt() } }.filter { isSafeTolerate1Mistake(it) }.size
}

private fun isSafe(line: Sequence<Int>): Boolean {
    if (line.zipWithNext().any { (a, b) -> (a - b).absoluteValue > 3 || a == b }){
        return false
    }
    val ascendingPairs = line.zipWithNext().any{(a,b) -> (a < b)}
    val descendingPairs = line.zipWithNext().any{(a,b) -> (a > b)}
    return (ascendingPairs && !descendingPairs) || (!ascendingPairs && descendingPairs)
}

private fun isSafeTolerate1Mistake(line: List<Int>): Boolean {
    return isSafe(line.asSequence()) || line.indices.any { index ->
        isSafe(line.toMutableList().apply { removeAt(index) }.asSequence())
    }
}

private val input: List<String> by lazy { readInputAsStream() }

fun readInputAsStream(): List<String> =
    object {}.javaClass.getResourceAsStream("Day2.input").bufferedReader().readLines()
