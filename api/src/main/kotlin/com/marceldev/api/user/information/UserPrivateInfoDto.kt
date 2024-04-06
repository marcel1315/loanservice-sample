package com.marceldev.api.user.information

class UserPrivateInfoDto {
    data class UserPrivateInfoOutputDto(
        val userKey: String,
        val userRegistrationNumber: String
    )
}