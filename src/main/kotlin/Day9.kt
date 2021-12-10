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


data class Coordinate(val X: Int, val Y: Int, val height: Int, val left: Int? = null, val right: Int? = null) {

    fun determineIfUpDownLowpoint(heightMap: List<List<Int>>): Boolean {
        if (Y != 0 && (Y + 1) <= heightMap.size - 1) { // check whole lines except first and last digit
            if (height < heightMap[Y - 1][X] && height < heightMap[Y + 1][X]) {
                return true
            }
        } else if (Y == 0) {
            if (height < heightMap[Y + 1][X]) {
                return true
            }
        } else if ((Y + 1) > heightMap.size - 1) {
            if (height < heightMap[Y - 1][X]) {
                return true
            }
        }
        return false
    }

    fun leftIslarger() = left!! > height && left != 9

    fun rightIslarger() = right!! > height && right != 9

    fun topIsLarger(heightMap: List<List<Int>>) = if (Y>0) {heightMap[Y-1][X] > height && heightMap[Y-1][X] != 9} else false

    fun bottomIsLarger(heightMap: List<List<Int>>) = if (Y < heightMap.size) {heightMap[Y+1][X] > height && heightMap[Y+1][X] != 9} else false

    fun findLeftBasinCoordinates(heightMap: List<List<Int>>, coordinates: MutableList<Coordinate>): List<Coordinate> {
        val newCoordinates = coordinates
        coordinates.forEach {
            if (it.leftIslarger()) {
                val newCoordinate = it.left?.let { it1 -> Coordinate(X = it.X - 1, Y = it.Y, height = it1, left = heightMap[it.Y][it.X - 1], right = it.height) }
                if (newCoordinate != null) {
                    newCoordinates.add(newCoordinate)
                }
                if (newCoordinates.equals(coordinates)) {
                    return coordinates
                } else {
                    this.findLeftBasinCoordinates(heightMap, newCoordinates)
                }
            }
        }
        return coordinates
    }

    fun findRightBasinCoordinates(heightMap: List<List<Int>>, coordinates: MutableList<Coordinate>): List<Coordinate> {
        val newCoordinates = coordinates
        coordinates.forEach {
            if (it.rightIslarger()) {
                val newCoordinate = it.right?.let { it1 -> Coordinate(X = it.X + 1, Y = it.Y, height = it1, left = heightMap[it.Y][it.X + 1], right = it.height) }
                if (newCoordinate != null) {
                    newCoordinates.add(newCoordinate)
                }
                if (newCoordinates.equals(coordinates)) {
                    return coordinates
                } else {
                    this.findRightBasinCoordinates(heightMap, newCoordinates)
                }
            }
        }
        return coordinates
    }

    fun findTopBasinCoordinates(heightMap: List<List<Int>>, coordinates: MutableList<Coordinate>): List<Coordinate> {
        val newCoordinates = coordinates
        coordinates.forEach {
            if (it.topIsLarger(heightMap)) {
                val newCoordinate = Coordinate(X = it.X, Y = it.Y-1, height = heightMap[Y-1][X], left = heightMap[it.Y-1][it.X-1], right = heightMap[it.Y-1][it.X+1])
                newCoordinates.add(newCoordinate)
                if (newCoordinates.equals(coordinates)) {
                    return coordinates
                } else {
                    this.findTopBasinCoordinates(heightMap, newCoordinates)
                }
            }
        }
        return coordinates
    }

    fun findBottomBasinCoordinates(heightMap: List<List<Int>>, coordinates: MutableList<Coordinate>): List<Coordinate> {
        val newCoordinates = coordinates
        coordinates.forEach {
            if (it.bottomIsLarger(heightMap)) {
                val newCoordinate = Coordinate(X = it.X, Y = it.Y+1, height = heightMap[Y+1][X], left = heightMap[it.Y+1][it.X-1], right = heightMap[it.Y+1][it.X+1])
                newCoordinates.add(newCoordinate)
                if (newCoordinates.equals(coordinates)) {
                    return coordinates
                } else {
                    this.findBottomBasinCoordinates(heightMap, newCoordinates)
                }
            }
        }
        return coordinates
    }



    fun findVerticalBasinCoordinates(): List<Coordinate> {
        return listOf()
    }
}

data class Basin(var coordinates: List<Coordinate>) {
    val id = this.hashCode()

    fun mapCompleteBasin(heightMap: List<List<Int>>, coordinates: MutableList<Coordinate>) {

    }
}

fun main() {
    val input = readInput("day9")
    val lowpoints = createCoordinateListOfXYlowpoints(input)
    val lowpointHeights = lowpoints.map { it.height + 1 }
    println("part1 = ${lowpointHeights.sum()}")


}