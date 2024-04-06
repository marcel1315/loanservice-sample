package com.marceldev.domain.repository

import com.marceldev.domain.domain.UserInfo
import org.springframework.data.jpa.repository.JpaRepository

interface UserInfoRepository: JpaRepository<UserInfo, Long> {
    fun findByUserKey(userKey: String): UserInfo?
}