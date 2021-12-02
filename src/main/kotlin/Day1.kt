import java.io.File

fun readInputIntoListOfInt(filePath: String): List<Int> {
    val input: MutableList<Int> = mutableListOf()
    File(filePath).forEachLine { input.add(it.toInt()) }
    return input
}

fun main() {
    val measurements = readInputIntoListOfInt("/Users/fputker/Ideaprojects/AdventOfCode_2021/src/main/kotlin/input/day1.txt")
    println("The first answer is: " + measurements.zipWithNext().count { it.first < it.second })

    println("The second answer is: " +
            measurements.windowed(3, 1, partialWindows = false).map { it.sum() }.zipWithNext()
                .count { it.first < it.second })
}