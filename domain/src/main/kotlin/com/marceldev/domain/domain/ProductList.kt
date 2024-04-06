package com.marceldev.domain.domain

import com.marceldev.domain.types.OrganizationCode
import com.marceldev.domain.types.ProductCode
import javax.persistence.*

@Entity
@Table(name = "PRODUCT_LIST")
class ProductList(
    @Column(name = "org_cd")
    val organizationCode: OrganizationCode,
    @Column(name = "prod_cd")
    val productCode: ProductCode
) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null
}