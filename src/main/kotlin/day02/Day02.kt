package day02

import AocSolver
import Utils

class Day02 : AocSolver {
    override fun solveCase1(input: List<String>): String {
        TODO("Not yet implemented")
    }

    override fun solveCase2(input: List<String>): String {
        TODO("Not yet implemented")
    }

    private fun getResult(opponentMove: Move, myMove: Move): Results {
        return Results.WIN

    }

}

fun main() {
    val input = Utils.readFileAsLinesUsingReadLines("src/main/kotlin/day02/input.txt")
    val solver = Day02()
    println(solver.solveCase1(input))
    println(solver.solveCase2(input))

}

enum class Move { ROCK, PAPER, SCISSORS }


enum class OpponentMove(val move: String) {
    ROCK("A"),
    PAPER("B"),
    SCISSORS("C")
}

enum class MyMove(val move: String) {
    ROCK("X"),
    PAPER("Y"),
    SCISSORS("Z")
}

enum class Results { WIN, LOSS, DRAW }