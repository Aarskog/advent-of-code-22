import org.junit.jupiter.api.Test

interface AocSolverTester {
    val objectMother: ObjectMother
    val solver: AocSolver

    @Test
    fun `should solve the first test case`()

    @Test
    fun `should solve the second test case`()
}