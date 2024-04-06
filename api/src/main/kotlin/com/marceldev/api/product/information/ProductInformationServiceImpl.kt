package com.marceldev.api.product.information

import com.marceldev.api.constants.CacheKey
import com.marceldev.api.exception.CustomErrorCode
import com.marceldev.api.exception.CustomException
import com.marceldev.domain.repository.ProductInfoRepository
import com.marceldev.domain.repository.ProductListRepository
import org.springframework.cache.annotation.Cacheable
import org.springframework.dao.DataIntegrityViolationException
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class ProductInformationServiceImpl(
    private val productInfoRepository: ProductInfoRepository,
    private val productListRepository: ProductListRepository
): ProductInformationService {

    @Cacheable(value = [CacheKey.KEY_ORGANIZATION], key = "#organizationCode", cacheManager = "redisCacheManager")
    override fun loadProductInformation(organizationCode: String): List<ProductInformationDto.ProductInfoOutputDto> {
        val list = productInfoRepository.findProductInfosByOrganizationCode(organizationCode)
        return list.map {
            ProductInformationDto.ProductInfoOutputDto(
                it.organizationCode,
                it.productCode,
                it.productMaxInterest,
                it.productMinInterest,
                it.productName
            )
        }
    }

    @Transactional
    override fun saveProductInformation(productInformationDto: ProductInformationDto.ProductInfoInputDto): String {
        val info = productInformationDto.toProductInfoEntity()
        val list = productInformationDto.toProductListEntity()

        try {
            val productInfo = productInfoRepository.save(info)
            productListRepository.save(list)

            return productInfo.productCode
        } catch (e: DataIntegrityViolationException) {
            if (e.rootCause?.message?.contains("Duplicate entry") == true) {
                throw CustomException(CustomErrorCode.DUPLICATE_ORG_AND_PRODUCT)
            } else {
                throw CustomException(CustomErrorCode.INTERNAL_SERVER_ERROR)
            }
        }
    }
}