package com.marceldev.api.exception

import com.marceldev.api.response.CustomResponse
import mu.KotlinLogging
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class GlobalExceptionHandler {

    var logger = KotlinLogging.logger { }

    @ExceptionHandler(IllegalArgumentException::class)
    fun illegalArgumentExceptionHandler(e: IllegalArgumentException) =
        CustomResponse(
            HttpStatus.BAD_REQUEST,
            "400",
            e.message ?: "Illegal Argument Exception",
            null
        ).toResponseEntity()

    @ExceptionHandler(CustomException::class)
    fun customExceptionHandler(e: CustomException) =
        CustomResponse(
            e.customErrorCode.statusCode,
            e.customErrorCode.errorCode,
            e.customErrorCode.errorMessage,
            null
        ).toResponseEntity()

    @ExceptionHandler(Exception::class)
    fun exceptionHandler(e: Exception) {
        logger.error(e.stackTraceToString())

        CustomResponse(
            HttpStatus.INTERNAL_SERVER_ERROR,
            "E100",
            "Internal Server Error",
            null
        ).toResponseEntity()
    }
}