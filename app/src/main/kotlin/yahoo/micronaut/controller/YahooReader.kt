package yahoo.micronaut.controller

import com.beust.klaxon.KlaxonException
import yahoo.micronaut.logger
import yahoo.micronaut.models.StockPrice
import java.net.URI
import java.net.http.HttpClient
import java.net.http.HttpRequest
import java.net.http.HttpResponse

private const val BASE_URL = "https://finance.yahoo.com"


fun loadStockPrice(ticker: String): StockPrice? {
    logger.info {"Load data for ticker: $ticker from $BASE_URL"}
    val client = HttpClient.newBuilder().build()
    val request = HttpRequest.newBuilder()
            .uri(URI.create("$BASE_URL/quote/$ticker/"))
            .build()
    val response = client.send(request, HttpResponse.BodyHandlers.ofString())
    return getStockPriceFromResponse(response.body(), ticker)
}

fun findJsonStockPriceFromWebsite(response: String, ticker: String): String {
    val startPosition = response.indexOf("\"$ticker\":{\"sourceInterval\"") + "\"$ticker\":".length
    var index = startPosition
    var bracketsCounter = 0
    for (i in index..response.length) {
        if (response[i] == '{') {
            bracketsCounter++
        }
        if (response[i] == '}') {
            bracketsCounter--
        }
        if (bracketsCounter == 0) {
            index = i
            break
        }
    }
    return response.substring(startPosition, index + 1)
}

fun getStockPriceFromResponse(response: String, ticker: String): StockPrice? {
    return try {
        StockPrice.fromJson(findJsonStockPriceFromWebsite(response, ticker))
    } catch (e: KlaxonException) {
        logger.info { "Could not parse response for ticker: $ticker" }
        null
    }
}

