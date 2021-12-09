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
}