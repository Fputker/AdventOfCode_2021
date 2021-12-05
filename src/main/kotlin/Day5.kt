import java.util.Collections.sort

/*
* 1 read strings from file, split strings into two sets of coordinates
* 2 filter for sets that have matching x or y
* 3 process sets of coordinates to list of coordinates covered by that set
* 4 'mark' coordinates in lists
* 5 filter for coordinates 'marked' more than twice
* */

fun main() {
    val listOfStrings = readInput("day5")
    println(day5part1(listOfStrings))
}

fun day5part1(input: List<String>): Int {

    val listOfLines = input.map { inputStringToLine(it) }.filter { it.isLine() }
    val allLinesCombined = listOfLines.flatMap { it.coordinates }
    println(allLinesCombined)
    return allLinesCombined.groupingBy { it }.eachCount().filter { it.value > 1 }.size
}

fun inputStringToLine(input: String): Line {
    val coordinateStrings = input.split(" -> ")
    val coordinate1 = coordinateStrings[0].split(",")
    val coordinate2 = coordinateStrings[1].split(",")

    return Line(
        mutableListOf(
            Pair(coordinate1[0].toInt(), coordinate1[1].toInt()),
            Pair(coordinate2[0].toInt(), coordinate2[1].toInt())
        )
    )
}

data class Line(var coordinates: MutableList<Pair<Int, Int>>) {

    init {
        if (coordinates[0].first.equals(coordinates[1].first)) {
            coordinates.sortBy { it.second }
            drawHorizontalLine()
        } else if (coordinates[0].second.equals(coordinates[1].second)) {
            coordinates.sortBy { it.first }
            drawVerticalLine()
        } else if (coordinates[0].first + coordinates[0].second == coordinates[1].first + coordinates[1].second) {
            coordinates.sortBy { it.first }
        }
    }

    fun isLine(): Boolean {
        return coordinates.size > 2
    }

    fun isDiagonal(): Boolean {
        return ((coordinates[0].equals(coordinates[1]) == false) && ((coordinates[0].first + coordinates[0].second == coordinates[1].first + coordinates[1].second) || (coordinates[0].first == coordinates[0].second && coordinates[1].first == coordinates[1].second)))
    }

    fun drawHorizontalLine() {
        for (i in coordinates[0].second + 1 until coordinates[1].second) {
            val newPair = Pair(coordinates[0].first, i)
            coordinates.add(newPair)
        }
    }

    fun drawVerticalLine() {
        for (i in coordinates[0].first + 1 until coordinates[1].first) {
            val newPair = Pair(i, coordinates[0].second)
            coordinates.add(newPair)
        }
    }

    fun drawDiagonal() {
        for (i in coordinates[0].first + 1 until coordinates[1].first) {
            for (j in coordinates[0].second + 1 until coordinates[1].second) {

            }
        }

    }
}



