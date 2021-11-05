/*
 * This Kotlin source file was generated by the Gradle 'init' task.
 */
package yahoo.micronaut

import com.google.common.io.Resources
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull

class AppTest {
    @Test
    fun appHasAGreeting() {
        val classUnderTest = App()
        assertNotNull(classUnderTest.greeting, "app should have a greeting")
    }

    @Test
    fun parseSubJSONFromYahooResponseTest() {
        val responseHTML = Resources.getResource("response.txt").readText()
        val response = getStockpriceFromResponse(responseHTML, "WLD.PA")

        assertEquals(response?.regularMarketPrice?.raw, 278.37)
    }
}
