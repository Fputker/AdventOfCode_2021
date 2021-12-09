fun findLowPoint() {

}

fun createCoordinateWithAdjacentHeights(heightMap: List<String>) {
    val newMap = heightMap.map{ string -> string.toCharArray().map { it.digitToInt() }}
    for (i in newMap.indices) {
        for (j in newMap[i].indices){
            if (newMap[i][j] < newMap[i][j-1] && newMap[i][j] < newMap[i][j+1]){
                Coordinate( j,i, newMap[i][j])
            }
        }
    }
}

data class Coordinate(val X: Int, val Y: Int, val height: Int){
    init {
        val up: Int = 0
        val down: Int = 0
        val left: Int = 0
        val right: Int = 0
    }
}