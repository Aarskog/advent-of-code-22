import java.io.File

class ObjectMother {
    val testDir = "src/test/kotlin/"

    val day01Example = Example(input = File(testDir + "day01/input.txt").readLines(), output = "24000")
    val day01Example2 = Example(input = File(testDir + "day01/input.txt").readLines(), output = "45000")

    val day02Example1 = Example(input = File(testDir + "day02/input.txt").readLines(), output = "15")
//    val day01Example2 = ExampleCase1(input = File(testDir + "day02/input.txt").readLines(), output = "45000")

    val day14Example1 = Example(input = File(testDir + "day14/input.txt").readLines(), output = "24")
    val day14Example2 = Example(input = File(testDir + "day14/input.txt").readLines(), output = "93")

    val day16Example1 = Example(input = File(testDir + "day16/input.txt").readLines(), output = "1651")
//    val day1&Example2 = Example(input = File(testDir + "day14/input.txt").readLines(), output = "93")


}

interface ExampleCase {
    val input: Any
    val output: Any
}

data class Example(
    override val input: List<String>,
    override val output: String
) : ExampleCase
