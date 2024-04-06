package com.marceldev.api.product.information

interface ProductInformationService {
    fun loadProductInformation(organizationCode: String): List<ProductInformationDto.ProductInfoOutputDto>
    fun saveProductInformation(productInformationDto: ProductInformationDto.ProductInfoInputDto): String
}