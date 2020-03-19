package online.mengchen.collect_helper.pojo.dto

import javax.validation.constraints.NotBlank

/**
 * @Author mengchen
 * @create 2020-13-17 10:13
 */
data class RegisterUserDTO(
        @NotBlank
        var username: String = "",
        var avatar: String = "",
        var phone: String = "",
        @NotBlank
        var password: String
)