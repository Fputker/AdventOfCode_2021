import org.junit.jupiter.api.Test

internal class Day2KtTest {

    @Test
    fun parseInstruction() {
        val instruction = "forward 1"
        assert(parseInstruction(instruction).second == 1)
    }

    @Test
    fun `Given instruction forward5 when processinstruction get triple(5,0,0)`() {
        val input: MutableList<String> = mutableListOf("forward 5","down 5","forward 8","up 3", "down 8", "forward 2")
        val parsedInstructions = input.map { parseInstruction(it) }
        val newPosition = executePartTwoInstruction(parsedInstructions[0], Triple(0,0,0))
        assert(newPosition.first == 5)
        assert(newPosition.second == 0)
        assert(newPosition.third == 0)
    }

    @Test
    fun `Given instruction down5 when processinstruction with triple(5,0,0) get triple(5,5,0)`() {
        val input: MutableList<String> = mutableListOf("forward 5","down 5","forward 8","up 3", "down 8", "forward 2")
        val parsedInstructions = input.map { parseInstruction(it) }

        val newPosition = executePartTwoInstruction(parsedInstructions[0], Triple(0,0,0))
        val newerPosition = executePartTwoInstruction(parsedInstructions[1], newPosition)
        assert(newerPosition.first == 5)
        assert(newerPosition.second == 5)
        assert(newerPosition.third == 0)
    }

    @Test
    fun `Given instructions when processinstruction with triple(5,0,0) get triple(5,5,0)`() {
        val input: MutableList<String> = mutableListOf("forward 5","down 5","forward 8","up 3", "down 8", "forward 2")
        val result = input.map { parseInstruction(it) }
            .fold(Triple(0,0,0)) {
                    position, instruction -> executePartTwoInstruction(instruction, position)
            }
        assert ((result.first * result.third) == 900)
    }
}