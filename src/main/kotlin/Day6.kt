fun createNewFishList(input: String): List<Long> {
    val inputInts = input.split(",").map { it.toLong() }
    val fishlist = listOf(
        inputInts.count { it == 0L }.toLong(),
        inputInts.count { it == 1L }.toLong(),
        inputInts.count { it == 2L }.toLong(),
        inputInts.count { it == 3L }.toLong(),
        inputInts.count { it == 4L }.toLong(),
        inputInts.count { it == 5L }.toLong(),
        inputInts.count { it == 6L }.toLong(),
        inputInts.count { it == 7L }.toLong(),
        inputInts.count { it == 8L }.toLong())
    return fishlist
}

fun ageXamountOfDays(nrOfDays: Int, startingFish: MutableList<Long>): List<Long> {
    var copy: MutableList<Long> = startingFish
    for (day in 1..nrOfDays) {
        copy = ageAday(copy) as MutableList<Long>
    }
    return copy
}

fun ageAday(fishList: MutableList<Long>): List<Long> {
    val fishAt0 = fishList[0]
    for (i in 0..fishList.size-2) {
        fishList[i] = fishList[i+1]
    }
    fishList[6] = fishList[6] +fishAt0
    fishList[8] = fishAt0
    return fishList
}

fun main() {
    val startingFish1 = createNewFishList(readInput("Day6")[0])
    println("answer to part 1 = ${ageXamountOfDays(80, startingFish1 as MutableList<Long>).sum()}")
    val startingFish2 = createNewFishList(readInput("Day6")[0])
    println("answer to part 2 = ${ageXamountOfDays(256, startingFish2 as MutableList<Long>).sum()}")

}