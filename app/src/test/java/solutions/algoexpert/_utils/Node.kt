package solutions.algoexpert._utils

class Node(private val name: String) {
    private val children = mutableListOf<Node>()

    fun depthFirstSearch(): List<String> {
        return depthFirstSearchHelper(this, mutableListOf())
    }

    private fun depthFirstSearchHelper(
        node: Node?,
        list: MutableList<String>
    ): List<String> {

        if (node == null) return list

        list.add(node.name)

        node.children.forEach {
            depthFirstSearchHelper(it, list)
        }

        return list
    }
}