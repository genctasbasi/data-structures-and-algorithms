package solutions.leetcode.easy

import org.junit.Assert
import org.junit.Test

/**
 * https://leetcode.com/problems/employee-importance/
 */
class EmployeeImportance {

    @Test
    fun test() {
        val employee1 = Employee(1, 5, listOf(2, 3))
        val employee2 = Employee(2, 3, listOf())
        val employee3 = Employee(3, 3, listOf())

        Assert.assertEquals(11, getImportance(listOf(employee1, employee2, employee3), 1))
    }

    fun getImportance(employees: List<Employee?>, id: Int): Int {

        val employeeMap = mutableMapOf<Int, Employee>()
        employees.forEach {
            it?.let { employee ->
                employeeMap[employee.id] = employee
            }
        }

        return getImportanceMemo(employeeMap, id, mutableMapOf())
    }

    fun getImportanceMemo(
        employees: MutableMap<Int, Employee>?,
        id: Int,
        map: MutableMap<Int, Int>
    ): Int {

        if (map[id] != null) return map[id]!!
        if (employees == null) return 0

        var sum = employees[id]?.importance ?: 0
        employees[id]?.subordinates?.forEach {
            val subordinateEmployee = employees[it]
            subordinateEmployee?.let {
                val importance = getImportanceMemo(employees, subordinateEmployee.id, map)
                map[subordinateEmployee.id] = importance
                sum += importance
            }
        }

        return sum
    }

    class Employee(
        var id: Int = 0, var importance: Int = 0, var subordinates: List<Int> = listOf()
    )
}