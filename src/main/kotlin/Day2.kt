import java.lang.Exception

fun parseInstruction(instruction: String): Pair<String, Int> {
    val (direction, quantity) = instruction.split(' ')
    return Pair(direction, quantity.toInt())
}

fun executePartOneInstruction(instruction: Pair<String, Int>): Pair<Int, Int> {
    return when (instruction.first) {
        "forward" -> instruction.second to 0
        "down" -> 0 to instruction.second
        "up" -> 0 to -instruction.second
        else -> throw Exception("unknown instruction")
    }
}

fun processPartOneInstructions() {
    val instructions = readInput("day2")
    val output = instructions.map { parseInstruction(it) }
        .fold(0 to 0) { position, instruction ->
            position.first + executePartOneInstruction(instruction).first to
                    position.second + executePartOneInstruction(instruction).second
        }
    println("The final position of part 1 = " + output.first * output.second)
}

fun processPartTwoInstructions(){
    val instructions = readInput("day2")
    var position: Triple<Int,Int,Int> = Triple(0,0,0)
    for (instruction in instructions){
        when(instruction[0]){
            'f' -> {position = getNewPositionUsingForwardinstruction(instruction, position)}
            'd' -> {position = getNewPositionUsingDowninstruction(instruction, position)}
            'u' -> {position = getNewPositionUsingUpinstruction(instruction, position)}
        }
    }
    println("The final position of part 2 = " + position.first * position.third)
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
    processPartTwoInstructions()
}

class Position(triple: Triple<Int, Int, Int>) {
    val horizontal: Int = triple.first
    val aim: Int = triple.second
    val depth: Int = triple.third
}