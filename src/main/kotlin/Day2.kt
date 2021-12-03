import java.lang.Exception

private const val day2 = "day2"

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

fun executePartTwoInstruction(instruction: Pair<String, Int>, position: Triple<Int, Int, Int>): Triple<Int, Int, Int> {
    return when (instruction.first) {
        "forward" -> Triple(position.first + instruction.second, position.second, position.third + (position.second * instruction.second))
        "down" -> Triple(position.first, position.second + instruction.second, position.third)
        "up" -> Triple(position.first, position.second - instruction.second, position.third)
        else -> throw Exception("unknown instruction")
    }
}

fun processPartTwoInstructions(){
    val instructions = readInput("day2")
    val result = instructions.map { parseInstruction(it) }
        .fold(Triple(0,0,0)) {
            position, instruction -> executePartTwoInstruction(instruction, position)
        }
    println("The final position of part 1 = " + result.first * result.third)
}

fun main() {
    processPartOneInstructions()
    processPartTwoInstructions()
}
