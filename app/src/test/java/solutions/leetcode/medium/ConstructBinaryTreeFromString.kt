package solutions.leetcode.medium

import junit.framework.TestCase.assertEquals
import org.junit.Test
import java.util.*

/**
 * https://leetcode.com/problems/construct-binary-tree-from-string/
 */
class ConstructBinaryTreeFromString {

    @Test
    fun test() {
        val root = str2tree("-4(2(-3)(1))(-6(5))")
        assertEquals(-4, root?.`val`)
        assertEquals(2, root?.left?.`val`)
        assertEquals(-3, root?.left?.left?.`val`)
        assertEquals(1, root?.left?.right?.`val`)
        assertEquals(-6, root?.right?.`val`)
        assertEquals(5, root?.right?.left?.`val`)
    }

    @Test
    fun test1() {
        val root = str2tree("-4")
        assertEquals(-4, root?.`val`)
    }

    class TreeNode(var `val`: Int) {
        var left: TreeNode? = null
        var right: TreeNode? = null
    }

    fun str2tree(s: String): TreeNode? {

        val numberStack = Stack<TreeNode>()
        val sb = StringBuilder()

        s.forEach {

            when (it) {
                '(' -> {
                    if (sb.isNotEmpty()) {
                        val number = sb.toString().toInt()
                        numberStack.add(TreeNode(number))
                        sb.clear()
                    }
                }

                ')' -> {    // time to pop
                    if (sb.isNotEmpty()) {
                        val number = sb.toString().toInt()
                        val newNode = TreeNode(number)
                        val root = numberStack.peek()
                        if (root.left == null) root.left = newNode else root.right = newNode
                        sb.clear()
                    } else {
                        val newNode = numberStack.pop()
                        val root = numberStack.peek()
                        if (root.left == null) root.left = newNode else root.right = newNode
                    }
                }

                else -> {
                    sb.append(it)   // i.e. building the number
                }
            }
        }

        return if (numberStack.isEmpty()) {
            if (sb.isEmpty()) null else TreeNode(sb.toString().toInt())
        } else numberStack.pop()
    }
}