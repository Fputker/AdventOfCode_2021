import java.io.File

fun readInput(fileName: String) = File("src/main/kotlin/input/", "$fileName.txt").readLines()

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