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
        assertEquals(expect, createBingoBoardFromString(input))
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

    @Test
    fun `given a row is full when didIWin then true`() {
        val board = BingoBoard(
            listOf(
                listOf(50, 83, 3, 31, 16),
                listOf(47, 9, 94, 10, 86),
                listOf(61, 22, 53, 46, 74),
                listOf(77, 41, 79, 55, 62),
                listOf(97, 78, 43, 73, 40)
            )
        )
        board.markNumber(50)
        board.markNumber(83)
        board.markNumber(3)
        board.markNumber(31)
        board.markNumber(16)

        board.display()
        assert(board.didIWin())
    }

    @Test
    fun `given a column is full when didIWin then true`() {
        val board = BingoBoard(
            listOf(
                listOf(50, 83, 3, 31, 16),
                listOf(47, 9, 94, 10, 86),
                listOf(61, 22, 53, 46, 74),
                listOf(77, 41, 79, 55, 62),
                listOf(97, 78, 43, 73, 40)
            )
        )
        board.markNumber(16)
        board.markNumber(86)
        board.markNumber(74)
        board.markNumber(62)
        board.markNumber(40)

        board.display()
        assert(board.didIWin())
    }


    @Test
    fun `testSUmOfUnmarkedNumbers`() {
        val board = BingoBoard(
            listOf(
                listOf(14, 21, 17, 24, 4),
                listOf(10, 16, 15, 9, 19),
                listOf(18, 8, 23, 26, 20),
                listOf(22, 11, 13, 6, 5),
                listOf(2, 0, 12, 3, 7),
            )
        )
        board.markNumber(7)
        board.markNumber(4)
        board.markNumber(9)
        board.markNumber(5)
        board.markNumber(11)
        board.markNumber(17)
        board.markNumber(23)
        board.markNumber(2)
        board.markNumber(0)
        board.markNumber(14)
        board.markNumber(21)
        board.markNumber(24)

        assertEquals(188, board.sumOfUnmarkedNumbers())
    }

    @Test
    fun completeTestCase() {
        val numbersDrawn =
            "7,4,9,5,11,17,23,2,0,14,21,24,10,16,13,6,15,25,12,22,18,20,8,19,3,26,1".split(",").map { it.toInt() }
        val listOfBoards = listOf(
            listOf(
                listOf(22, 13, 17, 11, 0),
                listOf(8, 2, 23, 4, 24),
                listOf(21, 9, 14, 16, 7),
                listOf(6, 10, 3, 18, 5),
                listOf(1, 12, 20, 15, 19)
            ),
            listOf(
                listOf(3, 15, 0, 2, 22),
                listOf(9, 18, 13, 17, 5),
                listOf(19, 8, 7, 25, 23),
                listOf(20, 11, 10, 24, 4),
                listOf(1, 42, 11, 61, 2)
            ),
            listOf(
                listOf(14, 21, 17, 24, 4),
                listOf(10, 16, 15, 9, 19),
                listOf(18, 8, 23, 26, 20),
                listOf(22, 11, 13, 6, 5),
                listOf(2, 0, 12, 3, 7)
            )
        )

        val bingoboards = listOfBoards.map { BingoBoard(it) }

        for (number in numbersDrawn) {
            for (board in bingoboards) {
                board.markNumber(number)
                if (board.didIWin()) {
                    println("the final score should be: ${board.sumOfUnmarkedNumbers() * number}")
                    assertEquals(4512,(board.sumOfUnmarkedNumbers() * number))
                }
            }
        }
    }
}
