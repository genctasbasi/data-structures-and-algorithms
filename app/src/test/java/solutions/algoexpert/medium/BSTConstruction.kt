package solutions.algoexpert.medium

import org.junit.Assert
import org.junit.Test

/**
 * https://www.algoexpert.io/questions/BST%20Construction
 */
class BSTConstruction {

    @Test
    fun testCase1() {
        val bst = BST(10)
        bst.insert(5)
        bst.insert(15)
        bst.insert(2)
        bst.insert(5)
        bst.insert(13)
        bst.insert(22)
        bst.insert(1)
        bst.insert(14)
        bst.insert(12)

        bst.remove(10)
        Assert.assertEquals(true, bst.contains(15))
    }

    @Test
    fun testCase7() {
        val bst = BST(1)
        bst.insert(2)
        bst.insert(3)
        bst.insert(4)

        bst.remove(1)
        Assert.assertEquals(false, bst.contains(15))
    }

    @Test
    fun testCase8() {
        val bst = BST(1)
        bst.insert(-2)
        bst.insert(-3)
        bst.insert(-4)

        bst.remove(1)
        Assert.assertEquals(false, bst.contains(15))
    }

    enum class ChildPosition {
        LEFT, RIGHT
    }

    open class BST(value: Int) {
        var value = value
        var left: BST? = null
        var right: BST? = null

        fun insert(value: Int): BST {

            when {
                value < this.value -> {
                    if (this.left == null) this.left = BST(value)
                    else this.left?.insert(value)
                }

                else -> {
                    if (this.right == null) this.right = BST(value)
                    else this.right?.insert(value)
                }
            }

            return this
        }

        fun contains(value: Int): Boolean {
            if (this.value == value) return true
            return this.left?.contains(value) ?: false || this.right?.contains(value) ?: false
        }

        fun remove(value: Int): BST {
            return removeHelper(value, null)
        }

        private fun removeHelper(
            value: Int,
            parent: BST?,
            childPosition: ChildPosition? = null
        ): BST {

            return when {
                this.value == value -> {
                    removeValue(parent, childPosition)
                }

                value < this.value -> {
                    this.left!!.removeHelper(value, this, ChildPosition.LEFT)
                }

                else -> {
                    this.right!!.removeHelper(value, this, ChildPosition.RIGHT)
                }
            }
        }

        private fun removeValue(
            parent: BST?,
            childPosition: ChildPosition?
        ): BST {

            when {
                parent == null -> { // this is the root edge case

                    when {
                        this.right == null && this.left == null -> {
                        } // single node, do nothing

                        else -> {
                            if (this.right != null) {
                                val leftMostNode = getLeftMostNode(this.right!!)
                                this.value = leftMostNode.value
                                this.right!!.removeHelper(leftMostNode.value, this)
                            } else {    // then it should have left
                                val rightMostNode = getRightMostNode(this.left!!)
                                this.value = rightMostNode.value
                                this.left!!.removeHelper(
                                    rightMostNode.value,
                                    this,
                                    ChildPosition.LEFT
                                )
                            }
                        }
                    }

                }

                this.left == null && this.right == null -> {
                    if (childPosition == ChildPosition.LEFT) parent.left = null
                    else parent.right = null
                }

                this.right == null -> {
                    if (childPosition == ChildPosition.LEFT)
                        parent.left = this.left
                    else parent.right = this.left
                }

                this.left == null -> {
                    if (childPosition == ChildPosition.LEFT)
                        parent.left = this.right
                    else parent.right = this.right
                }

                else -> {   // node has both right and left nodes
                    val leftMostNode = getLeftMostNode(this.right!!)
                    this.value = leftMostNode.value
                    this.right!!.removeHelper(leftMostNode.value, this)
                }
            }

            return this
        }

        private fun getLeftMostNode(node: BST): BST {
            if (node.left == null) return node
            return getLeftMostNode(node.left!!)
        }

        private fun getRightMostNode(node: BST): BST {
            if (node.right == null) return node
            return getRightMostNode(node.right!!)
        }
    }
}