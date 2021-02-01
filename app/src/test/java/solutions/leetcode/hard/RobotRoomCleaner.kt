package solutions.leetcode.hard

import org.junit.Test

/**
 * https://leetcode.com/problems/robot-room-cleaner/
 */
class RobotRoomCleaner {

    @Test
    fun test() {

        // 0 means the cell is blocked, while 1 means the cell is accessible
        val input = arrayOf(
            intArrayOf(1, 1, 1, 1, 1, 0, 1, 1),
            intArrayOf(1, 1, 1, 1, 1, 0, 1, 1),
            intArrayOf(1, 0, 1, 1, 1, 1, 1, 1),
            intArrayOf(0, 0, 0, 1, 0, 0, 0, 0),
            intArrayOf(1, 1, 1, 1, 1, 1, 1, 1)
        )

        val robot = Robot()
        val solution = Solution()
        solution.cleanRoom(robot)
    }

    class Solution {

        private val visited = HashSet<Pair<Int, Int>>()
        lateinit var robot: Robot

        fun cleanRoom(robot: Robot) {
            this.robot = robot
            explore(0, 0, Direction.UP)
        }

        fun explore(row: Int, col: Int, direction: Direction) {

            visited.add(Pair(row, col))
            robot.clean()

            var currentDirection = direction

            // now explore 4 squares around and come back to this square
            (0..3).forEach { _ ->
                if (robot.move()) { // if robot can move..

                    when (currentDirection) {
                        Direction.UP -> {
                            // visit UP & come back
                            if (!visited.contains(Pair(row - 1, col))) {
                                explore(row - 1, col, currentDirection)
                            }
                        }
                        Direction.RIGHT -> {
                            if (!visited.contains(Pair(row, col + 1))) {
                                explore(row, col + 1, currentDirection)
                            }
                        }
                        Direction.DOWN -> {
                            if (!visited.contains(Pair(row + 1, col))) {
                                explore(row + 1, col, currentDirection)
                            }
                        }
                        Direction.LEFT -> {
                            if (!visited.contains(Pair(row, col - 1))) {
                                explore(row, col - 1, currentDirection)
                            }
                        }
                    }

                    goBack()
                }

                // turn right
                robot.turnRight()
                currentDirection = getNextDirection(currentDirection)
            }
        }

        /**
         * returns next direction assuming we're turning right
         */
        private fun getNextDirection(currentDirection: Direction): Direction {
            return when (currentDirection) {
                Direction.UP -> Direction.RIGHT
                Direction.RIGHT -> Direction.DOWN
                Direction.DOWN -> Direction.LEFT
                Direction.LEFT -> Direction.UP
            }
        }

        private fun goBack() {
            robot.turnRight()
            robot.turnRight()
            robot.move()
            robot.turnRight()
            robot.turnRight()
        }
    }

    enum class Direction {
        UP, DOWN, LEFT, RIGHT
    }

    class Robot {
        // Returns true if the cell in front is open and robot moves into the cell.
        // Returns false if the cell in front is blocked and robot stays in the current cell.
        fun move(): Boolean {
            return false
        }

        // Robot will stay in the same cell after calling turnLeft/turnRight.
        // Each turn will be 90 degrees.
        fun turnLeft() {}
        fun turnRight() {}

        // Clean the current cell.
        fun clean() {}
    }
}