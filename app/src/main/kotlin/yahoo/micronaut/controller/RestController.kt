package yahoo.micronaut.controller

import io.micronaut.http.MediaType
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.Produces
import yahoo.micronaut.models.StockPrice


@Controller("/api")
class RestController(private val yahooController: YahooController) {

    @Get("/{ticker}")
    fun greet(ticker: String): StockPrice? {
        return yahooController.loadStockForTicker(ticker)
    }
}