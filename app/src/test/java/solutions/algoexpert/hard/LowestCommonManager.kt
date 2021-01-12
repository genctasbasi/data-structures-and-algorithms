package solutions.algoexpert.hard

import junit.framework.Assert.assertEquals
import org.junit.Test

/**
 * https://www.algoexpert.io/questions/Lowest%20Common%20Manager
 */
class LowestCommonManager {

    @Test
    fun test() {

        val orgChartA = OrgChart('A')
        val orgChartB = OrgChart('B')
        val orgChartC = OrgChart('C')
        val orgChartD = OrgChart('D')
        val orgChartE = OrgChart('E')
        val orgChartF = OrgChart('F')
        val orgChartG = OrgChart('G')
        val orgChartH = OrgChart('H')
        val orgChartI = OrgChart('I')

        orgChartD.directReports.add(orgChartH)
        orgChartD.directReports.add(orgChartI)

        orgChartB.directReports.add(orgChartD)
        orgChartB.directReports.add(orgChartE)

        orgChartC.directReports.add(orgChartF)
        orgChartC.directReports.add(orgChartG)

        orgChartA.directReports.add(orgChartB)
        orgChartA.directReports.add(orgChartC)

        val lowestCommonManager = getLowestCommonManager(orgChartA, orgChartE, orgChartI)
        assertEquals(orgChartB, lowestCommonManager)
    }

    @Test
    fun test10() {

        val orgChartA = OrgChart('A')
        val orgChartB = OrgChart('B')
        val orgChartC = OrgChart('C')
        val orgChartD = OrgChart('D')
        val orgChartE = OrgChart('E')
        val orgChartF = OrgChart('F')
        val orgChartG = OrgChart('G')
        val orgChartH = OrgChart('H')
        val orgChartI = OrgChart('I')
        val orgChartJ = OrgChart('J')
        val orgChartK = OrgChart('K')
        val orgChartL = OrgChart('L')
        val orgChartM = OrgChart('M')
        val orgChartN = OrgChart('N')
        val orgChartO = OrgChart('O')
        val orgChartP = OrgChart('P')
        val orgChartQ = OrgChart('Q')
        val orgChartR = OrgChart('R')
        val orgChartS = OrgChart('S')
        val orgChartT = OrgChart('T')
        val orgChartU = OrgChart('U')
        val orgChartV = OrgChart('V')
        val orgChartW = OrgChart('W')
        val orgChartX = OrgChart('X')
        val orgChartY = OrgChart('Y')
        val orgChartZ = OrgChart('Z')

        orgChartA.directReports.add(orgChartB)
        orgChartA.directReports.add(orgChartC)
        orgChartA.directReports.add(orgChartD)
        orgChartA.directReports.add(orgChartE)
        orgChartA.directReports.add(orgChartF)

        orgChartB.directReports.add(orgChartG)
        orgChartB.directReports.add(orgChartH)
        orgChartB.directReports.add(orgChartI)

        orgChartC.directReports.add(orgChartJ)

        orgChartD.directReports.add(orgChartK)
        orgChartD.directReports.add(orgChartL)

        orgChartF.directReports.add(orgChartM)
        orgChartF.directReports.add(orgChartN)

        orgChartH.directReports.add(orgChartO)
        orgChartH.directReports.add(orgChartP)
        orgChartH.directReports.add(orgChartQ)
        orgChartH.directReports.add(orgChartR)

        orgChartK.directReports.add(orgChartS)

        orgChartP.directReports.add(orgChartT)
        orgChartP.directReports.add(orgChartU)

        orgChartR.directReports.add(orgChartV)

        orgChartV.directReports.add(orgChartW)
        orgChartV.directReports.add(orgChartX)
        orgChartV.directReports.add(orgChartY)

        orgChartX.directReports.add(orgChartZ)


        val lowestCommonManager = getLowestCommonManager(orgChartA, orgChartT, orgChartH)
        assertEquals(orgChartH, lowestCommonManager)
    }

    class OrgChart(name: Char) {
        val name = name
        val directReports = mutableListOf<OrgChart>()
    }

    fun getLowestCommonManager(
        topManager: OrgChart,
        reportOne: OrgChart,
        reportTwo: OrgChart
    ): OrgChart? {
        val commonManagerList = getLowestCommonManagerHelper(topManager, reportOne, reportTwo)
        return if (commonManagerList?.isNotEmpty() == true) commonManagerList[0] else null
    }

    fun getLowestCommonManagerHelper(
        topManager: OrgChart,
        reportOne: OrgChart,
        reportTwo: OrgChart
    ): MutableList<OrgChart>? {

        if (topManager.directReports.size == 0) {
            return if (topManager == reportOne || topManager == reportTwo)
                mutableListOf(topManager)
            else null
        }

        val reportLists = mutableListOf<MutableList<OrgChart>>()
        topManager.directReports.forEach {
            val list = getLowestCommonManagerHelper(it, reportOne, reportTwo)
            if (list != null) reportLists.add(list)
        }

        if (reportLists.isEmpty())
            return if (topManager == reportOne || topManager == reportTwo) mutableListOf()
            else null

        val count = reportLists.count { it.isNotEmpty() }
        return if (count == 1) {
            if (topManager == reportOne || topManager == reportTwo)
                mutableListOf(topManager)
            else reportLists[0]
        } else mutableListOf(topManager)
    }
}