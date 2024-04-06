package com.marceldev.api.user.information

interface UserInformationService {
    // fun saveUserInformation(userInformationDto: UserInformationDto): UserInfo
    fun saveUserMain(userInfoInputDto: UserInformationDto.UserInfoInputDto): UserInformationDto.UserInfoOutputDto
}