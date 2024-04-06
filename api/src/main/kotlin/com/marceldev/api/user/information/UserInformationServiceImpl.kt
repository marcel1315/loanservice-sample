package com.marceldev.api.user.information

import com.marceldev.api.exception.CustomErrorCode
import com.marceldev.api.exception.CustomException
import com.marceldev.domain.repository.UserInfoRepository
import org.springframework.dao.DataIntegrityViolationException
import org.springframework.stereotype.Service

@Service
class UserInformationServiceImpl(
    private val generateKey: GenerateKey,
    private val userInfoRepository: UserInfoRepository
): UserInformationService {
    override fun saveUserMain(userInfoInputDto: UserInformationDto.UserInfoInputDto): UserInformationDto.UserInfoOutputDto {
        var userKey = generateKey.generateUserKey()
        val userInfo = userInfoInputDto.toEntity(userKey)

        try {
            userInfoRepository.save(userInfo)
        } catch (e: DataIntegrityViolationException) {
            if (e.rootCause?.message?.contains("Duplicate entry") == true) {
                throw CustomException(CustomErrorCode.DUPLICATE_USER)
            } else {
                throw CustomException(CustomErrorCode.INTERNAL_SERVER_ERROR)
            }
        }

        return UserInformationDto.UserInfoOutputDto(userKey)
    }

    fun loadUserPrivateInfo(userKey: String): UserPrivateInfoDto.UserPrivateInfoOutputDto {
        val userInfo = userInfoRepository.findByUserKey(userKey)
            ?:throw CustomException(CustomErrorCode.RESULT_NOT_FOUND)
        return UserPrivateInfoDto.UserPrivateInfoOutputDto(
            userKey = userInfo.userKey,
            userRegistrationNumber = userInfo.userRegistrationNumber
        )
    }
}