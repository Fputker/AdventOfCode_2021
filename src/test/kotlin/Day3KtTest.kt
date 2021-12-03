import org.junit.jupiter.api.Test

internal class Day3KtTest {

//    @Test
//    fun `given 123 and 123 when transpose expect 11 22 33`() {
//        assert(
//            transpose(mutableListOf(mutableListOf<Int>(1, 2, 3), mutableListOf<Int>(1, 2, 3))) == mutableListOf(
//                mutableListOf<Int>(1, 1),
//                mutableListOf<Int>(2, 2),
//                mutableListOf<Int>(3, 3)
//            )
//        )
//    }

    @Test
    fun `given 123456789012 when transpose expect 1 2 3 4 5 6 7 8 9 0 1 2`() {
        assert(
            createTwelveLists(listOf("123456789012")) == mutableListOf(
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
    fun `given 001 when isBitinposition`(){

    }

}