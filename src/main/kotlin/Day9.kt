

fun createCoordinateListOfXYlowpoints(heightMap: List<String>): List<Coordinate> {
    val newMap = heightMap.map { string -> string.toCharArray().map { it.digitToInt() } }
    var coordinates = mutableListOf<Coordinate>()
    for (i in newMap.indices) {
        coordinates.addAll(findLowPointsInLine(newMap, i))
    }
    val lowPoints = coordinates.filter { it.determineIfUpDownLowpoint(newMap) }
    return lowPoints
}

fun findLowPointsInLine(newMap: List<List<Int>>, i: Int): MutableList<Coordinate> {
    val coordinates = mutableListOf<Coordinate>()
    for (j in newMap[i].indices) {
        if (j != 0 && (j + 1) <= newMap[i].size - 1) { // check whole lines except first and last digit
            if (newMap[i][j] < newMap[i][j - 1] && newMap[i][j] < newMap[i][j + 1]) {
                coordinates.add(Coordinate(j, i, newMap[i][j], left = newMap[i][j - 1], right = newMap[i][j + 1]))
            }
        } else if (j == 0) {
            if (newMap[i][j] < newMap[i][j + 1]) {
                coordinates.add(Coordinate(j, i, newMap[i][j], right = newMap[i][j + 1]))
            }
        } else if ((j + 1) > newMap[i].size - 1) {
            if (newMap[i][j] < newMap[i][j - 1]) {
                coordinates.add(Coordinate(j, i, newMap[i][j], left = newMap[i][j - 1]))
            }
        }
    }
    return coordinates
}



data class Coordinate(val X: Int, val Y: Int, val height: Int, val left: Int? = null, val right: Int? = null, ) {
    fun determineIfUpDownLowpoint(heightMap: List<List<Int>>): Boolean{
        if (Y != 0 && (Y + 1) <= heightMap.size - 1) { // check whole lines except first and last digit
            if (height < heightMap[Y-1][X]  && height < heightMap[Y+1][X]) {
                return true
            }
        } else if (Y == 0) {
            if (height < heightMap[Y+1][X]) {
                return true
            }
        } else if ((Y + 1) > heightMap.size - 1) {
            if (height < heightMap[Y-1][X]) {
                return true
            }
        }
        return false
    }
}

fun main() {
    val input = readInput("day9")
    val lowpoints = createCoordinateListOfXYlowpoints(input)
    val lowpointHeights = lowpoints.map{it.height + 1}
    println("part1 = ${lowpointHeights.sum()}")


}