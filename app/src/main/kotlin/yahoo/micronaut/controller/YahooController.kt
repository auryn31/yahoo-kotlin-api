package yahoo.micronaut.controller

import io.micronaut.cache.annotation.CacheConfig
import io.micronaut.cache.annotation.Cacheable
import jakarta.inject.Singleton
import yahoo.micronaut.models.StockPrice

@Singleton
@CacheConfig("stocks")
open class YahooController {

    @Cacheable
    open fun loadStockForTicker(ticker: String): StockPrice? {
        return loadStockPrice(ticker)
    }
}