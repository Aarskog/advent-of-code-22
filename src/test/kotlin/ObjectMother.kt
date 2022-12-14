import java.io.File

class ObjectMother {
    val testDir = "src/test/kotlin/"

    val day01Example = ExampleCase1(input = File(testDir + "day01/input.txt").readLines(), output = "24000")
    val day01Example2 = ExampleCase1(input = File(testDir + "day01/input.txt").readLines(), output = "45000")

    val day02Example1 = ExampleCase1(input = File(testDir + "day02/input.txt").readLines(), output = "15")
//    val day01Example2 = ExampleCase1(input = File(testDir + "day02/input.txt").readLines(), output = "45000")


}

interface ExampleCase {
    val input: Any
    val output: Any
}

data class ExampleCase1(
    override val input: List<String>,
    override val output: String
) : ExampleCase
