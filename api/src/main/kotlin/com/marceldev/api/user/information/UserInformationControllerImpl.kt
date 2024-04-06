package com.marceldev.api.user.information

import com.marceldev.api.response.CustomResponse
import io.swagger.annotations.*
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/fintech/v1/user")
class UserInformationControllerImpl(
    private val userInformationServiceImpl: UserInformationServiceImpl
) {
    @ApiOperation(value = "유저 정보 수신 API", notes = "유저 정보를 받는 API")
    @ApiResponses(value = [
        ApiResponse(code = 200, message = "OK", examples = Example(
            value = [
                ExampleProperty(
                    value = "{\n" +
                            "  \"data\": {\n" +
                            "    \"userKey\": \"2cbd0d0ee3bc460e94979e451210bedb\"\n" +
                            "  },\n" +
                            "  \"responseCode\": \"00\",\n" +
                            "  \"responseMessage\": \"success\"\n" +
                            "}"
                    )
                ]
            )
        )
    ])
    @PostMapping("/information")
    fun saveUserInformation(
        @ApiParam(examples = Example(value = [
            ExampleProperty(
                value = "{\n" +
                        "  \"userIncomeAmount\": 100000,\n" +
                        "  \"userName\": \"백엔드\",\n" +
                        "  \"userRegistrationNumber\": \"900101-1234567\"\n" +
                        "}\n"
                    )
                ]
            )
        )
        @RequestBody userInfoInputDto: UserInformationDto.UserInfoInputDto
    ): ResponseEntity<CustomResponse.ResponseDto<UserInformationDto.UserInfoOutputDto>> {
        return CustomResponse(
            HttpStatus.OK,
            "00",
            "success",
            userInformationServiceImpl.saveUserMain(userInfoInputDto)
        ).toResponseEntity()
    }

    @ApiOperation(value = "유저 정보 조회 API", notes = "유저 정보를 조회하는 API")
    @ApiResponses(value = [
        ApiResponse(code = 200, message = "OK", examples = Example(
            value = [
                ExampleProperty(
                    value = "{\n" +
                            "  \"data\": {\n" +
                            "    \"userKey\": \"2cbd0d0ee3bc460e94979e451210bedb\",\n" +
                            "    \"userRegistrationNumber\": \"900101-1234567\"\n" +
                            "  },\n" +
                            "  \"responseCode\": \"00\",\n" +
                            "  \"responseMessage\": \"success\"\n" +
                            "}\n"
                )
            ]
        )
        )
    ])
    @GetMapping("/private-info/{userKey}")
    fun userPrivateInfo(
        @PathVariable userKey: String
    ): ResponseEntity<CustomResponse.ResponseDto<UserPrivateInfoDto.UserPrivateInfoOutputDto>> {
        return CustomResponse(
            HttpStatus.OK,
            "00",
            "success",
            userInformationServiceImpl.loadUserPrivateInfo(userKey)
        ).toResponseEntity()
    }
}