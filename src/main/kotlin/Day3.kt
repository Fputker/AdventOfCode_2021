import java.util.stream.IntStream.range

private const val day3 = "day3"

// create transposed lists
// calculate total -> transform to binary
// then get resulting bits and convert to Int

fun <T: Any> transpose(input: List<List<T>>): List<List<T>> {
    display(input)
    val columnCount = input[0].size
    println("columnCount = $columnCount")
    val rowCount = input.size
    println("rowCount = $rowCount")
    val transpose = mutableListOf<List<T>>()
    for (i in 0..columnCount - 1) {
        val column = mutableListOf<T>()
        for (j in 0..rowCount - 1) {
            column.add(input[j][i])
        }
        transpose.add(column)
    }
    display(transpose)
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

fun createTwelveLists(input: List<String>): List<List<Int>> {
    input.map { s -> s.toCharArray() }
    val a = mutableListOf<Int>()
    val b = mutableListOf<Int>()
    val c = mutableListOf<Int>()
    val d = mutableListOf<Int>()
    val e = mutableListOf<Int>()
    val f = mutableListOf<Int>()
    val g = mutableListOf<Int>()
    val h = mutableListOf<Int>()
    val x = mutableListOf<Int>()
    val j = mutableListOf<Int>()
    val k = mutableListOf<Int>()
    val l = mutableListOf<Int>()
    for (i in range(0, input.size)) {
        a.add(input[i][0].digitToInt())
        b.add(input[i][1].digitToInt())
        c.add(input[i][2].digitToInt())
        d.add(input[i][3].digitToInt())
        e.add(input[i][4].digitToInt())
        f.add(input[i][5].digitToInt())
        g.add(input[i][6].digitToInt())
        h.add(input[i][7].digitToInt())
        x.add(input[i][8].digitToInt())
        j.add(input[i][9].digitToInt())
        k.add(input[i][10].digitToInt())
        l.add(input[i][11].digitToInt())
    }
    return mutableListOf<List<Int>>(a, b, c, d, e, f, g, h, x, j, k, l)
}

private fun part1() {
    val a = createTwelveLists(readInput(day3)).map { it.sum() }
    println(a)
    val gamma = "000100011001".toInt(2)
    val epsilon = "111011100110".toInt(2)
    println("$gamma times $epsilon = ${gamma * epsilon}")
}

private fun part2() {
    val input = readInput(day3)
    val arrayOfMostCommonBits = determineMostCommonBits(input)
    println(" most common bits are $arrayOfMostCommonBits")

    var inputAsIntArray = input.map { s -> s.toCharArray().map { it.digitToInt() } } as MutableList<List<Int>>

    var i = 0
    for (bit in arrayOfMostCommonBits) {
        println("bit=$bit")
        println("i=$i")
        inputAsIntArray = inputAsIntArray.filter { isBitInPosition(it, bit, i) } as MutableList<List<Int>>
        i += 1
        println(inputAsIntArray)
    }

    val leastCommon = determineLeastCommonBits(input)
    println(" least common bits are $leastCommon")
    var leastCommonAsIntArray = input.map { s -> s.toCharArray().map { it.digitToInt() } } as MutableList<List<Int>>
    var j = 0
    for (bit in leastCommon) {
        println("bit=$bit")
        println("j=$j")
        leastCommonAsIntArray =
            leastCommonAsIntArray.filter { it -> isBitInPosition(it, bit, j) } as MutableList<List<Int>>
        j += 1
        println(leastCommonAsIntArray)
    }

    val oxygen = "000100011011".toInt(2)
    val co2 = "000100011011".toInt(2)
    println(" answer to part two is $oxygen times $co2 = ${oxygen * co2}")
}

fun determineMostCommonBits(input: List<String>) = createTwelveLists(input)
    .map { it.sum() }
    .map { if (it >= input.size / 2) 1 else 0 }

fun determineLeastCommonBits(input: List<String>) = createTwelveLists(input)
    .map { it.sum() }
    .map { if (it <= input.size / 2) 0 else 1 }

fun isBitInPosition(listToCheck: List<Int>, bit: Int, position: Int): Boolean {
    return (listToCheck[position] == bit)
}

fun main() {

    part1()
    part2()
}

