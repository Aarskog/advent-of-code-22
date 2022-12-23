package day16

import AocSolver
import AocSolverTester
import ObjectMother
import kotlin.test.Test
import kotlin.test.assertEquals

class Day16Test : AocSolverTester {
    override val objectMother: ObjectMother = ObjectMother()
    override val solver: AocSolver = Day16()

    @Test
    override fun `should solve the first test case`() {
        assertEquals(objectMother.day16Example1.output, solver.solveCase1(objectMother.day16Example1.input))
    }

    @Test
    override fun `should solve the second test case`() {
        TODO("Not yet implemented")
    }
}