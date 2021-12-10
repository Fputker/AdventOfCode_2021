import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class Day9KtTest {

    @Test
    fun `test find low point`() {
        val testInput = listOf(
            "2199943210",
            "3987894921",
            "9856789892",
            "8767896789",
            "9899965678",
        )
        assertEquals(4, createCoordinateListOfXYlowpoints(testInput).size)
    }

    @Test
    fun `test find low point in line`() {
        val testInput = listOf(
            "2199943210"
        )

        val newMap = testInput.map { string -> string.toCharArray().map { it.digitToInt() } }

        assertEquals(2, findLowPointsInLine(newMap, 0 ).size)
    }

    @Test
    fun `test findleftBasinCoordinates`(){
        val testInput = listOf(
            "2199943210"
        )

        val newMap = testInput.map { string -> string.toCharArray().map { it.digitToInt() } }
        val startList = mutableListOf(Coordinate(9,0,0, 1, null))

        assertEquals(5, Coordinate(1,0,1, 2, 9).findLeftBasinCoordinates(newMap, startList).size)
    }

    @Test
    fun `test findrightBasinCoordinates`(){
        val testInput = listOf(
            "0123499921"
        )
        val newMap = testInput.map { string -> string.toCharArray().map { it.digitToInt() } }
        val startList = mutableListOf(Coordinate(0,0,0, null, 1))

        assertEquals(5, startList[0].findRightBasinCoordinates(newMap, startList).size)
    }

    @Test
    fun `test findTopBasinCoordinates`(){
        val testInput = listOf(
            "2199843210",
            "3987794921",
            "9856689892",
            "8767896789",
            "9899965678"
        )

        val newMap = testInput.map { string -> string.toCharArray().map { it.digitToInt() } }
        val startList = mutableListOf(Coordinate(4,2,6, 6, 8))

        assertEquals(3, startList[0].findTopBasinCoordinates(newMap, startList).size)
    }

    @Test
    fun `test findBottomBasinCoordinates`(){
        val testInput = listOf(
            "2199943210",
            "3987894921",
            "9856789892",
            "8767896789",
            "9899965678"
        )

        val newMap = testInput.map { string -> string.toCharArray().map { it.digitToInt() } }
        val startList = mutableListOf(Coordinate(9,0,0, 1, null))

        assertEquals(3, startList[0].findBottomBasinCoordinates(newMap, startList).size)
    }

    @Test
    fun `test basin size`(){
        val testInput = listOf(
            "2199943210",
            "3987894921",
            "9856789892",
            "8767896789",
            "9899965678"
        )

        val newMap = testInput.map { string -> string.toCharArray().map { it.digitToInt() } }
        val startList = mutableListOf(Coordinate(1,0,1, 2, 9))

        assertEquals(3, mapCompleteBasin(newMap, startList).size)
    }

    @Test
    fun `test another basin size`(){
        val testInput = listOf(
            "2199943210",
            "3987894921",
            "9856789892",
            "8767896789",
            "9899965678"
        )

        val newMap = testInput.map { string -> string.toCharArray().map { it.digitToInt() } }
        val startList = mutableListOf(Coordinate(9,0,0, 1, null))
        val result = mapCompleteBasin(newMap, startList)
        assertEquals(9, result.size)
    }

}