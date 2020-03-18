package online.mengchen.collect_helper.pojo.dto

import org.hibernate.validator.constraints.Length
import javax.validation.constraints.NotBlank

/**
 * @Author mengchen
 * @create 2020-13-16 23:13
 */
data class LoginUser (
        @NotBlank
        @Length(min =4, max = 11)
        val username: String,
        @NotBlank
        @Length(min = 6, max = 16)
        val password: String
)