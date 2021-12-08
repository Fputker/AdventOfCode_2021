import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class Day8KtTest {

    @Test
    fun testCounting() {
        val input = listOf("""be cfbegad cbdgef fgaecd cgeb fdcge agebfd fecdb fabcd edb |
        fdgacbe cefdb cefbgd gcbe""",
            """ edbfga begcd cbg gc gcadebf fbgde acbgfd abcde gfcbed gfec |
        fcgedb cgb dgebacf gc""",
            """ fgaebd cg bdaec gdafb agbcfd gdcbef bgcad gfac gcb cdgabef |
        cg cg fdcagb cbg""",
            """ fbegcd cbd adcefb dageb afcb bc aefdc ecdab fgdeca fcdbega |
        efabcd cedba gadfec cb""",
            """ aecbfdg fbg gf bafeg dbefa fcge gcbea fcaegb dgceab fcbdga |
        gecf egdcabf bgf bfgea""",
            """ fgeab ca afcebg bdacfeg cfaedg gcfdb baec bfadeg bafgc acf |
        gebdcfa ecba ca fadegcb""",
            """ dbcfg fgd bdegcaf fgec aegbdf ecdfab fbedc dacgb gdcebf gf |
        cefg dcbef fcge gbcadfe""",
            """ bdfegc cbegaf gecbf dfcage bdacg ed bedf ced adcbefg gebcd |
        ed bcgafe cdgba cbgef""",
            """ egadfb cdbfeg cegd fecab cgb gbdefca cg fgcdab egfdb bfceg |
        gbdfcae bgc cg cgb""",
            """ gcafb gcf dcaebfg ecagb gf abcdeg gaef cafbge fdbac fegbdc |
        fgae cfgab fg bagce """)

        assertEquals(26, countOneFourEightNine(input))

    }

    @Test
    fun `test subtractString`() {
        val a = "abc"
        val b = "cb"
        assertEquals("a", subtractString(a, b))
    }

    @Test
    fun `test getSegmentMapping`() {
        val signalList = "acedgfb cdfbe gcdfa fbcad dab cefabd cdfgeb eafb cagedb ab".split(" ")
        val expected = listOf<Pair<Char, Int>>()
        assertEquals(expected, getSegmentMapping(signalList))
    }

    @Test
    fun `test part2 `() {
        val signalList = "acedgfb cdfbe gcdfa fbcad dab cefabd cdfgeb eafb cagedb ab".split(" ")
        println(signalList)
        val expected = listOf(
            Pair(8, "acedgfb"),
            Pair(5, "cdfbe"),
            Pair(2, "gcdfa"),
            Pair(3, "fbcad"),
            Pair(7, "dab"),
            Pair(9, "cefabd"),
            Pair(6, "cdfgeb"),
            Pair(4, "eafb"),
            Pair(0, "cagedb"),
            Pair(1, "ab"),
        )
        val result = getSegmentMapping(signalList)
        println("result=$result")
        assert(expected.sortedBy { it.first }.equals(result))
    }

    @Test
    fun `test find number for segment`(){
        val input = "cdfeb"
        val signalList = "acedgfb cdfbe gcdfa fbcad dab cefabd cdfgeb eafb cagedb ab".split(" ")

        val segmentMapping = getSegmentMapping(signalList)

        assertEquals(5, findNumberForSegment(input, segmentMapping))
    }

    @Test
    fun testfindAnswerForSingleLine(){
        val input = """acedgfb cdfbe gcdfa fbcad dab cefabd cdfgeb eafb cagedb ab |
        cdfeb fcadb cdfeb cdbaf"""

        val expected = 5353

        assertEquals(expected, findAnswerForSingleLine(input))

    }

}