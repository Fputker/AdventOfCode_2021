/*
Step 1 read file to boards
    1a read strings to list of Integers
Step 2 determine when board wins (remove matching ints and check for empty rows?)
step 3 find first winning board
step 4 calculate score of board
*/

fun main() {
    val numbersDrawn = readInput("day4")
    val listOfBoards = readBoardsFromInput(readInput("day4_boards"))
    for (number in numbersDrawn){
        listOfBoards.map { BingoBoard(it) }
    }
}


fun readBoardsFromInput(input: List<String>): List<List<List<Int>>> {
    val boards = mutableListOf(listOf(listOf<Int>()))
    var i = 0
    var j = 0
    while (i < input.size - 1) {
        val board = createBingoBoardFromString(input.subList(i, i + 5))
        boards.add(board)
        i += 6
    }
    return boards.drop(1)
}

fun createBingoBoardFromString(
    input: List<String>,
): List<MutableList<Int>> {
    val board = mutableListOf(mutableListOf<Int>())
    for (row in input) {
        val rowAsList = inputStringToListofInt(row)
        board.add(input.indexOf(row), rowAsList as MutableList<Int>)
    }
    return board.filter { it.isNotEmpty() }
}

fun inputStringToListofInt(input: String): List<Int> {
    val split = input.split(" ")
    return split.filter { it.isNotEmpty() }.map { it.toInt() }
}

class BingoBoard(
    inputValues: List<List<Int>>
) {
    var values: List<List<Pair<Int, Boolean>>> = inputValues.map { list -> list.map { return@map Pair(it, false) } }

    fun markNumber(numberToMark: Int) {
        values = values.map { list ->
            list.map {
                if (it.first == numberToMark) {
                    return@map Pair(it.first, true)
                } else return@map it
            }
        }
    }

    fun didIWin(): Boolean{
        for (row in values) {
            if (isRowFull(row)) return true
        }

        val transposed = transpose(values)
        for (row in transposed) {
            if (isRowFull(row)) return true
        }

        return false
    }

    private fun isRowFull(row: List<Pair<Int, Boolean>>): Boolean {
        var count = 0
        for (value in row) {
            if (value.second) {
                count += 1
            }
        }
        if (count == 5) {
            return true
        }
        return false
    }

    fun sumOfUnmarkedNumbers():Int{
        var sum = 0
        for (row in values){
            for (pair in row){
                if(!pair.second){
                    sum+=pair.first
                }
            }
        }
        return sum
    }

    fun display() {
        display(this.values.map { it.map { it.first } })
    }
}