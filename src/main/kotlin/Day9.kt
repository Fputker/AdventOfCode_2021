
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

    fun bottomIsLarger(heightMap: List<List<Int>>) = if (Y < heightMap.size - 1) {
        heightMap[Y + 1][X] > height && heightMap[Y + 1][X] != 9
    } else false

    fun findLeftBasinCoordinates(heightMap: List<List<Int>>, coordinates: List<Coordinate>): List<Coordinate> {
        val newCoordinates = coordinates.toMutableList()
        for (i in 0..coordinates.size - 1) {
            val xy = coordinates[i]
            if (xy.leftIslarger()) {
                val newCoordinate: Coordinate? = if (xy.X > heightMap[0].size -1 ) {
                    xy.left?.let { it1 -> Coordinate(X = xy.X - 1, Y = xy.Y, height = it1, left = heightMap[xy.Y][xy.X - 2], right = null) }
                } else if (xy.X - 2 < 0) {
                    xy.left?.let { it1 -> Coordinate(X = xy.X - 1, Y = xy.Y, height = it1, left = null, right = xy.height) }
                } else {
                    xy.left?.let { it1 -> Coordinate(X = xy.X - 1, Y = xy.Y, height = it1, left = heightMap[xy.Y][xy.X - 2], right = xy.height) }
                }
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
            val xy = coordinates[i]
            if (xy.rightIslarger()) {
                val newCoordinate: Coordinate? = if (xy.X + 2 > heightMap[0].size - 1) {
                    xy.right?.let { it1 -> Coordinate(X = xy.X + 1, Y = xy.Y, height = it1, left = xy.height, right = null) }
                } else if (xy.X -1 < 0) {
                    xy.right?.let { it1 -> Coordinate(X = xy.X + 1, Y = xy.Y, height = it1, left = xy.height, right = heightMap[xy.Y][xy.X + 2]) }
                } else {
                    xy.right?.let { it1 -> Coordinate(X = xy.X + 1, Y = xy.Y, height = it1, left = xy.height, right = heightMap[xy.Y][xy.X + 2]) }
                }
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
            val xy = coordinates[i]
            if (xy.topIsLarger(heightMap)) {
                val newCoordinate: Coordinate = if (xy.X + 1 < heightMap[0].size && xy.X > 0 && xy.Y - 1 >= 0) {
                    Coordinate(X = xy.X, Y = xy.Y - 1, height = heightMap[xy.Y - 1][xy.X], left = heightMap[xy.Y - 1][xy.X - 1], right = heightMap[xy.Y - 1][xy.X + 1])
                } else if (xy.X + 1 > heightMap[0].size - 1) {
                    Coordinate(X = xy.X, Y = xy.Y - 1, height = heightMap[xy.Y - 1][xy.X], left = heightMap[xy.Y - 1][xy.X - 1], right = null)
                } else if (X - 1 == -1) {
                    Coordinate(X = xy.X, Y = xy.Y - 1, height = heightMap[xy.Y - 1][xy.X], left = null, right = heightMap[xy.Y - 1][xy.X + 1])
                } else {
                    break
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
            val xy = coordinates[i]
            if (xy.bottomIsLarger(heightMap)) {
                val newCoordinate: Coordinate = if ((xy.X + 1 < heightMap[0].size) && xy.X > 0 && xy.Y + 1 < heightMap.size) {
                    Coordinate(X = xy.X, Y = xy.Y + 1, height = heightMap[xy.Y + 1][xy.X], left = heightMap[xy.Y + 1][xy.X - 1], right = heightMap[xy.Y + 1][xy.X + 1])
                } else if (xy.X + 1 > heightMap[0].size -1 ) {
                    Coordinate(X = xy.X, Y = xy.Y + 1, height = heightMap[xy.Y + 1][xy.X], left = heightMap[xy.Y + 1][xy.X - 1], right = null)
                } else if (xy.X - 1 < 0) {
                    Coordinate(X = xy.X, Y = xy.Y + 1, height = heightMap[xy.Y + 1][xy.X], left = null, right = heightMap[xy.Y + 1][xy.X + 1])
                } else {
                    break
                }
                if (!newCoordinates.contains(newCoordinate)) {
                    newCoordinates.add(newCoordinate)
                    newCoordinates.addAll(findBottomBasinCoordinates(heightMap, newCoordinates))
                }
            }
        }
        return coordinates.distinct()
    }
}

fun mapCompleteBasin(heightMap: List<List<Int>>, coordinates: List<Coordinate>): List<Coordinate> {
    var newCoordinates = coordinates.toMutableList()
    for (i in 0..coordinates.size - 1) {
        val xy = coordinates[i]

        newCoordinates += xy.findLeftBasinCoordinates(heightMap, newCoordinates).distinct()
        newCoordinates += xy.findRightBasinCoordinates(heightMap, newCoordinates).distinct()
        newCoordinates += xy.findBottomBasinCoordinates(heightMap, newCoordinates).distinct()
        newCoordinates += xy.findTopBasinCoordinates(heightMap, newCoordinates).distinct()

        newCoordinates = newCoordinates.distinctBy { it }.toMutableList()

        if(newCoordinates.size != coordinates.size){
            newCoordinates += mapCompleteBasin(heightMap, newCoordinates)
        }
    }
    return newCoordinates.distinctBy { it}
}

fun main() {
    val input = readInput("day9")
    val lowpoints = createCoordinateListOfXYlowpoints(input)
    var lowpointHeights = lowpoints.map { it.height + 1 }
    println("part1 = ${lowpointHeights.sum()}")

    val newInput = input.map { string -> string.toCharArray().map { it.digitToInt() } }

    val result = mutableListOf<List<Coordinate>>()
    for (i in 0..lowpoints.size -1) {
        result.add(mapCompleteBasin(newInput, listOf(lowpoints[i])))
        println(i)
    }
    val ordered = result.sortedBy { it.size }

    val basin1 = ordered[result.size-1]
    val basin2 = ordered[result.size-2]
    val basin3 = ordered[result.size-3]
    println("answer to part 2 = ${basin1.size * basin2.size * basin3.size}")
}