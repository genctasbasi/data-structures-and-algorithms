package solutions.hackerRank.trees

import com.escmobile.lab.domain.model.Node

abstract class TreeTestBase {

    fun insertNode(root: Node?, data: Int): Node {

        if (root == null) return Node(data)

        if (data < root.data) {
            root.left = insertNode(root.left, data)
            return root
        }

        root.right = insertNode(root.right, data)
        return root
    }

    fun buildBST(arr: List<Int>): Node {

        val root = Node()
        root.data = arr[0]
        arr.minus(arr[0]).forEach {
            insertNode(root, it)
        }

        return root
    }

}
