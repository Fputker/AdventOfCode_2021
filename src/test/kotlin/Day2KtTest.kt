import org.junit.jupiter.api.Test

internal class Day2KtTest {

    @Test
    fun directionToNumber() {
        val direction = "forward 1"
        assert(directionToNumber(direction) == 1)
    }

    @Test
    fun `Given Direction forward5 when processDirection get triple(5,0,0)`() {
        val input: MutableList<String> = mutableListOf("forward 5","down 5","forward 8","up 3", "down 8", "forward 2")
        val newPosition = getNewPositionUsingForwardDirection(input[0], Triple(0,0,0))
        assert(newPosition.first == 5)
        assert(newPosition.second == 0)
        assert(newPosition.third == 0)
    }

    @Test
    fun `Given Direction down5 when processDirection with triple(5,0,0) get triple(5,5,0)`() {
        val input: MutableList<String> = mutableListOf("forward 5","down 5","forward 8","up 3", "down 8", "forward 2")
        val newPosition = getNewPositionUsingForwardDirection(input[0], Triple(0,0,0))
        val newerPosition = getNewPositionUsingDownDirection(input[1], newPosition)
        assert(newerPosition.first == 5)
        assert(newerPosition.second == 5)
        assert(newerPosition.third == 0)
    }

    @Test
    fun `Given Directions when processDirection with triple(5,0,0) get triple(5,5,0)`() {
        val input: MutableList<String> = mutableListOf("forward 5","down 5","forward 8","up 3", "down 8", "forward 2")
        var position: Triple<Int,Int,Int> = Triple(0,0,0)
        for (direction in input){
            when(direction[0]){
                'f' -> {position = getNewPositionUsingForwardDirection(direction, position)}
                'd' -> {position = getNewPositionUsingDownDirection(direction, position)}
                'u' -> {position = getNewPositionUsingUpDirection(direction, position)}
            }
        }
        assert ((position.first * position.third) == 900)
    }
}