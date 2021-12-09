import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class Day9KtTest {

    @Test
    fun `test find low point`() {
        val testInput =
            """2199943210
                3987894921
                9856789892
                8767896789
                9899965678"""

        assertEquals(1, findLowPoint())
    }
}