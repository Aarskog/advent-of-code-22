package day01

import AocSolver
import Utils

class Day01 : AocSolver {
    override fun solveCase1(input: List<String>): String {
        var biggestSum: Int = 0
        var currentSum: Int = 0

        input.forEach {
            if (it.isEmpty()) {
                if (currentSum > biggestSum) {
                    biggestSum = currentSum
                }
                currentSum = 0
            } else {
                currentSum += it.toInt()
            }
        }

        return biggestSum.toString()
    }

    override fun solveCase2(input: List<String>): String {
        val sums: MutableList<Int> = mutableListOf()
        var currentSum: Int = 0

        input.forEach {
            if (it.isEmpty()) {
                sums.add(currentSum)
                currentSum = 0
            } else {
                currentSum += it.toInt()
            }
        }
        sums.add(currentSum)
        sums.sort()
        sums.reverse()
        return sums.slice(0..2).sum().toString()

    }
}

fun main() {
    val input = Utils.readFileAsLinesUsingReadLines("src/main/kotlin/day01/input.txt")
    val solver = Day01()
    println(solver.solveCase1(input))
    println(solver.solveCase2(input))

}