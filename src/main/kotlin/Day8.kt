/*
* 1 = C + F -> length = 2
* 4 = B + C + D + F -> length = 4
* 7 = A + C + F -> length = 3
* 8 = A + B + C + D + E + F + G length = 7
* */

// part 1 --> Count number of times a part of signal //

fun countOneFourEightNine(input: List<String>): Int {
    val predicate: (String) -> Boolean = { it.length == 2 || it.length == 3 || it.length == 4 || it.length == 7 }
    val parsedIput = parseInput(input)
    return parsedIput.count(predicate)
}

fun parseInput(input: List<String>): List<String> {
    val listOflistOfSIgnalOutput = input.map { it.split("|") }
    println(listOflistOfSIgnalOutput)
    val listOfOutput = listOflistOfSIgnalOutput.map { it[1].trim() }
    println(listOfOutput)
    val listOfsplitOutput = listOfOutput.map { it.split(" ") }
    println(listOfsplitOutput)
    val oneList = listOfsplitOutput.flatMap { it }
    println(oneList)
    return oneList
}

fun Day8part1(){
    val input = readInput("Day8")
    println("answer to part 1 = : ${countOneFourEightNine(input)}")
}

fun getSegmentMapping(signalList: List<String>): List<String>{
    return
}

fun main() {
    Day8part1()
}