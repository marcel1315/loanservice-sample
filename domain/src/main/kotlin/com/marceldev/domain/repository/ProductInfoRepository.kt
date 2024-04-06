package com.marceldev.domain.repository

import com.marceldev.domain.domain.ProductInfo
import org.springframework.data.jpa.repository.JpaRepository

interface ProductInfoRepository: JpaRepository<ProductInfo, Long> {
    fun findProductInfosByOrganizationCode(code: String): List<ProductInfo>
}