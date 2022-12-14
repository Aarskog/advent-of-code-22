import java.io.File

class Utils {
    companion object {
        fun readFileAsLinesUsingReadLines(fileName: String): List<String> = File(fileName).readLines()

    }

}