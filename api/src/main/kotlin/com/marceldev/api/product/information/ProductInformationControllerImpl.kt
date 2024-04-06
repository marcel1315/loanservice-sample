package com.marceldev.api.product.information

import com.marceldev.api.constants.CacheKey
import com.marceldev.api.response.CustomResponse
import com.marceldev.domain.types.OrganizationCode
import io.swagger.annotations.ApiOperation
import io.swagger.annotations.ApiParam
import io.swagger.annotations.Example
import io.swagger.annotations.ExampleProperty
import io.swagger.annotations.ApiResponse
import io.swagger.annotations.ApiResponses
import org.springframework.cache.CacheManager
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.*

@Controller
@RequestMapping("/fintech/v1/product")
class ProductInformationControllerImpl(
    private val productInformationServiceImpl: ProductInformationServiceImpl,
    private val redisCacheManager: CacheManager
) {
    @ApiOperation(value = "상품 정보 수신 API", notes = "금융사로부터 상품 정보를 받는 API\n" +
            "(organizationCode: 00000 ~ 00002 가능 / productCode: 001 ~ 009 가능)")
    @ApiResponses(value = [
        ApiResponse(
            code = 200, message = "OK", examples = Example(
                value = [
                    ExampleProperty(
                        value = "{\n" +
                                "   \"responseCode\":\"00\",\n" +
                                "   \"responseMessage\":\"success\"\n" +
                                "}"
                    )
                ]
            )
        )
    ])
    @PostMapping("/information")
    fun saveProductInformation(
        @ApiParam(examples = Example(
            value = [
                ExampleProperty(
                    value = "{\n" +
                            "    \"organizationCode\": \"00001\", \n" +
                            "    \"productCode\": \"001\", \n" +
                            "    \"productMaximumInterest\": 9.9, \n" +
                            "    \"productMinimumInterest\": 1.1, \n" +
                            "    \"productName\": \"상품 1\"\n" +
                            "}"
                )
            ]
        ))
        @RequestBody productInformationProductInfoInputDto: ProductInformationDto.ProductInfoInputDto
    ): ResponseEntity<CustomResponse.ResponseDto<Nothing>> {
        productInformationServiceImpl.saveProductInformation(productInformationProductInfoInputDto)
        clearOrganizationCache(productInformationProductInfoInputDto.organizationCode)

        return CustomResponse(
            HttpStatus.OK,
            "00",
            "success",
            null
        ).toResponseEntity()
    }

    @ApiOperation(value = "상품 정보 조회 API", notes = "상품 정보를 조회하는 API")
    @ApiResponses(value = [
        ApiResponse(
            code = 200, message = "OK", examples = Example(
                value = [
                    ExampleProperty(
                        value = "{\n" +
                                "  \"data\": [\n" +
                                "    {\n" +
                                "      \"organizationCode\": \"00001\", \n" +
                                "      \"productCode\": \"001\", \n" +
                                "      \"productMaximumInterest\": 9.9, \n" +
                                "      \"productMinimumInterest\": 1.1, \n" +
                                "      \"productName\": \"상품 1\"\n" +
                                "    }\n" +
                                "  ],\n" +
                                "  \"responseCode\": \"00\",\n" +
                                "  \"responseMessage\": \"success\"\n" +
                                "}"
                    )
                ]
            )
        )
    ])
    @GetMapping("/{organizationCode}")
    fun loadProductInformation(
        @ApiParam(value = "기관 코드", allowableValues = "NONE, ORGANIZATION_ONE, ORGANIZATION_TWO")
        @PathVariable organizationCode: OrganizationCode
    ): ResponseEntity<CustomResponse.ResponseDto<List<ProductInformationDto.ProductInfoOutputDto>>> {
        return CustomResponse(
            HttpStatus.OK,
            "00",
            "success",
            productInformationServiceImpl.loadProductInformation(organizationCode.code)
        ).toResponseEntity()
    }

    fun clearOrganizationCache(organizationCode: String) {
        val cache = this.redisCacheManager.getCache(CacheKey.KEY_ORGANIZATION)
        if (cache != null) {
            cache.evict(organizationCode)
        }
    }
}