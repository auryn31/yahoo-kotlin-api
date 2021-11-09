package yahoo.micronaut.controller

import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import reactor.core.publisher.Mono
import yahoo.micronaut.models.StockPrice


@Controller("/api")
class RestController(private val yahooController: YahooController) {

    @Get("/{ticker}")
    fun greet(ticker: String): Mono<StockPrice> {
        return Mono.justOrEmpty(yahooController.loadStockForTicker(ticker))
    }
}