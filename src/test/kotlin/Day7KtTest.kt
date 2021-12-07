import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class Day7KtTest{

    @Test
    fun `test mapTotalConsumptionToPositions`(){
        val input = listOf(16,1,2,0,4,2,7,1,2,14)
        assertEquals(5, mapTotalConsumptionToPositions(input))
    }

    @Test
    fun `test partial sum`(){
        assertEquals(66, getNthTriangleNumber(11))
    }

}