import java.lang.Math.abs

fun determineMedianPosition(positions: List<Int>): Int {
    return positions.sorted()[(positions.size/2)]
}

fun calculateTotalDuelConsumptionForPositionComparedToReference(reference:Int, positions: List<Int>):Int{
    val total = positions.map{calculateConsumptionComparedToReference(reference, it)}.sum()
    println(total)
    return total
}

fun calculateConsumptionComparedToReference(reference: Int, position: Int):Int{
    val distance = abs(position - reference)
    val fuelConsumed = getNthTriangleNumber(distance)
    println( "difference = $distance, fuelconsumed = $fuelConsumed")
    return fuelConsumed
}

fun getNthTriangleNumber(n: Int): Int{
    return ((n*(n+1)).div(2))
}

fun main(){
    val input = readInput("Day7")[0].split(",").map { it.toInt() }
    mapTotalConsumptionToPositions(input)
}

fun mapTotalConsumptionToPositions(input: List<Int>): Int {
    val allDistances = 0..(input.sorted().last())
    println("highest number in list = ${input.sorted().last()}")
    val listOfFuelConsumptionsForAllPositions =
        allDistances.map { Pair(it, calculateTotalDuelConsumptionForPositionComparedToReference(it, input)) }
    val lowest = listOfFuelConsumptionsForAllPositions.sortedBy { it.second }
    println("position = ${lowest[0].first}, fuelconsumption = ${lowest[0].second}")
    println("The lowest fuel consumption is at position: ${lowest[0].first}")
    return lowest[0].first
}