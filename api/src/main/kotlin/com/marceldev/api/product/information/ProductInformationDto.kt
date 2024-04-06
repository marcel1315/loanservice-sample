package com.marceldev.api.product.information

import com.marceldev.domain.domain.ProductInfo
import com.marceldev.domain.domain.ProductList
import com.marceldev.domain.types.OrganizationCode
import com.marceldev.domain.types.ProductCode
import io.swagger.annotations.ApiModelProperty

class ProductInformationDto {
    data class ProductInfoInputDto(
        @ApiModelProperty(example = "00001")
        val organizationCode: String,
        @ApiModelProperty(example = "001", notes = "001 ~ 009 가능")
        val productCode: String,
        @ApiModelProperty(example = "9.9")
        val productMaximumInterest: Double,
        @ApiModelProperty(example = "1.1")
        val productMinimumInterest: Double,
        @ApiModelProperty(example = "상품 1")
        val productName: String
    ) {
        fun toProductInfoEntity(): ProductInfo {
            return ProductInfo(
                organizationCode = organizationCode,
                productCode = productCode,
                productName = productName,
                productMaxInterest = productMaximumInterest,
                productMinInterest = productMinimumInterest
            )
        }
        fun toProductListEntity(): ProductList {
            return ProductList(
                organizationCode = OrganizationCode.from(organizationCode),
                productCode = ProductCode.from(productCode)
            )
        }
    }

    data class ProductInfoOutputDto(
        val organizationCode: String,
        val productCode: String,
        val productMaximumInterest: Double,
        val productMinimumInterest: Double,
        val productName: String
    )
}