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
        val newPosition = getNewPositionUsingForwardinstruction(input[0], Triple(0,0,0))
        assert(newPosition.first == 5)
        assert(newPosition.second == 0)
        assert(newPosition.third == 0)
    }

    @Test
    fun `Given instruction down5 when processinstruction with triple(5,0,0) get triple(5,5,0)`() {
        val input: MutableList<String> = mutableListOf("forward 5","down 5","forward 8","up 3", "down 8", "forward 2")
        val newPosition = getNewPositionUsingForwardinstruction(input[0], Triple(0,0,0))
        val newerPosition = getNewPositionUsingDowninstruction(input[1], newPosition)
        assert(newerPosition.first == 5)
        assert(newerPosition.second == 5)
        assert(newerPosition.third == 0)
    }

    @Test
    fun `Given instructions when processinstruction with triple(5,0,0) get triple(5,5,0)`() {
        val input: MutableList<String> = mutableListOf("forward 5","down 5","forward 8","up 3", "down 8", "forward 2")
        var position: Triple<Int,Int,Int> = Triple(0,0,0)
        for (instruction in input){
            when(instruction[0]){
                'f' -> {position = getNewPositionUsingForwardinstruction(instruction, position)}
                'd' -> {position = getNewPositionUsingDowninstruction(instruction, position)}
                'u' -> {position = getNewPositionUsingUpinstruction(instruction, position)}
            }
        }
        assert ((position.first * position.third) == 900)
    }
}