package com.jet.feature.restaurant.presentation.util

import java.math.RoundingMode
import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.util.Locale

object Util {
    private val decimalFormat = DecimalFormat("#.##")
    private val decimalFormatSymbol = DecimalFormatSymbols.getInstance(Locale.ENGLISH)

    fun getStringHavingPrefixAndPostfix(
        prefix: String,
        price: String,
        postfix: String,
    ): String = "$prefix $price$postfix"

    fun getStringHavingPostfix(
        distance: String,
        postfix: String,
    ): String = "$distance$postfix"

    fun centToEuro(cent: Int): String = (cent.toDouble() / 100).round()

    fun Double.round(): String =
        decimalFormat.run {
            decimalFormatSymbols = decimalFormatSymbol
            roundingMode = RoundingMode.UP
            format(this@round)
        }
}
