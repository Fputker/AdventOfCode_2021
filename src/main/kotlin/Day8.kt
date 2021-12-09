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
    val listOfOutput = listOflistOfSIgnalOutput.map { it[1].trim() }
    val listOfsplitOutput = listOfOutput.map { it.split(" ") }
    val oneList = listOfsplitOutput.flatMap { it }
    return oneList
}

fun Day8part1() {
    val input = readInput("Day8")
    println("answer to part 1 = : ${countOneFourEightNine(input)}")
}

fun getSegmentMapping(signalList: List<String>): List<Pair<Int, String>> {
    var mapOfBitsToNumbers = mutableListOf(
        Pair(1, signalList.filter { it.length == 2 }[0]),
        Pair(4, signalList.filter { it.length == 4 }[0]),
        Pair(7, signalList.filter { it.length == 3 }[0]),
        Pair(8, signalList.filter { it.length == 7 }[0])
    )

    val segmentsAsChars = signalList.map { it.toList() }.flatten()
    val segmentCounts = segmentsAsChars.map { char -> Pair(char, segmentsAsChars.count { it == char }) }.distinct()

    val mapOfBitsToSegments = mutableListOf(
        Pair("c", segmentCounts.find { it.second == 9 }?.first.toString()),
        Pair("e", segmentCounts.find { it.second == 4 }?.first.toString()),
        Pair("f", segmentCounts.find { it.second == 6 }?.first.toString())
    )

    // add bit 1 to bits to segments
    mapOfBitsToSegments.add(Pair("a", subtractString(mapOfBitsToNumbers.filter { (it.first == 7) }[0].second, mapOfBitsToNumbers.filter { (it.first == 1) }[0].second)))

    mapOfBitsToSegments.add(Pair("b", subtractString(mapOfBitsToNumbers.filter { (it.first == 1) }[0].second, mapOfBitsToSegments.filter { it.first == "c" }[0].second)))

    mapOfBitsToNumbers.add(Pair(2, subtractString(getBitsForNumber(8, mapOfBitsToNumbers), "${getBitBySegment("c", mapOfBitsToSegments)}${getBitBySegment("f", mapOfBitsToSegments)}")))

    mapOfBitsToNumbers.add(Pair(9, subtractString(getBitsForNumber(8, mapOfBitsToNumbers), getBitBySegment("e", mapOfBitsToSegments))))

    mapOfBitsToNumbers.add(Pair(6, subtractString(getBitsForNumber(8, mapOfBitsToNumbers), getBitBySegment("b", mapOfBitsToSegments))))

    mapOfBitsToNumbers.add(Pair(3, subtractString(getBitsForNumber(8, mapOfBitsToNumbers), getBitBySegment("e", mapOfBitsToSegments) + getBitBySegment("f", mapOfBitsToSegments))))

    mapOfBitsToNumbers.add(Pair(5, subtractString(getBitsForNumber(8, mapOfBitsToNumbers), getBitBySegment("b", mapOfBitsToSegments) + getBitBySegment("e", mapOfBitsToSegments))))

    val orderedSignalList = signalList.map { it.orderString() }.sorted()

    val definedSignals = mapOfBitsToNumbers.map { it.second.orderString() }.sorted()

    val bitsFor0 = orderedSignalList.filterNot { definedSignals.indexOf(it) > -1 }[0]

    mapOfBitsToNumbers.add(Pair(0, bitsFor0))


    val sorted = mapOfBitsToNumbers.sortedBy { it.first }
    println("sorted = ${sorted}")
    val sortedAgain = sorted.map {
        Pair(it.first, it.second.toCharArray().sorted().joinToString(""))
    }
    return sortedAgain
}

fun getBitsForNumber(number: Int, mapOfBitsToNumbers: List<Pair<Int, String>>): String {
    val a = mapOfBitsToNumbers.filter { it.first == number }[0].second
    return a
}

fun getBitBySegment(segment: String, mapOfBitsToSegments: List<Pair<String, String>>): String {
    val a = mapOfBitsToSegments.filter { it.first == segment }[0].second
    return a
}

fun String.orderString(): String {
    return this.toCharArray().sorted().joinToString("")
}

fun subtractString(s1: String, s2: String): String {
    val a = s1  // "abc"
    val b = s2 //"cb"
    return s1.filterNot { s2.indexOf(it) > -1 }
}

fun findNumberForSegment(segment: String, segmentMapping: List<Pair<Int, String>>): Int {
    val match = segmentMapping.filter { it.second == segment.orderString() }
    return match[0].first
}

fun Day8part2() {
    val input = readInput("Day8")
    println("answer to part 2 = ${input.map { findAnswerForSingleLine(it) }.sum()}")
}

fun findAnswerForSingleLine(s: String): Int {
    val listOfSignalWithAnswer = s.split("|").map { it.trim() }
    val segmentMap = getSegmentMapping(listOfSignalWithAnswer[0].split(" "))
    val answer = listOfSignalWithAnswer[1]
        .split(" ")
        .map { findNumberForSegment(it, segmentMap) }
        .joinToString("")
        .toInt()
    return answer
}

fun main() {
    Day8part1()
    Day8part2()
}