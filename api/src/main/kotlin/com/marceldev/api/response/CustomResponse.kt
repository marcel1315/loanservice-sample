package com.marceldev.api.response

import com.fasterxml.jackson.annotation.JsonInclude
import io.swagger.annotations.ApiModelProperty
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity

class CustomResponse<T>(
    private val httpStatus: HttpStatus,
    private val responseCode: String,
    private val responseMessage: String,
    private val data: T?
) {
    fun toResponseEntity(): ResponseEntity<ResponseDto<T>> {
        val responseDto = ResponseDto(
            responseCode = responseCode,
            responseMessage = responseMessage,
            data = data
        )

        return ResponseEntity
            .status(httpStatus)
            .body(responseDto)
    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    data class ResponseDto<T>(
        @ApiModelProperty(example = "00")
        val responseCode: String,
        @ApiModelProperty(example = "success")
        val responseMessage: String,
        val data: T?
    )
}