private const val day3 = "day3"

// create transposed lists
// calculate total -> transform to binary
// then get resulting bits and convert to Int

fun <T : Any> transpose(input: List<List<T>>): List<List<T>> {
    //display(input)
    val columnCount = input[0].size
    //println("columnCount = $columnCount")
    val rowCount = input.size
    //println("rowCount = $rowCount")
    val transpose = mutableListOf<List<T>>()
    for (i in 0..columnCount - 1) {
        val column = mutableListOf<T>()
        for (j in 0..rowCount - 1) {
            column.add(input[j][i])
        }
        transpose.add(column)
    }
    //display(transpose)
    return transpose
}


fun display(matrix: List<List<Any>>) {
    println("The matrix is: ")
    for (row in matrix) {
        for (column in row) {
            print("$column    ")
        }
        println()
    }
}

private fun part1() {
    val inputAsListofListOfInt = readInput(day3).map { s -> s.toCharArray().map { it.digitToInt() } }
    val transposed = transpose(inputAsListofListOfInt)
    val a = transposed.map { it.sum() }
    val gamma = "000100011001".toInt(2)
    val epsilon = "111011100110".toInt(2)
    println("$gamma times $epsilon = ${gamma * epsilon}")
}

fun part2(inputList: List<List<Int>>): Int {

    val reducingList1 = findMostCommonBits(inputList)
    val reducingList2 = findLeastCommonBits(inputList)

    println(reducingList1)
    println(reducingList2)
    val oxygen = reducingList1[0].joinToString("").toInt(2)
    val co2 = reducingList2[0].joinToString("").toInt(2)
    val answer = oxygen * co2
    println(" answer to part two is $oxygen times $co2 = $answer")
    return answer
}

fun findMostCommonBits(inputList: List<List<Int>>): List<List<Int>> {
    var reducingList = inputList
    for (i in 0..inputList.size - 1) {
        var arrayOfMostCommonBits = determineMostCommonBits(reducingList)
        val bitToFilterFor = Pair(arrayOfMostCommonBits[i], i)
        println("bit=${bitToFilterFor.first}, position is ${bitToFilterFor.second}")
        reducingList = filterForBitInPosition(bitToFilterFor, reducingList)
        if (reducingList.size == 1) {
            return reducingList
        }

        println(reducingList)
    }
    return reducingList
}

private fun findLeastCommonBits(inputList: List<List<Int>>): List<List<Int>> {
    var reducingList = inputList
    for (i in 0..inputList.size - 1) {
        var arrayOfLeastCommonBits = determineLeastCommonBits(reducingList)
        val bitToFilterFor = Pair(arrayOfLeastCommonBits[i], i)
        println("bit=${bitToFilterFor.first}, position is ${bitToFilterFor.second}")
        reducingList = filterForBitInPosition(bitToFilterFor, reducingList)
        if (reducingList.size == 1) {
            return reducingList
        }

        println(reducingList)
    }
    return reducingList
}

private fun filterForBitInPosition(
    bitToMatch: Pair<Int, Int>,
    listToFilter: List<List<Int>>
): List<List<Int>> {
    println("bit=${bitToMatch.first}")
    println("i=${bitToMatch.second}")
    return listToFilter.filter { isBitInPosition(it, bitToMatch.first, bitToMatch.second) }
}

fun determineMostCommonBits(input: List<List<Int>>): List<Int> {
    val transposed = transpose(input)
    println("transposed = $transposed")
    val summed = transposed.map { it.sum() }
    println("summed = $summed")
    val backToBits = summed.map { if (it >= (input.size.toFloat() / 2)) 1 else 0 }
    println("backToBits = $backToBits")
    return backToBits
}

fun determineLeastCommonBits(input: List<List<Int>>) = transpose(input)
    .map { it.sum().toFloat() }
    .map { if (it >= (input.size.toFloat() / 2)) 0 else 1 }

fun isBitInPosition(listToCheck: List<Int>, bit: Int, position: Int): Boolean {
    return (listToCheck[position] == bit)
}

fun main() {

    part1()

    val input = readInput(day3)
    val inputAsListofListOfInt = input.map { s -> s.toCharArray().map { it.digitToInt() } }
    part2(inputAsListofListOfInt)
}

