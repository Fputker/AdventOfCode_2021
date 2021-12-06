import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

internal class Day6KtTest {

    @Test
    fun `test simple aging`() {
        val input = mutableListOf(3, 4, 3, 1, 2)
        val expect = mutableListOf(2, 3, 2, 0, 1)
        assertEquals(expect, ageOneDay(input))
    }

    @Test
    fun `test spawning fish`() {
        val input = mutableListOf(2, 3, 2, 0, 1)
        val expect = mutableListOf(1, 2, 1, 6, 0, 8)
        assertEquals(expect, ageOneDay(input))
    }

    @Test
    fun `test complete input`() {
        val input = mutableListOf(3, 4, 3, 1, 2)

        val expect = mutableListOf(6, 0, 6, 4, 5, 6, 0, 1, 1, 2, 6, 0, 1, 1, 1, 2, 2, 3, 3, 4, 6, 7, 8, 8, 8, 8)
        assertEquals(ageXamountOfDays(18, input), expect)
    }

    @Test
    fun `test complete input for 80 days`() {
        val input = mutableListOf(3, 4, 3, 1, 2)

        assertEquals(ageXamountOfDays(80, input).size, 5934)
    }

    @Test
    fun `test complete input for 256 days`() {
        val input = mutableListOf(3, 4, 3, 1, 2)

        assertEquals(ageXamountOfDays(80, input).size, 5934)
    }

    @Test
    fun `test initialization`() {
        val input = "3,4,3,1,2"

        val expect = listOf(0, 1, 1, 2, 1, 0, 0, 0, 0)

        assertEquals(expect, createNewFishList(input))
    }

    @Test
    fun `test simple aging again`() {
        val input = mutableListOf(0, 1, 1, 2, 1,0,0,0,0)
        val expect = mutableListOf(1, 1, 2, 1, 0,0,0,0,0)
        assertEquals(expect, ageAday(input))
    }

    @Test
    fun `test simple aging again and again`() {
        val input = mutableListOf(1, 1, 2, 1, 0,0,0,0,0)
        val expect = mutableListOf(1, 2, 1, 0, 0,0,0,0,1)
        assertEquals(expect, ageAday(input))
    }

}