import java.io.File

fun readInputIntoListOfString(filePath: String): List<String> {
    val input: MutableList<String> = mutableListOf()
    File(filePath).forEachLine { input.add(it) }
    return input
}

fun parseInstruction(instruction: String): Pair<String, Int> {
    val (direction, quantity) = instruction.split(' ')
    return Pair(direction, quantity.toInt())
}

fun simpleinstructions(){
    val instructions =
        readInputIntoListOfString("/Users/fputker/Ideaprojects/AdventOfCode_2021/src/main/kotlin/input/day2.txt")

    val forward = instructions.filter { it.contains("forward") }
    val down = instructions.filter { it.contains("down") }
    val up = instructions.filter { it.contains("up") }

    val forwardNumbers: MutableList<Int> = mutableListOf()
    val downNumbers: MutableList<Int> = mutableListOf()
    val upNumbers: MutableList<Int> = mutableListOf()

    forward.forEach { forwardNumbers.add(parseInstruction(it).second) }
    down.forEach { downNumbers.add(parseInstruction(it).second) }
    up.forEach { upNumbers.add(parseInstruction(it).second * -1)  }

    println( "the answer is : " + forwardNumbers.sum() * ( downNumbers.sum() + upNumbers.sum()))
}

fun getNewPositionUsingForwardinstruction(instruction: String, position: Triple<Int,Int,Int>): Triple<Int,Int,Int>{
    val value = parseInstruction(instruction).second
    return Triple(position.first+value,position.second,position.third + (position.second * value) )
}

fun getNewPositionUsingDowninstruction(instruction: String, position: Triple<Int,Int,Int>): Triple<Int,Int,Int>{
    val value = parseInstruction(instruction).second
    return Triple(position.first,position.second + value,position.third)
}

fun getNewPositionUsingUpinstruction(instruction: String, position: Triple<Int,Int,Int>): Triple<Int,Int,Int>{
    val value = parseInstruction(instruction).second
    return Triple(position.first,position.second - value,position.third)
}

fun main() {
    val instructions =
        readInputIntoListOfString("/Users/fputker/Ideaprojects/AdventOfCode_2021/src/main/kotlin/input/day2.txt")
    var position: Triple<Int,Int,Int> = Triple(0,0,0)
    for (instruction in instructions){
        when(instruction[0]){
            'f' -> {position = getNewPositionUsingForwardinstruction(instruction, position)}
            'd' -> {position = getNewPositionUsingDowninstruction(instruction, position)}
            'u' -> {position = getNewPositionUsingUpinstruction(instruction, position)}
        }
    }
    println("The final answer is: " + position.first * position.third)

}

class Position(triple: Triple<Int, Int, Int>){
    val horizontal: Int = triple.first
    val aim: Int = triple.second
    val depth: Int = triple.third
}