import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

internal class Day6KtTest {

    @Test
    fun `test initialization`() {
        val input = "3,4,3,1,2"

        val expect = mutableListOf(0L, 1L, 1L, 2L, 1L, 0L, 0L, 0L, 0L)

        assertEquals(expect, createNewFishList(input))
    }

    @Test
    fun `test simple aging again`() {
        val input = mutableListOf(0L, 1L, 1L, 2L, 1L, 0L, 0L, 0L, 0L)
        val expect = mutableListOf(1L, 1L, 2L, 1L, 0L, 0L, 0L, 0L, 0L)
        assertEquals(expect, ageAday(input))
    }

    @Test
    fun `test simple aging again and again`() {
        val input = mutableListOf(1, 1, 2, 1, 0, 0, 0, 0, 0).map { it.toLong() } as MutableList<Long>
        val expect = mutableListOf(1, 2, 1, 0, 0, 0, 1, 0, 1).map { it.toLong() } as MutableList<Long>
        assertEquals(expect, ageAday(input))
    }

    @Test
    fun `test complete input for 80 days`() {
        val input = mutableListOf(0, 1, 1, 2, 1, 0, 0, 0, 0).map { it.toLong() } as MutableList<Long>

        assertEquals(5934, ageXamountOfDays(80, input).sum())
    }

    @Test
    fun `test complete input for 256 days`() {
        val input = mutableListOf(0, 1, 1, 2, 1, 0, 0, 0, 0).map { it.toLong() } as MutableList<Long>
        val expected: Long = 26984457539
        assertEquals(expected, ageXamountOfDays(256, input).sum())
    }

}