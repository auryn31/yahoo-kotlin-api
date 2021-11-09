package yahoo.micronaut.controller

import io.micronaut.cache.annotation.CacheConfig
import io.micronaut.cache.annotation.Cacheable
import jakarta.inject.Singleton
import yahoo.micronaut.models.StockPrice

// this is only once instantiated and the config is in our application yaml
@Singleton
@CacheConfig("stocks")
open class YahooController {

    // enable caching for this function
    // because we just have one parameter, we don't need a parameter for this annotation
    @Cacheable
    open fun loadStockForTicker(ticker: String): StockPrice? {
        return loadStockPrice(ticker)
    }
}