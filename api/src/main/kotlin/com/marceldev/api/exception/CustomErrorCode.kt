package com.marceldev.api.exception

import org.springframework.http.HttpStatus

enum class CustomErrorCode(
    val statusCode: HttpStatus,
    val errorCode: String,
    val errorMessage: String
) {
    RESULT_NOT_FOUND(HttpStatus.BAD_REQUEST, "E001", errorMessage = "Result not found"),
    DUPLICATE_ORG_AND_PRODUCT(HttpStatus.BAD_REQUEST, "E002", errorMessage = "Check Organization Code and Product Code already exists"),
    DUPLICATE_USER(HttpStatus.BAD_REQUEST, "E003", errorMessage = "A user with same Registration Number exists"),
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "E100", errorMessage = "Internal Server Error")
}