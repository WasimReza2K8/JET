package com.example.core.util

import java.math.RoundingMode
import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.util.Locale

object Util {
    private val decimalFormat = DecimalFormat("#.##")
    private val decimalFormatSymbol = DecimalFormatSymbols.getInstance(Locale.ENGLISH)
    private const val ONE_HUNDRED = 100
    private const val ONE_THOUSAND = 1000

    fun getStringHavingPrefixAndPostfix(
        prefix: String,
        price: String,
        postfix: String,
    ): String = "$prefix $price$postfix"

    fun getStringHavingPostfix(
        distance: String,
        postfix: String,
    ): String = "$distance$postfix"

    fun centToEuro(cent: Int): String = (cent.toDouble() / ONE_HUNDRED).round()

    fun meterToKm(meter: Int): String = (meter.toDouble() / ONE_THOUSAND).round()

    private fun Double.round(): String =
        decimalFormat.run {
            decimalFormatSymbols = decimalFormatSymbol
            roundingMode = RoundingMode.UP
            format(this@round)
        }
}
