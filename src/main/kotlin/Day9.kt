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

    fun leftIslarger() = if (left != null) {
        (left > height && left != 9)
    } else {
        false
    }

    fun rightIslarger() = if (right != null) {
        (right > height && right != 9)
    } else {
        false
    }

    fun topIsLarger(heightMap: List<List<Int>>) = if (Y > 0) {
        heightMap[Y - 1][X] > height && heightMap[Y - 1][X] != 9
    } else false

    fun bottomIsLarger(heightMap: List<List<Int>>) = if (Y < heightMap.size) {
        heightMap[Y + 1][X] > height && heightMap[Y + 1][X] != 9
    } else false

    fun findLeftBasinCoordinates(heightMap: List<List<Int>>, coordinates: List<Coordinate>): List<Coordinate> {
        val newCoordinates = coordinates.toMutableList()
        for (i in 0..coordinates.size - 1) {
            if (coordinates[i].leftIslarger()) {
                val newCoordinate = coordinates[i].left?.let { it1 -> Coordinate(X = coordinates[i].X - 1, Y = coordinates[i].Y, height = it1, left = heightMap[coordinates[i].Y][coordinates[i].X - 2], right = coordinates[i].height) }
                if (newCoordinate != null && !newCoordinates.contains(newCoordinate)) {
                    newCoordinates.add(newCoordinate)
                    newCoordinates.addAll(findLeftBasinCoordinates(heightMap, newCoordinates))
                }
            }
        }
        return newCoordinates.distinct()
    }

    fun findRightBasinCoordinates(heightMap: List<List<Int>>, coordinates: List<Coordinate>): List<Coordinate> {
        val newCoordinates = coordinates.toMutableList()
        for (i in 0..coordinates.size - 1) {
            if (coordinates[i].rightIslarger()) {
                val newCoordinate = coordinates[i].right?.let { it1 -> Coordinate(X = coordinates[i].X + 1, Y = coordinates[i].Y, height = it1, left = coordinates[i].height, right = heightMap[coordinates[i].Y][coordinates[i].X + 2]) }
                if (newCoordinate != null && !newCoordinates.contains(newCoordinate)) {
                    newCoordinates.add(newCoordinate)
                    newCoordinates.addAll(findRightBasinCoordinates(heightMap, newCoordinates))
                }
            }
        }
        return newCoordinates.distinct()
    }

    fun findTopBasinCoordinates(heightMap: List<List<Int>>, coordinates: MutableList<Coordinate>): List<Coordinate> {
        val newCoordinates = coordinates
        for (i in 0..coordinates.size - 1) {
            if (coordinates[i].topIsLarger(heightMap)) {
                val newCoordinate: Coordinate = if (X + 1 < heightMap[0].size - 1 && coordinates[i].X > 0) {
                    Coordinate(X = coordinates[i].X, Y = coordinates[i].Y - 1, height = heightMap[Y - 1][X], left = heightMap[coordinates[i].Y - 1][coordinates[i].X - 1], right = heightMap[coordinates[i].Y - 1][coordinates[i].X + 1])
                } else if (coordinates[i].X + 1 > heightMap[0].size - 1) {
                    Coordinate(X = coordinates[i].X, Y = coordinates[i].Y - 1, height = heightMap[Y - 1][X], left = heightMap[coordinates[i].Y - 1][coordinates[i].X - 1], right = null)
                } else {
                    Coordinate(X = coordinates[i].X, Y = coordinates[i].Y - 1, height = heightMap[Y - 1][X], left = null, right = heightMap[coordinates[i].Y - 1][coordinates[i].X + 1])
                }
                if (!newCoordinates.contains(newCoordinate)) {
                    newCoordinates.add(newCoordinate)
                    newCoordinates.addAll(findTopBasinCoordinates(heightMap, newCoordinates))
                }
            }
        }
        return coordinates.distinct()
    }

    fun findBottomBasinCoordinates(heightMap: List<List<Int>>, coordinates: MutableList<Coordinate>): List<Coordinate> {
        val newCoordinates = coordinates
        for (i in 0..coordinates.size - 1) {
            if (coordinates[i].bottomIsLarger(heightMap)) {
                val newCoordinate: Coordinate = if (X + 1 < heightMap[0].size - 1 && coordinates[i].X > 0) {
                    Coordinate(X = coordinates[i].X, Y = coordinates[i].Y + 1, height = heightMap[Y + 1][X], left = heightMap[coordinates[i].Y + 1][coordinates[i].X - 1], right = heightMap[coordinates[i].Y + 1][coordinates[i].X + 1])
                } else if (coordinates[i].X + 1 > heightMap[0].size - 1) {
                    Coordinate(X = coordinates[i].X, Y = coordinates[i].Y + 1, height = heightMap[Y + 1][X], left = heightMap[coordinates[i].Y + 1][coordinates[i].X - 1], right = null)
                } else {
                    Coordinate(X = coordinates[i].X, Y = coordinates[i].Y + 1, height = heightMap[Y + 1][X], left = null, right = heightMap[coordinates[i].Y + 1][coordinates[i].X + 1])
                }
                if (!newCoordinates.contains(newCoordinate)) {
                    newCoordinates.add(newCoordinate)
                    newCoordinates.addAll(findBottomBasinCoordinates(heightMap, newCoordinates))
                }
            }
        }
        return coordinates.distinct()
    }

    fun mapCompleteBasin(heightMap: List<List<Int>>, coordinates: List<Coordinate>): List<Coordinate> {
        val newCoordinates = coordinates.toMutableList()
        coordinates.forEach {
            it.findLeftBasinCoordinates(heightMap, newCoordinates)
            it.findRightBasinCoordinates(heightMap, newCoordinates)
            it.findBottomBasinCoordinates(heightMap, newCoordinates)
            it.findTopBasinCoordinates(heightMap, newCoordinates)
            newCoordinates.addAll(mapCompleteBasin(heightMap, newCoordinates))
        }
        return newCoordinates.distinct()
    }
}

fun main() {
    val input = readInput("day9")
    val lowpoints = createCoordinateListOfXYlowpoints(input)
    val lowpointHeights = lowpoints.map { it.height + 1 }
    println("part1 = ${lowpointHeights.sum()}")


}