import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class Day2KtTest {

    @Test
    fun directionToNumber() {
        val direction = "forward 1"
        assert(directionToNumber(direction) == 1)
    }
}