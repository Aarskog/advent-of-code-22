package day14

import AocSolver
import Utils
import kotlin.math.abs

data class Coordinate(val coordX: Int, val coordY: Int)

class Day14 : AocSolver {
    override fun solveCase1(input: List<String>): String {

        // Create map of all coordinates with stones
        val (coords, biggestY) = createCoordinateMap(input)

        val startPos = Coordinate(500, 0)
        var nextPos = startPos
        var iterations = 0
        while (nextPos.coordY < biggestY) {
            nextPos = getNextPosCase1(startPos, coords, biggestY)

            // Add sand to position
            coords[nextPos] = true
            iterations++
        }

        return (iterations - 1).toString()
    }

    override fun solveCase2(input: List<String>): String {
        // Create map of all coordinates with stones
        val (coords, biggestY) = createCoordinateMap(input)

        val startPos = Coordinate(500, 0)
        var nextPos = Coordinate(startPos.coordX, startPos.coordY + 1)
        var iterations = 0
        while (nextPos != startPos) {
            nextPos = getNextPosCase2(startPos, coords, biggestY)
            coords[nextPos] = true
            iterations++
        }

        return iterations.toString()
    }
}


// Create coordinate map of where the stones are
private fun createCoordinateMap(input: List<String>): Pair<MutableMap<Coordinate, Boolean>, Int> {
    fun String.removeWhitespaces() = replace(" ", "")

    val coords: MutableMap<Coordinate, Boolean> = mutableMapOf()
    var biggestY = 0

    // Iterate through each line and populate coords map with stones
    input.forEach {
        val lineCoords: MutableList<Coordinate> = mutableListOf()

        // Extract start and end coordinates of each line with stones
        it.removeWhitespaces().split("->").forEach { s ->
            val coordinate = s.split(",")
            val coordX = coordinate[0].toInt()
            val coordY = coordinate[1].toInt()

            val newCoordinate = Coordinate(coordX, coordY)

            // Input line with stones
            if (lineCoords.isNotEmpty()) {
                val lineStartCoord: Coordinate = lineCoords.last()
                // Fill coords with coordinates between the two lines
                val diffX = newCoordinate.coordX - lineStartCoord.coordX
                val diffY = newCoordinate.coordY - lineStartCoord.coordY

                if (diffX != 0) {
                    for (i in 0..abs(newCoordinate.coordX - lineStartCoord.coordX)) {
                        val newX = lineStartCoord.coordX + diffX - i * (diffX / abs(diffX))
                        coords[Coordinate(
                            newX,
                            lineStartCoord.coordY
                        )] = true
                    }
                }
                if (diffY != 0) {
                    for (i in 0..abs(newCoordinate.coordY - lineStartCoord.coordY)) {
                        coords[Coordinate(
                            lineStartCoord.coordX,
                            lineStartCoord.coordY + i * (diffY / abs(diffY))
                        )] =
                            true
                    }
                }
            }
            lineCoords.add(newCoordinate)


            biggestY = if (coordY > biggestY) coordY else biggestY
            coords[newCoordinate] = true
        }
    }

    return Pair(coords, biggestY)
}

private fun getNextPosCase1(pos: Coordinate, coords: MutableMap<Coordinate, Boolean>, biggestY: Int): Coordinate {

    var nextPos = Coordinate(pos.coordX, pos.coordY + 1)
    if (nextPos.coordY > biggestY) {
        return nextPos
    }

    // If next position is unoccupied
    if (!coords.containsKey(nextPos)) {
        nextPos = getNextPosCase1(nextPos, coords, biggestY)
        // If space is occupied
    } else if (coords.containsKey(nextPos)) {
        nextPos = Coordinate(pos.coordX - 1, pos.coordY + 1)

        // If down left is unoccupied
        if (!coords.containsKey(nextPos)) {
            nextPos = getNextPosCase1(nextPos, coords, biggestY)
            // If down left is occupied
        } else if (coords.containsKey(nextPos)) {
            nextPos = Coordinate(pos.coordX + 1, pos.coordY + 1)
            // If down right is unoccupied
            if (!coords.containsKey(nextPos)) {
                nextPos = getNextPosCase1(nextPos, coords, biggestY)

                // If down right is occupied, the sand will come to rest
            } else if (coords.containsKey(nextPos)) {
                return pos
            }

        }
    }
    return nextPos
}

private fun getNextPosCase2(pos: Coordinate, coords: MutableMap<Coordinate, Boolean>, biggestY: Int): Coordinate {

    var nextPos = Coordinate(pos.coordX, pos.coordY + 1)

    // Add floor
    if (nextPos.coordY > biggestY) {
        val adder = 2
        coords[Coordinate(nextPos.coordX, biggestY + adder)] = true
        coords[Coordinate(nextPos.coordX - 1, biggestY + adder)] = true
        coords[Coordinate(nextPos.coordX + 1, biggestY + adder)] = true
    }

    // If next position is unoccupied
    if (!coords.containsKey(nextPos)) {
        nextPos = getNextPosCase2(nextPos, coords, biggestY)
        // If space is occupied
    } else if (coords.containsKey(nextPos)) {
        nextPos = Coordinate(pos.coordX - 1, pos.coordY + 1)

        // If down left is unoccupied
        if (!coords.containsKey(nextPos)) {
            nextPos = getNextPosCase2(nextPos, coords, biggestY)
            // If down left is occupied
        } else if (coords.containsKey(nextPos)) {
            nextPos = Coordinate(pos.coordX + 1, pos.coordY + 1)
            // If down right is unoccupied
            if (!coords.containsKey(nextPos)) {
                nextPos = getNextPosCase2(nextPos, coords, biggestY)

                // If down right is occupied, the sand will come to rest
            } else if (coords.containsKey(nextPos)) {
                return pos
            }

        }
    }
    return nextPos
}


fun main() {
    val input = Utils.readFileAsLinesUsingReadLines("src/main/kotlin/day14/input.txt")
    val solver = Day14()
    println(solver.solveCase1(input))
    println(solver.solveCase2(input))

}