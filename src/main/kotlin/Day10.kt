fun main() {
    val answer = readInput("Day10").map { findInvalidClose(it) }.filter { it!='1' }

    println("part1=${calculateScore(answer)}")
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
            if (inputArray[i].matchingBracket(open.last())) {
                open.removeLast()
            } else {
                return inputArray[i]
            }
        }
    }
    return '1'
}

fun Char.isChunkOpening(): Boolean = this in listOf<Char>('(', '[', '{', '<')

fun Char.matchingBracket(match: Char): Boolean {
    return if (this == '}') match == '{' else if (this == ']') match == '[' else if (this == ')') match == '(' else if (this == '>') match == '<' else false
}

fun calculateScore(charList: List<Char>): Long {
    return charList.count { it == ')' } * 3L + charList.count { it == ']' } * 57L + charList.count { it == '}' } * 1197L + charList.count { it == '>' } * 25137L
}