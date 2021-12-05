import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class Day5KtTest {

    @Test
    fun `test readinputstring to line`() {
        val inputString = "35,968 -> 974,29"
        val expect = Line(listOf(Pair(35, 968), Pair(974, 29)) as MutableList<Pair<Int, Int>>)

        assertEquals(expect, inputStringToLine(inputString))
    }

    @Test
    fun `test main fun`() {

        val input = listOf(
            "0,9 -> 5,9",
            "8,0 -> 0,8",
            "9,4 -> 3,4",
            "2,2 -> 2,1",
            "7,0 -> 7,4",
            "6,4 -> 2,0",
            "0,9 -> 2,9",
            "3,4 -> 1,4",
            "0,0 -> 8,8",
            "5,5 -> 8,2"
        )
        assertEquals(5, day5part1(input))
    }
}