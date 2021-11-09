package yahoo.micronaut.api

import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import reactor.core.publisher.Mono
import yahoo.micronaut.controller.YahooController
import yahoo.micronaut.logger
import yahoo.micronaut.models.StockPrice


@Controller("/api")
class RestController(private val yahooController: YahooController) {


    @Get("/{ticker}")
    fun greet(ticker: String): Mono<StockPrice> {
        logger.info { "Request for ticker $ticker" }
        return Mono.justOrEmpty(yahooController.loadStockForTicker(ticker))
    }
}