import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class Day4KtTest {

    @Test
    fun `test read row to list of int`() {
        val result = inputStringToListofInt("50 83  3 31 16")
        val expect = listOf<Int>(50, 83, 3, 31, 16)
        assertEquals(expect, result)
    }

    @Test
    fun `createBingoBoardFromInput`() {
        val input = listOf(
            "50 83  3 31 16",
            "47  9 94 10 86",
            "61 22 53 46 74",
            "77 41 79 55 62",
            "97 78 43 73 40"
        )
        val expect = listOf(
            listOf(50, 83, 3, 31, 16),
            listOf(47, 9, 94, 10, 86),
            listOf(61, 22, 53, 46, 74),
            listOf(77, 41, 79, 55, 62),
            listOf(97, 78, 43, 73, 40)
        )
        assertEquals(expect, createBingoBoard(input))
    }

    @Test
    fun `readmultipleBoardsFromInput`() {
        val input = listOf(
            "50 83  3 31 16",
            "47  9 94 10 86",
            "61 22 53 46 74",
            "77 41 79 55 62",
            "97 78 43 73 40",
            "              ",
            "99 96 20 35 21",
            "38 17 48 69 68",
            "9 51 32 52 11",
            "67  8 42 89 27",
            "39 62 66 72 43"
        )
        val expect = listOf(
            listOf(
                listOf(50, 83, 3, 31, 16),
                listOf(47, 9, 94, 10, 86),
                listOf(61, 22, 53, 46, 74),
                listOf(77, 41, 79, 55, 62),
                listOf(97, 78, 43, 73, 40)
            ),
            listOf(
                listOf(99, 96, 20, 35, 21),
                listOf(38, 17, 48, 69, 68),
                listOf(9, 51, 32, 52, 11),
                listOf(67, 8, 42, 89, 27),
                listOf(39, 62, 66, 72, 43)
            )
        )
        assertEquals(expect, readBoardsFromInput(input))
    }

}