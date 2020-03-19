package online.mengchen.collect_helper.pojo.dto

import online.mengchen.collect_helper.pojo.User
import java.io.Serializable

/**
 * @Author mengchen
 * @create 2020-00-17 14:00
 */
class UserDTO (
        var userId: Long,
        var username: String,
        var phone: String? = null,
        var avatar: String? = null
): Serializable {
    companion object {
        fun convert(user: User): UserDTO {
            return UserDTO(user.uid!!, user.username, user.phone, user.avatar)
        }
    }
}