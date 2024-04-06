package com.marceldev.domain.domain

import javax.persistence.*

@Entity
@Table(name = "PRODUCT_INFO")
class ProductInfo(
    @Column(name = "org_cd")
    val organizationCode: String,
    @Column(name = "prod_cd")
    val productCode: String,
    @Column(name = "prod_nm")
    val productName: String,
    @Column(name = "prod_min_intr")
    val productMinInterest: Double,
    @Column(name = "prod_max_intr")
    val productMaxInterest: Double
) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null
}