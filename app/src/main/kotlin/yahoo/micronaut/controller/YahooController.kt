package yahoo.micronaut.controller

import jakarta.inject.Singleton
import yahoo.micronaut.loadStockPrice
import yahoo.micronaut.models.StockPrice

@Singleton
class YahooController {

    fun loadStockForTicker(ticker: String) : StockPrice? {
        return loadStockPrice(ticker)
    }
}