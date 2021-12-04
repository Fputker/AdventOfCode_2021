/*
Step 1 read file to boards
    1a read strings to list of Integers
Step 2 determine when board wins (remove matching ints and check for empty rows?
step 3 find first winning board
step 4 calculate score of board
*/
fun readBoardsFromInput(input: List<String>): List<List<List<Int>>> {
    val boards = mutableListOf(listOf(listOf<Int>()))
    var i=0
    var j=0
    while (i < input.size - 1) {
       val board = createBingoBoard(input.subList(i,i+5))
        boards.add(board)
        i += 6
    }
    return boards.drop(1)
}

fun createBingoBoard(
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
