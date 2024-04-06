package com.marceldev.api.user.information

import org.springframework.stereotype.Component
import java.util.UUID

@Component
class GenerateKey {
    fun generateUserKey() = UUID.randomUUID().toString().replace("-", "")
}