package com.marceldev.domain.domain

import com.marceldev.domain.aop.Encrypt
import javax.persistence.*

@Entity
@Table(name = "USER_INFO")
class UserInfo(
    @Column(name = "usr_key")
    val userKey: String,
    @Column(name = "usr_reg_num")
    @Encrypt
    var userRegistrationNumber: String,
    @Column(name = "usr_nm")
    val userName: String,
    @Column(name = "usr_icm_amt")
    val userIncomeAmount: Long
) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null
}