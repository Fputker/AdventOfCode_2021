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
            transpose(listOf(listOf("123456789012"))) == mutableListOf(
                mutableListOf(1),
                mutableListOf(2),
                mutableListOf(3),
                mutableListOf(4),
                mutableListOf(5),
                mutableListOf(6),
                mutableListOf(7),
                mutableListOf(8),
                mutableListOf(9),
                mutableListOf(0),
                mutableListOf(1),
                mutableListOf(2)
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
    fun `determineMostCommonBits`(){
        val input = listOf<String>(
            "00100",
            "11110",
            "10110",
            "10111",
            "10101",
            "01111",
            "00111",
            "11100",
            "10000",
            "11001",
            "00010",
            "01010"
        )


        assert(determineMostCommonBits(input) == listOf(1,0,1,1,0))
    }

//    @Test
//    fun `test part2 get oxygen`() {
//        val input = listOf<String>(
//            "00100",
//            "11110",
//            "10110",
//            "10111",
//            "10101",
//            "01111",
//            "00111",
//            "11100",
//            "10000",
//            "11001",
//            "00010",
//            "01010"
//        )
//
//        assert (determineMostCommonBits(input) == listOf(1,0,1,1,1))
//
//    }

}