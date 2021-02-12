package solutions.leetcode.medium

import org.junit.Test

/**
 * https://leetcode.com/problems/accounts-merge/
 */
class AccountsMerge {

    @Test
    fun test() {
        val result = accountsMerge(
            listOf(
                listOf("John", "johnsmith@mail.com", "john00@mail.com"),
                listOf("John", "johnnybravo@mail.com"),
                listOf("John", "john_newyork@mail.com", "johnsmith@mail.com"),
                listOf("Mary", "mary@mail.com")
            )
        )
    }

    @Test
    fun test2() {
        val result = accountsMerge(
            listOf(
                listOf("David", "0", "1"),
                listOf("David", "3", "4"),
                listOf("David", "4", "5"),
                listOf("David", "2", "3"),
                listOf("David", "1", "2")
            )
        )
    }

    fun accountsMerge(accounts: List<List<String>>): List<List<String>> {

        if (accounts.isEmpty()) return listOf()
        val output = mutableListOf<List<String>>()

        val names = mutableMapOf<String, String>()  // email, name
        val graph = mutableMapOf<String, HashSet<String>>()    // email, list of emails

        // build the graph
        accounts.forEachIndexed { index, account ->

            val name = account[0]

            for (i in 1..account.lastIndex) { // emails

                val email = account[i]
                names[email] = name

                if (graph[email] == null) {
                    graph[email] = hashSetOf()
                }

                // put the 'edge'
                if (i == 1) continue
                graph[account[i]]?.add(account[i - 1])
                graph[account[i - 1]]?.add(account[i])
            }
        }

        val visited = hashSetOf<String>()

        graph.keys.forEach {
            val ret = mutableListOf<String>()
            doDFS(graph, it, visited, ret)
            if (ret.isNotEmpty()) {
                val retSorted = ret.sorted().toMutableList()
                retSorted.add(0, names[it]!!)
                output.add(retSorted)
            }
        }
        return output
    }

    fun doDFS(
        graph: Map<String, HashSet<String>>,
        email: String,
        visited: HashSet<String>,
        ret: MutableList<String>
    ) { // no return, simply 'fill' the ret list provided

        if (visited.contains(email)) return
        if (ret.contains(email).not()) ret.add(email)
        visited.add(email)
        graph[email]?.forEach {
            doDFS(graph, it, visited, ret)
        }
    }
}