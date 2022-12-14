package day01

import ObjectMother
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals


class Day01Test {
    private val objectMother = ObjectMother()
    private val solver = Day01()

    @Test
    fun `should solve the test case`() {

        assertEquals(objectMother.day01Example.output, solver.solveCase1(objectMother.day01Example.input))
    }

    @Test
    fun `should solve the second test case`() {
        assertEquals(objectMother.day01Example2.output, solver.solveCase2(objectMother.day01Example2.input))
    }

}

