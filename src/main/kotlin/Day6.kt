fun createFishList(input: String): MutableList<Int> {
    return input.split(",").map { it.toInt() } as MutableList<Int>
}

fun day6part1(){
    val startingFish = createFishList(readInput("Day6")[0])
    println(ageXamountOfDays(80, startingFish).size)
}

fun ageXamountOfDays(nrOfDays: Int, startingFish: MutableList<Int>): List<Int>{
    var copy: MutableList<Int> = startingFish
    for (day in 1..nrOfDays){
        copy = ageOneDay(copy)
        println(copy )
    }
    return copy
}

fun ageOneDay(listOfFish: MutableList<Int>): MutableList<Int>{
    val aged = listOfFish.map { it - 1 } as MutableList<Int>
    return processAgedFish(aged)
}

fun processAgedFish(listOfFish : MutableList<Int>): MutableList<Int> {
    var newlistOfFish = listOfFish
    for (i in 1..listOfFish.count { it == -1 }){
        newlistOfFish.add(8)
    }
    newlistOfFish = newlistOfFish.map { if(it==-1) 6 else it } as MutableList<Int>
    return newlistOfFish
}

fun main() {
    day6part1()
}