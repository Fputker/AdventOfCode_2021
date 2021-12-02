import java.io.File

fun readInput(fileName: String) = File("src/main/kotlin/input/", "$fileName.txt").readLines()
