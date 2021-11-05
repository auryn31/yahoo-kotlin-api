// To parse the JSON, install Klaxon and do:
//
//   val welcome1 = Welcome1.fromJson(jsonString)

package yahoo.micronaut.models

import com.beust.klaxon.Json
import com.beust.klaxon.Klaxon

private val klaxon = Klaxon()

data class StockPrice(
        val sourceInterval: Long,
        val quoteSourceName: String,
        val regularMarketOpen: Price,
        val exchange: String,
        val regularMarketTime: Price,
        val fiftyTwoWeekRange: Range,
        val regularMarketDayHigh: Price,
        val shortName: String,
        val longName: String,
        val exchangeTimezoneName: String,
        val regularMarketChange: Price,
        val regularMarketPreviousClose: Price,
        val fiftyTwoWeekHighChange: Price,
        val exchangeTimezoneShortName: String,
        val fiftyTwoWeekLowChange: Price,
        val exchangeDataDelayedBy: Long,
        val regularMarketDayLow: Price,
        val priceHint: Long,
        val currency: String,
        val regularMarketPrice: Price,
        val regularMarketVolume: RegularMarketVolume,
        val isLoading: Boolean,
        val triggerable: Boolean,
        val gmtOffSetMilliseconds: Long,
        val region: String,
        val marketState: String,
        val quoteType: String,
        val invalid: Boolean,
        val symbol: String,
        val language: String,
        val fiftyTwoWeekLowChangePercent: Price,
        val regularMarketDayRange: Range,

        @Json(name = "messageBoardId")
        val messageBoardID: String,

        val fiftyTwoWeekHigh: Price,
        val fiftyTwoWeekHighChangePercent: Price,
        val uuid: String,
        val market: String,
        val fiftyTwoWeekLow: Price,
        val regularMarketChangePercent: Price,
        val fullExchangeName: String,
        val tradeable: Boolean
) {
    fun toJson() = klaxon.toJsonString(this)

    companion object {
        fun fromJson(json: String) = klaxon.parse<StockPrice>(json)
    }
}

data class Price(
        val raw: Double,
        val fmt: String
)

data class Range(
        val raw: String,
        val fmt: String
)

data class RegularMarketVolume(
        val raw: Long,
        val fmt: String,
        val longFmt: String
)
