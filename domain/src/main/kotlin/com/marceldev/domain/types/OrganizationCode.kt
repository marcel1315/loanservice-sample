package com.marceldev.domain.types

enum class OrganizationCode(
    val code: String
) {
    NONE("00000"),
    ORGANIZATION_ONE("00001"),
    ORGANIZATION_TWO("00002");

    companion object {
        fun from(code: String): OrganizationCode {
            when (code) {
                "00000" -> return NONE
                "00001" -> return ORGANIZATION_ONE
                "00002" -> return ORGANIZATION_TWO
                else -> throw IllegalArgumentException("Organization Code is between 00000 and 00002")
            }
        }
    }
}