import kotlin.math.absoluteValue

fun main() {
    val (left, right) = input.map { line ->
        val (a, b) = line.split("\\s+".toRegex()).map { it.toInt() }
        a to b
    }.unzip()
    println(part1(left, right))
    println(part2(left, right))
}

fun part1(left: List<Int>, right: List<Int>): Int {
    val leftSorted = left.sorted()
    val rightSorted = right.sorted()
    return leftSorted.withIndex().sumOf { (index, value) -> value.minus(rightSorted[index]).absoluteValue }
}

fun part2(left: List<Int>, right: List<Int>): Int {
    return left.sumOf{value -> value * right.count {it == value}}
}

private val input: List<String> by lazy {readInputAsStream()}

fun readInputAsStream(): List<String> =  object {}.javaClass.getResourceAsStream("Day1.input").bufferedReader().readLines()
