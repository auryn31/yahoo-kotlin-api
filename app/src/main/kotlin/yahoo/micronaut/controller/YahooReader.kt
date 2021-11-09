package yahoo.micronaut.controller

import com.beust.klaxon.KlaxonException
import yahoo.micronaut.logger
import yahoo.micronaut.models.StockPrice
import java.net.URI
import java.net.http.HttpClient
import java.net.http.HttpRequest
import java.net.http.HttpResponse

// This is the base URL where we will get the ticker data from
private const val BASE_URL = "https://finance.yahoo.com"


// load the data from the Yahoo url and return the StockPrice if found
fun loadStockPrice(ticker: String): StockPrice? {
    // log the request
    logger.info {"Load data for ticker: $ticker from $BASE_URL"}
    val client = HttpClient.newBuilder().build()
    // build the request
    val request = HttpRequest.newBuilder()
            .uri(URI.create("$BASE_URL/quote/$ticker/"))
            .build()
    // load the data and return the body as string
    val response = client.send(request, HttpResponse.BodyHandlers.ofString()).body()
    // parse the data and return the result
    return getStockPriceFromResponse(response, ticker)
}

// find the json in the website and return this part
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

// try to parse the selected string from the website
fun getStockPriceFromResponse(response: String, ticker: String): StockPrice? {
    return try {
        StockPrice.fromJson(findJsonStockPriceFromWebsite(response, ticker))
    } catch (e: KlaxonException) {
        logger.info { "Could not parse response for ticker: $ticker" }
        null
    }
}

