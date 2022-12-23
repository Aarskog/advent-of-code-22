package day16

import AocSolver
import Utils

data class Valve(
    val name: String,
    val flowRate: Int,
    val leadsTo: List<String>,
//    var opened: Boolean = false,
//    var timesVisited: Int = 0
) {
    override fun hashCode(): Int {
        return name.hashCode()
    }

    override fun equals(other: Any?): Boolean {
        other as Valve
        return this.name == other.name
    }
}

fun Boolean.toInt() = if (this) 1 else 0

class Day16 : AocSolver {

    var pressures: MutableSet<Int> = mutableSetOf()
    var numBadValves: Int = 0

    override fun solveCase1(input: List<String>): String {
        val valves = createValves(input)
//        return traverseValves(valves["AA"]!!, valves, 0, 20, mutableMapOf(), 0).toString()
        val result = traverseValves2(valves["AA"]!!, valves, 29, 0, mapOf(), mutableMapOf()).toString()
        return pressures.max().toString()


    }

    private fun createValves(input: List<String>): Map<String, Valve> {
        val regexGetValveNames = "[A-Z]{2}".toRegex()
        val regexGetFlowRate = "[\\d]+".toRegex()

        val valves: MutableMap<String, Valve> = mutableMapOf()
        input.forEach { valveDesc ->
            val valveNames: List<String> =
                regexGetValveNames.findAll(valveDesc).map { matchResult ->
                    matchResult.groupValues.first()
                }.toList()

            val flowRate: Int = regexGetFlowRate.find(valveDesc)!!.groupValues.first().toInt()
            if (flowRate == 0) {
                numBadValves++
            }
            valves[valveNames.first()] = Valve(valveNames.first(), flowRate, valveNames.slice(1 until valveNames.size))


        }
        return valves
    }

    override fun solveCase2(input: List<String>): String {
        TODO("Not yet implemented")
    }

    private fun traverseValves2(
        currentValve: Valve,
        valves: Map<String, Valve>,
        minutesLeft: Int,
        totalPressureReleased: Int,
        openedValves: Map<Valve, Boolean>,
        visitedValves: MutableMap<Valve, Int>,
    ) {
        pressures.add(totalPressureReleased)
        if (minutesLeft <= 0 || (openedValves.size + numBadValves) == valves.size) {

//            if ((openedValves.size + numBadValves) == valves.size) {
//                println("gitt!) ${pressures.max()}")
//            }

            return
        }
        var pressureReleased = 0

        visitedValves[currentValve] = visitedValves.getOrDefault(currentValve, 0) + 1
        println(openedValves.size)


        val nextValves = getNextValves(currentValve, valves, openedValves).reversed()
        nextValves.filter { visitedValves.getOrDefault(it, 0) < 2 }.forEach {

            if (it.name == currentValve.name) {
                pressureReleased = currentValve.flowRate * minutesLeft
                traverseValves2(
                    it,
                    valves,
                    minutesLeft - 1,
                    totalPressureReleased + pressureReleased,
                    openedValves + mapOf(it to true),
                    visitedValves.toList().toMap().toMutableMap()
                )
            } else {
                traverseValves2(
                    it,
                    valves,
                    minutesLeft - 1,
                    totalPressureReleased,
                    openedValves,
                    visitedValves.toList().toMap().toMutableMap()
                )
            }
        }
        return
    }

    private fun getNextValves(
        currentValve: Valve,
        valves: Map<String, Valve>,
        openedValves: Map<Valve, Boolean>
    ): List<Valve> {
        val possibleValves: MutableList<Valve> =
            currentValve.leadsTo.map { possibleValve -> valves[possibleValve]!! }.toMutableList()

        // Add the possibility to open the current valve
        if (currentValve.flowRate != 0 && !openedValves.containsKey(currentValve)) {
            possibleValves.add(currentValve)
        }
        return possibleValves
    }
//
//    private fun traverseValves(
//        currentValve: Valve,
//        valves: Map<String, Valve>,
//        totalPressureReleased: Int,
//        minutesLeft: Int,
//        openedValves: MutableMap<Valve, Boolean>,
//        maxValue: Int
//    ): Int {
//
//        currentValve.timesVisited++
//
//        if (minutesLeft <= 0 || openedValves.size == valves.size) {
//            return totalPressureReleased
//        }
//        var pressureReleased = totalPressureReleased
//
//        val possibleValves: MutableList<Valve> =
//            currentValve.leadsTo.map { possibleValve -> valves[possibleValve]!! }.toMutableList()
//
//        // Add the possibility to open the current valve
//        if (currentValve.flowRate != 0 && !currentValve.opened) {
//            possibleValves.add(currentValve)
//        } else if (currentValve.flowRate == 0) {
//            openedValves[currentValve] = true
//        }
//
//
//        possibleValves.filter { it.timesVisited < 2 }.forEach { possibleValve ->
//
//            // Choose to open the current valve
//            if (possibleValve == currentValve) {
//                currentValve.opened = true
//                openedValves[currentValve] = true
//                pressureReleased = traverseValves(
//                    currentValve.copy(),
//                    valves,
//                    totalPressureReleased + minutesLeft * currentValve.flowRate,
//                    minutesLeft - 1,
//                    openedValves.toList().toMap().toMutableMap(),
//                    maxOf(pressureReleased, maxValue)
//                )
//
//            }
//            //Choose to move to another valve
//            else {
////                println(
////                    "Moving from ${currentValve.name} to ${possibleValve.name} with $minutesLeft minutes left. "
////                )
//                pressureReleased = traverseValves(
//                    possibleValve.copy(),
//                    valves,
//                    totalPressureReleased,
//                    minutesLeft - 1,
//                    openedValves.toList().toMap().toMutableMap(),
//                    maxOf(pressureReleased, maxValue)
//                )
//            }
//        }
////        println("Pressure released $totalPressureReleased with $minutesLeft minutes left")
//        return maxOf(pressureReleased, maxValue)
//    }

}


fun main() {
    val input = Utils.readFileAsLinesUsingReadLines("src/main/kotlin/day16/input.txt")
    val solver = Day16()
    println(solver.solveCase1(input))
//    println(solver.solveCase2(input))

}

