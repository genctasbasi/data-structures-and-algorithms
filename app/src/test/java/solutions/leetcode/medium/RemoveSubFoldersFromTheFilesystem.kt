package solutions.leetcode.medium

import org.junit.Assert.assertArrayEquals
import org.junit.Test

/**
 * https://leetcode.com/problems/remove-sub-folders-from-the-filesystem/
 */
class RemoveSubFoldersFromTheFilesystem {

    @Test
    fun test() {
        assertArrayEquals(
            arrayOf("/a", "/c/d", "/c/f"),
            removeSubFolders(arrayOf("/a", "/a/b", "/c/d", "/c/d/e", "/c/f")).toTypedArray()
        )
    }

    @Test
    fun test2() {
        assertArrayEquals(
            arrayOf("/a/b/c", "/a/b/ca", "/a/b/d"),
            removeSubFolders(arrayOf("/a/b/c", "/a/b/ca", "/a/b/d")).toTypedArray()
        )
    }

    fun removeSubFolders(folder: Array<String>): List<String> {

        val output = mutableListOf<String>()
        folder.sort()
        folder.forEach {
            if (output.isEmpty())
                output.add(it)
            else {
                val prevFolder = output.last()
                if (it.startsWith("$prevFolder/").not()) output.add(it)
            }
        }

        return output
    }
}