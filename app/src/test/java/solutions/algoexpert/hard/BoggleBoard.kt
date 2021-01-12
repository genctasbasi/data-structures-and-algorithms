package solutions.algoexpert.hard

import org.junit.Assert.assertEquals
import org.junit.Test

/**
 * https://www.algoexpert.io/questions/Boggle%20Board
 */
class BoggleBoard {

    @Test
    fun test() {
        val list = boggleBoard(
            listOf(
                listOf('t', 'h', 'i', 's', 'i', 's', 'a'),
                listOf('s', 'i', 'm', 'p', 'l', 'e', 'x'),
                listOf('b', 'x', 'x', 'x', 'x', 'e', 'b'),
                listOf('x', 'o', 'g', 'g', 'l', 'x', 'o'),
                listOf('x', 'x', 'x', 'D', 'T', 'r', 'a'),
                listOf('R', 'E', 'P', 'E', 'A', 'd', 'x'),
                listOf('x', 'x', 'x', 'x', 'x', 'x', 'x'),
                listOf('N', 'O', 'T', 'R', 'E', '-', 'P'),
                listOf('x', 'x', 'D', 'E', 'T', 'A', 'E')
            ),

            listOf(
                "this",
                "is",
                "not",
                "a",
                "simple",
                "boggle",
                "board",
                "test",
                "REPEATED",
                "NOTRE-PEATED"
            )
        )

        assertEquals(listOf("this", "is", "a", "simple", "boggle", "board", "NOTRE-PEATED"), list)
    }


    @Test
    fun test2() {
        val list = boggleBoard(
            listOf(
                listOf('a', 'b', 'c', 'd', 'e'),
                listOf('f', 'g', 'h', 'i', 'j'),
                listOf('k', 'l', 'm', 'n', 'o'),
                listOf('p', 'q', 'r', 's', 't'),
                listOf('u', 'v', 'w', 'x', 'y')
            ),

            listOf(
                "agmsy", "agmsytojed", "agmsytojedinhcbgl", "agmsytojedinhcbfl"
            )
        )

        assertEquals(listOf("agmsy", "agmsytojed", "agmsytojedinhcbgl", "agmsytojedinhcbfl"), list)
    }

    class TrieNode(val children: MutableMap<Char, TrieNode?>) {
        var word: String? = null
    }

    private val trieEndMark = '*'

    fun boggleBoard(board: List<List<Char>>, words: List<String>): List<String> {

        val rootTrieNode = TrieNode(mutableMapOf())

        val finalWords = mutableSetOf<String>()
        val visitedNodes = mutableMapOf<String, Boolean>()  // key is row(dot)column i.e. 3.2
        val touchedNodes = mutableMapOf<String, Boolean>()  // key is row(dot)column i.e. 3.2

        words.forEach { word ->
            buildTrie(rootTrieNode, word)
        }

        for (i in board.indices) {
            for (j in board[i].indices) {
                explore(
                    i,
                    j,
                    board,
                    visitedNodes,
                    touchedNodes,
                    rootTrieNode,
                    rootTrieNode,
                    finalWords
                )
            }
        }

        return finalWords.toList()
    }

    private fun explore(
        i: Int,
        j: Int,
        board: List<List<Char>>,
        visitedNodes: MutableMap<String, Boolean>,
        touchedNodes: MutableMap<String, Boolean>,
        trieRoot: TrieNode,
        trieNode: TrieNode,
        finalWords: MutableSet<String>
    ) {

        touchedNodes["$i.$j"] = true  // this is being visited now

        if (visitedNodes["$i.$j"] == true)
            return

        val currentLetter = board[i][j]
        val currentTrieNode = trieNode.children[currentLetter] ?: return

        visitedNodes["$i.$j"] = true  // this is being visited now

        if (currentTrieNode.children[trieEndMark] != null) {
            currentTrieNode.children[trieEndMark]?.word?.let { finalWords.add(it) }
        }

        val neighbours = getNeighbours(i, j, board)
        neighbours.forEach { pair ->
            explore(
                pair.first,
                pair.second,
                board,
                visitedNodes,
                touchedNodes,
                trieRoot,
                currentTrieNode,
                finalWords
            )
        }

        visitedNodes["$i.$j"] = false
    }

    private fun buildTrie(root: TrieNode, word: String) {

        var currentTrieNode = root

        for (i in word.indices) {
            val char = word[i]

            if (currentTrieNode.children[char] == null) {
                currentTrieNode.children[char] = TrieNode(mutableMapOf())
            }

            currentTrieNode = currentTrieNode.children[char]!!
        }

        currentTrieNode.children[trieEndMark] = TrieNode(mutableMapOf())
        currentTrieNode.children[trieEndMark]?.word = word
    }

    private fun getNeighbours(i: Int, j: Int, board: List<List<Char>>): List<Pair<Int, Int>> {
        val neighbours = mutableListOf<Pair<Int, Int>>()

        val boardHeight = board.size
        val boardWidth = if (boardHeight > 0) board[0].size else 0

        // clockwise
        if (isValidIndex(boardWidth, boardHeight, i - 1, j + 1))
            neighbours.add(Pair(i - 1, j + 1))

        if (isValidIndex(boardWidth, boardHeight, i, j + 1))
            neighbours.add(Pair(i, j + 1))

        if (isValidIndex(boardWidth, boardHeight, i + 1, j + 1))
            neighbours.add(Pair(i + 1, j + 1))

        if (isValidIndex(boardWidth, boardHeight, i + 1, j))
            neighbours.add(Pair(i + 1, j))

        if (isValidIndex(boardWidth, boardHeight, i + 1, j - 1))
            neighbours.add(Pair(i + 1, j - 1))

        if (isValidIndex(boardWidth, boardHeight, i, j - 1))
            neighbours.add(Pair(i, j - 1))

        if (isValidIndex(boardWidth, boardHeight, i - 1, j - 1))
            neighbours.add(Pair(i - 1, j - 1))

        if (isValidIndex(boardWidth, boardHeight, i - 1, j))
            neighbours.add(Pair(i - 1, j))

        return neighbours
    }

    private fun isValidIndex(boardWidth: Int, boardHeight: Int, i: Int, j: Int) =
        i >= 0 && j >= 0 && i < boardHeight && j < boardWidth
}