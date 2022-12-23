package day14

import ObjectMother
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class Day14Test {
    private val objectMother = ObjectMother()
    private val solver = Day14()

    @Test
    fun `should solve the first test case`() {
        assertEquals(objectMother.day14Example1.output, solver.solveCase1(objectMother.day14Example1.input))
    }

    @Test
    fun `should solve the second test case`() {
        assertEquals(objectMother.day14Example2.output, solver.solveCase2(objectMother.day14Example2.input))
    }
}
