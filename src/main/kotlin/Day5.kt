/*
* 1 read strings from file, split strings into two sets of coordinates
* 2 filter for sets that have matching x or y
* 3 process sets of coordinates to list of coordinates covered by that set
* 4 'mark' coordinates in lists
* 5 filter for coordinates 'marked' more than twice
* */

fun main() {
    val listOfStrings = readInput("day5")
    println("the answer for part 2 = ${day5part1(listOfStrings)}")
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
            drawVerticalLine()
        } else if (coordinates[0].second.equals(coordinates[1].second)) {
            coordinates.sortBy { it.first }
            drawHorizontalLine()
        } else if (isDiagonal()) {
            coordinates.sortBy { it.first }
            drawDiagonal()
        }
    }

    fun isLine(): Boolean {
        return isHorizontalLine() || isVerticalLine() || isDiagonal()
    }

    fun isHorizontalLine():Boolean{
        return coordinates[0].second == coordinates[1].second
    }

    fun isVerticalLine():Boolean{
        return coordinates[0].first == coordinates[1].first
    }

    fun isDiagonal(): Boolean {
        return (!coordinates[0].equals(coordinates[1]) && ((coordinates[0].first + coordinates[0].second == coordinates[1].first + coordinates[1].second) || (coordinates[0].first == coordinates[0].second && coordinates[1].first == coordinates[1].second) || coordinates[0].first - coordinates[0].second == coordinates[1].first - coordinates[1].second))
    }

    fun drawVerticalLine() {
        for (i in coordinates[0].second + 1 until coordinates[1].second) {
            val newPair = Pair(coordinates[0].first, i)
            coordinates.add(newPair)
        }
    }

    fun drawHorizontalLine() {
        for (i in coordinates[0].first + 1 until coordinates[1].first) {
            val newPair = Pair(i, coordinates[0].second)
            coordinates.add(newPair)
        }
    }

    fun drawDiagonal() {
        if (coordinates[0].first.equals(coordinates[0].second) && coordinates[1].first.equals(coordinates[1].second)) {
            for (i in coordinates[0].first + 1 until coordinates[1].first) {
                val newPair = Pair(i, i)
                coordinates.add(newPair)
            }
        } else if (coordinates[0].first - coordinates[0].second == coordinates[1].first - coordinates[1].second) {
            var j = coordinates[0].second + 1
            for (i in coordinates[0].first + 1 until coordinates[1].first) {
                val newPair = Pair(i, j)
                coordinates.add(newPair)
                j += 1
            }
        } else {
            var j = coordinates[0].second - 1
            for (i in coordinates[0].first + 1 until coordinates[1].first) {
                val newPair = Pair(i, j)
                coordinates.add(newPair)
                j -= 1
            }
        }
    }
}





