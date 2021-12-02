import java.io.File

fun readInputIntoListOfString(filePath: String): List<String> {
    val input: MutableList<String> = mutableListOf()
    File(filePath).forEachLine { input.add(it) }
    return input
}

fun directionToNumber(direction: String): Int {
    return "\\d".toRegex().find(direction)?.value.toString().toInt()
}

fun simpleDirections(){
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

fun getNewPositionUsingForwardDirection(direction: String, position: Triple<Int,Int,Int>): Triple<Int,Int,Int>{
    val value = directionToNumber(direction)
    return Triple(position.first+value,position.second,position.third + (position.second * value) )
}

fun getNewPositionUsingDownDirection(direction: String, position: Triple<Int,Int,Int>): Triple<Int,Int,Int>{
    val value = directionToNumber(direction)
    return Triple(position.first,position.second + value,position.third)
}

fun getNewPositionUsingUpDirection(direction: String, position: Triple<Int,Int,Int>): Triple<Int,Int,Int>{
    val value = directionToNumber(direction)
    return Triple(position.first,position.second - value,position.third)
}

fun main() {
    val directions =
        readInputIntoListOfString("/Users/fputker/Ideaprojects/AdventOfCode_2021/src/main/kotlin/input/day2.txt")
    var position: Triple<Int,Int,Int> = Triple(0,0,0)
    for (direction in directions){
        when(direction[0]){
            'f' -> {position = getNewPositionUsingForwardDirection(direction, position)}
            'd' -> {position = getNewPositionUsingDownDirection(direction, position)}
            'u' -> {position = getNewPositionUsingUpDirection(direction, position)}
        }
    }
    println("The final answer is: " + position.first * position.third)

}

class Position(triple: Triple<Int, Int, Int>){
    val horizontal: Int = triple.first
    val aim: Int = triple.second
    val depth: Int = triple.third
}