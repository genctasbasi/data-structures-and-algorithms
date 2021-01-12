package solutions.algoexpert.medium

import org.junit.Assert.assertEquals
import org.junit.Test

/**
 * https://www.algoexpert.io/questions/Youngest%20Common%20Ancestor
 *
 * Space complexity is O(log(n)) + O(log(m)), since I'm returning a list of ancestors and comparing the lists
 * On AlgoExpert website space complexity is O(1)
 */
class YoungestCommonAncestor {

    @Test
    fun testCase1() {

        val nodeA = AncestralTree('A')
        val nodeB = AncestralTree('B')
        val nodeC = AncestralTree('C')
        val nodeD = AncestralTree('D')
        val nodeE = AncestralTree('E')
        val nodeF = AncestralTree('F')
        val nodeG = AncestralTree('G')
        val nodeH = AncestralTree('H')
        val nodeI = AncestralTree('I')

        nodeH.ancestor = nodeD
        nodeI.ancestor = nodeD

        nodeD.ancestor = nodeB
        nodeE.ancestor = nodeB

        nodeB.ancestor = nodeA
        nodeC.ancestor = nodeA

        nodeF.ancestor = nodeC
        nodeG.ancestor = nodeC

        assertEquals(nodeB, getYoungestCommonAncestor(nodeA, nodeE, nodeI))
    }

    @Test
    fun testCase2() {

        val nodeA = AncestralTree('A')
        val nodeB = AncestralTree('B')
        val nodeC = AncestralTree('C')
        val nodeD = AncestralTree('D')
        val nodeE = AncestralTree('E')
        val nodeF = AncestralTree('F')
        val nodeG = AncestralTree('G')
        val nodeH = AncestralTree('H')
        val nodeI = AncestralTree('I')

        val nodeJ = AncestralTree('J')
        val nodeK = AncestralTree('K')
        val nodeL = AncestralTree('L')
        val nodeM = AncestralTree('M')
        val nodeN = AncestralTree('N')
        val nodeO = AncestralTree('O')
        val nodeP = AncestralTree('P')
        val nodeQ = AncestralTree('Q')
        val nodeR = AncestralTree('R')

        val nodeS = AncestralTree('S')
        val nodeT = AncestralTree('T')
        val nodeU = AncestralTree('U')
        val nodeV = AncestralTree('V')
        val nodeW = AncestralTree('W')
        val nodeX = AncestralTree('X')
        val nodeY = AncestralTree('y')
        val nodeZ = AncestralTree('Z')

        nodeB.ancestor = nodeA
        nodeC.ancestor = nodeA
        nodeD.ancestor = nodeA
        nodeE.ancestor = nodeA
        nodeF.ancestor = nodeA

        nodeG.ancestor = nodeB
        nodeH.ancestor = nodeB
        nodeI.ancestor = nodeB

        nodeJ.ancestor = nodeC

        nodeK.ancestor = nodeD
        nodeL.ancestor = nodeD

        nodeM.ancestor = nodeF
        nodeN.ancestor = nodeF

        nodeO.ancestor = nodeH
        nodeP.ancestor = nodeH
        nodeQ.ancestor = nodeH
        nodeR.ancestor = nodeH

        nodeS.ancestor = nodeK

        nodeT.ancestor = nodeP
        nodeU.ancestor = nodeP

        nodeV.ancestor = nodeR

        nodeW.ancestor = nodeV
        nodeX.ancestor = nodeV
        nodeY.ancestor = nodeV

        nodeZ.ancestor = nodeX

        val youngest = getYoungestCommonAncestor(nodeA, nodeT, nodeH)
        assertEquals(nodeH, youngest)
    }


    class AncestralTree(name: Char) {
        val name = name
        var ancestor: AncestralTree? = null
    }

    fun getYoungestCommonAncestor(
        topAncestor: AncestralTree,
        descendantOne: AncestralTree,
        descendantTwo: AncestralTree
    ): AncestralTree? {

        val ancestor1 = getAncestors(topAncestor, descendantOne)
        val ancestor2 = getAncestors(topAncestor, descendantTwo)

        var index1 = ancestor1.size - 1
        var index2 = ancestor2.size - 1
        var youngestCommonAncestor: AncestralTree? = null
        while (index1 >= 0 &&
            index2 >= 0 &&
            ancestor1[index1] == ancestor2[index2]
        ) {

            youngestCommonAncestor = ancestor1[index1]
            index1--
            index2--
        }

        return youngestCommonAncestor
    }

    fun getAncestors(
        topAncestor: AncestralTree,
        descendant: AncestralTree
    ): List<AncestralTree> {

        val list = mutableListOf<AncestralTree>()
        list.add(descendant)
        var currentAncestor = descendant.ancestor
        while (currentAncestor != null && currentAncestor != topAncestor) {
            list.add(currentAncestor)
            currentAncestor = currentAncestor.ancestor
        }

        list.add(topAncestor)
        return list
    }

}