package com.marceldev.domain.types

enum class ProductCode(
    val code: String
) {
    NONE("000"),
    PRODUCT_ONE("001"),
    PRODUCT_TWO("002"),
    PRODUCT_THREE("003"),
    PRODUCT_FOUR("004"),
    PRODUCT_FIVE("005"),
    PRODUCT_SIX("006"),
    PRODUCT_SEVEN("007"),
    PRODUCT_EIGHT("008"),
    PRODUCT_NINE("009");

    companion object {
        fun from(code: String): ProductCode {
            when (code) {
                "000" -> return ProductCode.NONE
                "001" -> return ProductCode.PRODUCT_ONE
                "002" -> return ProductCode.PRODUCT_TWO
                "003" -> return ProductCode.PRODUCT_THREE
                "004" -> return ProductCode.PRODUCT_FOUR
                "005" -> return ProductCode.PRODUCT_FIVE
                "006" -> return ProductCode.PRODUCT_SIX
                "007" -> return ProductCode.PRODUCT_SEVEN
                "008" -> return ProductCode.PRODUCT_EIGHT
                "009" -> return ProductCode.PRODUCT_NINE
                else -> throw IllegalArgumentException("Product Code is between 001 and 009")
            }
        }
    }
}