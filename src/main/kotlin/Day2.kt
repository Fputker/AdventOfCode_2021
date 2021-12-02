import java.io.File
import java.lang.Exception

fun readInputIntoListOfString(filePath: String): List<String> {
    val input: MutableList<String> = mutableListOf()
    File(filePath).forEachLine { input.add(it) }
    return input
}

fun parseInstruction(instruction: String): Pair<String, Int> {
    val (direction, quantity) = instruction.split(' ')
    return Pair(direction, quantity.toInt())
}

fun executeInstruction(instruction: Pair<String, Int>): Pair<Int, Int> {
    return when (instruction.first) {
        "forward" -> instruction.second to 0
        "down" -> 0 to instruction.second
        "up" -> 0 to -instruction.second
        else -> throw Exception("unknown instruction")
    }
}

fun processPartOneInstructions() {
    val instructions =
        readInputIntoListOfString("/Users/fputker/Ideaprojects/AdventOfCode_2021/src/main/kotlin/input/day2.txt")
    val output = instructions.map { parseInstruction(it) }
        .fold(0 to 0) { position, instruction ->
            position.first + executeInstruction(instruction).first to
                    position.second + executeInstruction(instruction).second
        }
    println("The final position = " + output.first * output.second)
}

fun getNewPositionUsingForwardinstruction(instruction: String, position: Triple<Int, Int, Int>): Triple<Int, Int, Int> {
    val value = parseInstruction(instruction).second
    return Triple(position.first + value, position.second, position.third + (position.second * value))
}

fun getNewPositionUsingDowninstruction(instruction: String, position: Triple<Int, Int, Int>): Triple<Int, Int, Int> {
    val value = parseInstruction(instruction).second
    return Triple(position.first, position.second + value, position.third)
}

fun getNewPositionUsingUpinstruction(instruction: String, position: Triple<Int, Int, Int>): Triple<Int, Int, Int> {
    val value = parseInstruction(instruction).second
    return Triple(position.first, position.second - value, position.third)
}

fun main() {
    processPartOneInstructions()
//    val instructions =
//        readInputIntoListOfString("/Users/fputker/Ideaprojects/AdventOfCode_2021/src/main/kotlin/input/day2.txt")
//    var position: Triple<Int,Int,Int> = Triple(0,0,0)
//    for (instruction in instructions){
//        when(instruction[0]){
//            'f' -> {position = getNewPositionUsingForwardinstruction(instruction, position)}
//            'd' -> {position = getNewPositionUsingDowninstruction(instruction, position)}
//            'u' -> {position = getNewPositionUsingUpinstruction(instruction, position)}
//        }
//    }
//    println("The final answer is: " + position.first * position.third)

}

class Position(triple: Triple<Int, Int, Int>) {
    val horizontal: Int = triple.first
    val aim: Int = triple.second
    val depth: Int = triple.third
}