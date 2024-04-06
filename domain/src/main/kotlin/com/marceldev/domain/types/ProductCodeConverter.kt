package com.marceldev.domain.types

import javax.persistence.AttributeConverter
import javax.persistence.Converter

@Converter(autoApply = true)
class ProductCodeConverter: AttributeConverter<ProductCode, String> {
    override fun convertToDatabaseColumn(attribute: ProductCode?): String {
        if (attribute == null) {
            throw IllegalArgumentException("Product Code can't be null")
        }
        return attribute.code
    }

    override fun convertToEntityAttribute(dbData: String?): ProductCode {
        if (dbData == null) {
            throw IllegalArgumentException("Product Code can't be null")
        }
        return ProductCode.from(dbData)
    }
}