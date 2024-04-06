package com.marceldev.domain.types

import javax.persistence.AttributeConverter
import javax.persistence.Converter

@Converter(autoApply = true)
class OrganizationCodeConverter: AttributeConverter<OrganizationCode, String> {
    override fun convertToDatabaseColumn(attribute: OrganizationCode?): String {
        if (attribute == null) {
            throw IllegalArgumentException("Organization can't be null")
        }
        return attribute.code
    }

    override fun convertToEntityAttribute(dbData: String?): OrganizationCode {
        if (dbData == null) {
            throw IllegalArgumentException("Organization Code can't be null")
        }
        return OrganizationCode.from(dbData)
    }
}