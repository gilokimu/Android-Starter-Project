package me.gilo.localdataprovider.utils

import java.text.DecimalFormat

/**
 * Created by gilo on 1/31/16.
 */
object StringFormatter {

    fun formatPrice(price: Float): String {
        val formatter = DecimalFormat("#,###.00")
        val formattedText = formatter.format(price.toDouble())
        return "$$formattedText"
    }

    fun formatNumber(price: Float): String {
        val formatter = DecimalFormat("#,###.00")
        val formattedText = formatter.format(price.toDouble())
        return "" + formattedText
    }
}
