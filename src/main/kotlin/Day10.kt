import java.lang.Math.abs

fun main() {
    val answer = readInput("Day10").map { findInvalidClose(it) }.filter { it != '1' }
    println("part1=${calculateScore(answer)}")

    val incompleteLines = readInput("Day10").map { findIncompleteLines(it) }.filter { it.size > 0 }
    val sortedScores = incompleteLines.map { completeSequence(it) }
        .map {scoreforLine(it) }
        .sorted()
    val answerPart2 = sortedScores[sortedScores.size/2]
    println("part2=${answerPart2}")
}

fun findInvalidClose(input: String): Char {
    val open = mutableListOf<Char>()
    val inputArray = input.toCharArray()
    for (i in inputArray.indices) {
        if (i == 0 && inputArray[i] in listOf<Char>('(', '[', '{', '<')) {
            open.add(inputArray[i])
        } else if (inputArray[i].isChunkOpening()) {
            open.add(inputArray[i])
        } else if (!inputArray[i].isChunkOpening()) {
            if (inputArray[i].isMatchingBracket(open.last())) {
                open.removeLast()
            } else {
                return inputArray[i]
            }
        }
    }
    return '1'
}

fun findIncompleteLines(input: String): List<Char> {
    val open = mutableListOf<Char>()
    val inputArray = input.toCharArray()
    for (i in inputArray.indices) {
        if (i == 0 && inputArray[i] in listOf<Char>('(', '[', '{', '<')) {
            open.add(inputArray[i])
        } else if (inputArray[i].isChunkOpening()) {
            open.add(inputArray[i])
        } else if (!inputArray[i].isChunkOpening()) {
            if (inputArray[i].isMatchingBracket(open.last())) {
                open.removeLast()
            } else {
                return listOf()
            }
        }
        if (i == inputArray.size - 1) {
            return open
        }
    }
    return listOf('E')
}

fun completeSequence(input: List<Char>): List<Char> {
    return input.reversed().map { it.getMatchingBracket() }
}

fun Char.isChunkOpening(): Boolean = this in listOf<Char>('(', '[', '{', '<')

fun Char.isMatchingBracket(match: Char): Boolean {
    return if (this == '}') match == '{' else if (this == ']') match == '[' else if (this == ')') match == '(' else if (this == '>') match == '<' else false
}

fun Char.getMatchingBracket(): Char {
    return if (this == '{') '}' else if (this == '[') ']' else if (this == '(') ')' else if (this == '<') '>' else 'E'
}

fun calculateScore(charList: List<Char>): Long {
    return charList.count { it == ')' } * 3L + charList.count { it == ']' } * 57L + charList.count { it == '}' } * 1197L + charList.count { it == '>' } * 25137L
}

fun scoreforLine(charList: List<Char>): Long {
    var score = 0L
    for (i in charList.indices) {
        score *= 5L
        score += scoreForChar(charList[i])
    }
    return abs(score)
}

fun scoreForChar(char: Char): Long {
    if (char == ')') {
        return 1L
    } else if (char == ']') {
        return 2L
    } else if (char == '}') {
        return 3L
    } else if (char == '>') {
        return 4L
    }
    return 0L
}