import java.math.BigInteger
import java.util.stream.IntStream.range

private const val day3 = "day3"

// create transposed lists
// calculate total -> transform to binary
// then get resulting bits and convert to Int

//fun transpose(input: MutableList<MutableList<Int>>): MutableList<MutableList<Int>>{
//    for (i in range(0, input[0].size)){
//        input.let
//    }
//    return mutableListOf(mutableListOf())
//}

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
    println(arrayOfMostCommonBits)

    println(input.map { s -> s.toCharArray().map { it.digitToInt() } })

}

private fun determineMostCommonBits(input: List<String>) = createTwelveLists(input)
    .map { it.sum() }
    .map { if (it > input.size / 2) 1 else 0 }

private fun isBitInPosition(listToCheck: List<Int>, bit: Int, position: Int): Boolean {
    return (listToCheck[position] == bit)
}

fun main() {

    part1()
    part2()
}

