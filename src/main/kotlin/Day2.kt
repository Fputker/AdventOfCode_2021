import java.io.File

fun readInputIntoListOfString(filePath: String): List<String> {
    val input: MutableList<String> = mutableListOf()
    File(filePath).forEachLine { input.add(it) }
    return input
}

fun directionToNumber(direction: String): Int {
    return "\\d".toRegex().find(direction)?.value.toString().toInt()
}

fun main() {
    val directions =
        readInputIntoListOfString("/Users/fputker/Ideaprojects/AdventOfCode_2021/src/main/kotlin/input/day2.txt")

    val forward = directions.filter { it.contains("forward") }
    val down = directions.filter { it.contains("down") }
    val up = directions.filter { it.contains("up") }

    val forwardNumbers: MutableList<Int> = mutableListOf()
    val downNumbers: MutableList<Int> = mutableListOf()
    val upNumbers: MutableList<Int> = mutableListOf()

    forward.forEach { forwardNumbers.add(directionToNumber(it)) }
    down.forEach { downNumbers.add(directionToNumber(it)) }
    up.forEach { upNumbers.add(directionToNumber(it) * -1) }

    println( "the answer is : " + forwardNumbers.sum() * ( downNumbers.sum() + upNumbers.sum()))


}