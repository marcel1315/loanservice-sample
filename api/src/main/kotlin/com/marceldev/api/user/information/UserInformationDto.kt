package com.marceldev.api.user.information

import com.marceldev.domain.domain.UserInfo
import io.swagger.annotations.ApiModelProperty

class UserInformationDto {
    data class UserInfoInputDto(
        @ApiModelProperty(example = "900101-1234567")
        var userRegistrationNumber: String,
        @ApiModelProperty(example = "백엔드")
        val userName: String,
        @ApiModelProperty(example = "100000")
        val userIncomeAmount: Long
    ) {
        fun toEntity(userKey: String): UserInfo =
            UserInfo(
                userKey, userRegistrationNumber, userName, userIncomeAmount
            )
    }

    data class UserInfoOutputDto(
        val userKey: String
    )
}