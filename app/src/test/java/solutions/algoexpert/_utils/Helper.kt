package solutions.algoexpert._utils

class Helper {

    companion object {
        fun buildBST(): BST {

            val node1 = BST(1)
            val node2 = BST(2).run {
                left = node1
                this
            }

            val node52 = BST(5)
            val node5 = BST(5).run {
                left = node2
                right = node52
                this
            }

            val node14 = BST(14)
            val node22 = BST(22)
            val node13 = BST(13).run {
                right = node14
                this
            }
            val node15 = BST(15).run {
                left = node13
                right = node22
                this
            }

            return BST(10).run {
                left = node5
                right = node15
                this
            }
        }

        fun buildInvalidBST(): BST {
            return buildBST().apply {
                right?.right?.value = 14
            }
        }

        fun buildBinaryTree(): BinaryTree {

            val node1 = BinaryTree(1)
            val node2 = BinaryTree(2)
            val node3 = BinaryTree(3)
            val node4 = BinaryTree(4)
            val node5 = BinaryTree(5)
            val node6 = BinaryTree(6)
            val node7 = BinaryTree(7)
            val node8 = BinaryTree(8)
            val node9 = BinaryTree(9)
            val node10 = BinaryTree(10)

            node1.left = node2
            node1.right = node3

            node2.left = node4
            node2.right = node5

            node3.left = node6
            node3.right = node7

            node4.left = node8
            node4.right = node9

            node5.left = node10

            return node1
        }

        fun buildNode(): Node {
            TODO("Not yet implemented")
        }
    }
}