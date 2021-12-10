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
    fun `test findHorizontalBasinCoordinates`(){
        val testInput = listOf(
            "2199943210"
        )

        val newMap = testInput.map { string -> string.toCharArray().map { it.digitToInt() } }
        val startList = mutableListOf(Coordinate(1,0,1, 2, 9))

        assertEquals(2, Coordinate(1,0,1, 2, 9).findLeftBasinCoordinates(newMap, startList).size)
    }

    @Test
    fun `test findrightBasinCoordinates`(){
        val testInput = listOf(
            "2199943210"
        )

        val newMap = testInput.map { string -> string.toCharArray().map { it.digitToInt() } }
        val startList = mutableListOf(Coordinate(1,0,1, 2, 9))

        assertEquals(1, Coordinate(1,0,1, 2, 9).findRightBasinCoordinates(newMap, startList).size)
    }

    @Test
    fun `test findrTopBasinCoordinates`(){
        val testInput = listOf(
            "2199943210",
            "3987894921",
            "9856789892",
            "8767896789",
            "9899965678"
        )

        val newMap = testInput.map { string -> string.toCharArray().map { it.digitToInt() } }
        val startList = mutableListOf(Coordinate(1,0,1, 2, 9))

        assertEquals(1, Coordinate(1,0,1, 2, 9).findRightBasinCoordinates(newMap, startList).size)
    }

}