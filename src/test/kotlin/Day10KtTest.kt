import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class Day10KtTest {

    @Test
    fun `test parseLine`() {
        val input = "{([(<{}[<>[]}>{[]{[(<()>"

        assertEquals('}', findInvalidClose(input))

    }

    @Test
    fun `test complete`() {
        val input = listOf("[({(<(())[]>[[{[]{<()<>>",
            "[(()[<>])]({[<{<<[]>>(",
            "{([(<{}[<>[]}>{[]{[(<()>",
            "(((({<>}<{<{<>}{[]{[]{}",
            "[[<[([]))<([[{}[[()]]]",
            "[{[{({}]{}}([{[{{{}}([]",
            "{<[[]]>}<{[{[{[]{()[[[]",
            "[<(<(<(<{}))><([]([]()",
            "<{([([[(<>()){}]>(<<{{",
            "<{([{{}}[<[[[<>{}]]]>[]]")

        val list = input.map { findInvalidClose(it) }.filter { it != '1' }

        assertEquals(listOf('}',')',']',')','>'), list)

        assertEquals(26397L, calculateScore(list))
    }

    @Test
    fun `test complete part 2`() {
        val input = listOf("[({(<(())[]>[[{[]{<()<>>",
            "[(()[<>])]({[<{<<[]>>(",
            "{([(<{}[<>[]}>{[]{[(<()>",
            "(((({<>}<{<{<>}{[]{[]{}",
            "[[<[([]))<([[{}[[()]]]",
            "[{[{({}]{}}([{[{{{}}([]",
            "{<[[]]>}<{[{[{[]{()[[[]",
            "[<(<(<(<{}))><([]([]()",
            "<{([([[(<>()){}]>(<<{{",
            "<{([{{}}[<[[[<>{}]]]>[]]")

        val list = input.map { findInvalidClose(it) }.filter { it != '1' }

        assertEquals(listOf('}',')',']',')','>'), list)

        assertEquals(26397L, calculateScore(list))
    }

    @Test
    fun `test completion`(){
        val input = "[({(<(())[]>[[{[]{<()<>>"
        val incomplete = findIncompleteLines(input)
        assertEquals("[({([[{{".toCharArray().toList(), incomplete)


        assertEquals("}}]])})]".toCharArray().toList(), completeSequence(incomplete) )
    }

    @Test
    fun `test part 2 score`(){
        val input = listOf(']',')','}','>')
        assertEquals(294, scoreforLine(input) )
    }

}