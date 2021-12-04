import org.junit.jupiter.api.Test
import kotlin.test.assertFalse

internal class Day3KtTest {

    @Test
    fun `given 123 and 123 when transpose expect 11 22 33`() {
        assert(
            transpose(listOf(listOf(1, 2, 3), listOf(1, 2, 3))) == listOf(
                listOf(1, 1),
                listOf(2, 2),
                listOf(3, 3)
            )
        )
    }

    @Test
    fun `given 123456789012 when transpose expect 1 2 3 4 5 6 7 8 9 0 1 2`() {
        assert(
            transpose(listOf(listOf("123456789012"))) == listOf(
                listOf(1),
                listOf(2),
                listOf(3),
                listOf(4),
                listOf(5),
                listOf(6),
                listOf(7),
                listOf(8),
                listOf(9),
                listOf(0),
                listOf(1),
                listOf(2)
            )
        )
    }

    @Test
    fun `given 001 when isBitinposition(bit 0, position 0) then true`() {
        assert(isBitInPosition(listOf(0, 0, 1), 0, 0))
    }

    @Test
    fun `given 001 when isBitinposition(bit 0, position 2) then true`() {
        assertFalse { isBitInPosition(listOf(0, 0, 1), 0, 2) }
    }

    @Test
    fun `determineMostCommonBits`() {
        val input = listOf(
            "00100".toCharArray().map { it.digitToInt() },
            "11110".toCharArray().map { it.digitToInt() },
            "10110".toCharArray().map { it.digitToInt() },
            "10111".toCharArray().map { it.digitToInt() },
            "10101".toCharArray().map { it.digitToInt() },
            "01111".toCharArray().map { it.digitToInt() },
            "00111".toCharArray().map { it.digitToInt() },
            "11100".toCharArray().map { it.digitToInt() },
            "10000".toCharArray().map { it.digitToInt() },
            "11001".toCharArray().map { it.digitToInt() },
            "00010".toCharArray().map { it.digitToInt() },
            "01010".toCharArray().map { it.digitToInt() }
        )
        assert(determineMostCommonBits(input) == listOf(1, 0, 1, 1, 0))
    }

    @Test
    fun `determineLeastCommonBits`() {
        val input = listOf(
            "00100".toCharArray().map { it.digitToInt() },
            "11110".toCharArray().map { it.digitToInt() },
            "11110".toCharArray().map { it.digitToInt() },
            "10101".toCharArray().map { it.digitToInt() },
        )
        println(determineLeastCommonBits(input))
        assert(determineLeastCommonBits(input) == listOf(0, 0, 0, 0, 1))
    }

    @Test
    fun `determinefinalOutput`() {
        val input = listOf(
            "00100".toCharArray().map { it.digitToInt() },
            "11110".toCharArray().map { it.digitToInt() },
            "10110".toCharArray().map { it.digitToInt() },
            "10111".toCharArray().map { it.digitToInt() },
            "10101".toCharArray().map { it.digitToInt() },
            "01111".toCharArray().map { it.digitToInt() },
            "00111".toCharArray().map { it.digitToInt() },
            "11100".toCharArray().map { it.digitToInt() },
            "10000".toCharArray().map { it.digitToInt() },
            "11001".toCharArray().map { it.digitToInt() },
            "00010".toCharArray().map { it.digitToInt() },
            "01010".toCharArray().map { it.digitToInt() }
        )
        assert(part2(input) == 230)
    }

    @Test
    fun `test second iteration`() {
        val input = listOf(
            "11110".toCharArray().map { it.digitToInt() },
            "10110".toCharArray().map { it.digitToInt() },
            "10111".toCharArray().map { it.digitToInt() },
            "10101".toCharArray().map { it.digitToInt() },
            "11100".toCharArray().map { it.digitToInt() },
            "10000".toCharArray().map { it.digitToInt() },
            "11001".toCharArray().map { it.digitToInt() }
        )
        assert(determineMostCommonBits(input) == listOf(1,0,1,0,0))
    }
}