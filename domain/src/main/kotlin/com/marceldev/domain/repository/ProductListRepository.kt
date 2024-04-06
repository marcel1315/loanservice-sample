package com.marceldev.domain.repository

import com.marceldev.domain.domain.ProductList
import org.springframework.data.jpa.repository.JpaRepository

interface ProductListRepository: JpaRepository<ProductList, Long> {
}