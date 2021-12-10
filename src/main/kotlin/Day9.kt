import java.util.logging.Logger
import kotlin.time.Duration.Companion.days
import kotlin.time.Duration.Companion.hours

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
                val newCoordinate: Coordinate? = if (coordinates[i].X > heightMap[0].size -1 ) {
                    coordinates[i].left?.let { it1 -> Coordinate(X = coordinates[i].X - 1, Y = coordinates[i].Y, height = it1, left = heightMap[coordinates[i].Y][coordinates[i].X - 2], right = null) }
                } else if (coordinates[i].X - 1 < 0) {
                    coordinates[i].left?.let { it1 -> Coordinate(X = coordinates[i].X - 1, Y = coordinates[i].Y, height = it1, left = null, right = coordinates[i].height) }
                } else {
                    coordinates[i].left?.let { it1 -> Coordinate(X = coordinates[i].X - 1, Y = coordinates[i].Y, height = it1, left = heightMap[coordinates[i].Y][coordinates[i].X - 2], right = coordinates[i].height) }
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
            if (coordinates[i].rightIslarger()) {
                val newCoordinate: Coordinate? = if (coordinates[i].X + 1 < heightMap[0].size - 1 && coordinates[i].X > 0) {
                    coordinates[i].right?.let { it1 -> Coordinate(X = coordinates[i].X + 1, Y = coordinates[i].Y, height = it1, left = coordinates[i].height, right = heightMap[coordinates[i].Y][coordinates[i].X + 2]) }
                } else if (coordinates[i].X + 1 > heightMap[0].size - 1) {
                    coordinates[i].right?.let { it1 -> Coordinate(X = coordinates[i].X + 1, Y = coordinates[i].Y, height = it1, left = coordinates[i].height, right = null) }
                } else {
                    coordinates[i].right?.let { it1 -> Coordinate(X = coordinates[i].X + 1, Y = coordinates[i].Y, height = it1, left = null, right = heightMap[coordinates[i].Y][coordinates[i].X + 2]) }
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
            if (coordinates[i].topIsLarger(heightMap)) {
                val newCoordinate: Coordinate = if (coordinates[i].X + 1 < heightMap[0].size && coordinates[i].X > 0 && coordinates[i].Y - 1 >= 0 && coordinates[i].Y + 1 < heightMap.size) {
                    Coordinate(X = coordinates[i].X, Y = coordinates[i].Y - 1, height = heightMap[coordinates[i].Y - 1][X], left = heightMap[coordinates[i].Y - 1][coordinates[i].X - 1], right = heightMap[coordinates[i].Y - 1][coordinates[i].X + 1])
                } else if (coordinates[i].X + 1 > heightMap[0].size - 1) {
                    Coordinate(X = coordinates[i].X, Y = coordinates[i].Y - 1, height = heightMap[coordinates[i].Y - 1][X], left = heightMap[coordinates[i].Y - 1][coordinates[i].X - 1], right = null)
                } else if (X - 1 == -1) {
                    Coordinate(X = coordinates[i].X, Y = coordinates[i].Y - 1, height = heightMap[coordinates[i].Y - 1][X], left = null, right = heightMap[coordinates[i].Y - 1][coordinates[i].X + 1])
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
            if (coordinates[i].bottomIsLarger(heightMap)) {
                val newCoordinate: Coordinate = if ((coordinates[i].X + 1 < heightMap[0].size) && coordinates[i].X > 0 && coordinates[i].Y + 1 < heightMap.size) {
                    Coordinate(X = coordinates[i].X, Y = coordinates[i].Y + 1, height = heightMap[coordinates[i].Y + 1][coordinates[i].X], left = heightMap[coordinates[i].Y + 1][coordinates[i].X - 1], right = heightMap[coordinates[i].Y + 1][coordinates[i].X + 1])
                } else if (coordinates[i].X + 1 > heightMap[0].size -1 ) {
                    Coordinate(X = coordinates[i].X, Y = coordinates[i].Y + 1, height = heightMap[coordinates[i].Y + 1][coordinates[i].X], left = heightMap[coordinates[i].Y + 1][coordinates[i].X - 1], right = null)
                } else if (coordinates[i].X - 1 < 0) {
                    Coordinate(X = coordinates[i].X, Y = coordinates[i].Y + 1, height = heightMap[coordinates[i].Y + 1][coordinates[i].X], left = null, right = heightMap[coordinates[i].Y + 1][coordinates[i].X + 1])
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
    val newCoordinates = coordinates.toMutableList()
    var intermediateCoordinates: List<Coordinate>
    for (i in 0..coordinates.size - 1) {
        intermediateCoordinates = coordinates[i].findLeftBasinCoordinates(heightMap, newCoordinates).distinct()
        newCoordinates.addAll(intermediateCoordinates.filter { !newCoordinates.contains(it) })

        intermediateCoordinates = coordinates[i].findRightBasinCoordinates(heightMap, newCoordinates).distinct()
        newCoordinates.addAll(intermediateCoordinates.filter { !newCoordinates.contains(it) })

        intermediateCoordinates = coordinates[i].findBottomBasinCoordinates(heightMap, newCoordinates).distinct()
        newCoordinates.addAll(intermediateCoordinates.filter { !newCoordinates.contains(it) })

        intermediateCoordinates = coordinates[i].findTopBasinCoordinates(heightMap, newCoordinates).distinct()
        newCoordinates.addAll(intermediateCoordinates.filter { !newCoordinates.contains(it) })

        intermediateCoordinates = mapCompleteBasin(heightMap, newCoordinates.filter { !coordinates.contains(it) })

        newCoordinates.addAll(intermediateCoordinates.filter { !newCoordinates.contains(it) })
    }
    return newCoordinates.distinct()
}

fun main() {
    val input = readInput("day9")
    val lowpoints = createCoordinateListOfXYlowpoints(input)
    val lowpointHeights = lowpoints.map { it.height + 1 }
    println("part1 = ${lowpointHeights.sum()}")

    val newInput = input.map { string -> string.toCharArray().map { it.digitToInt() } }
    println()
    val basins = lowpoints.map{ mapCompleteBasin(newInput, listOf(it)) }.sortedBy { it.size }
    val basin1 = basins[basins.size-1]
    val basin2 = basins[basins.size-2]
    val basin3 = basins[basins.size-3]
    println("answer to part 2 = ${basin1.size * basin2.size * basin3.size}")
}