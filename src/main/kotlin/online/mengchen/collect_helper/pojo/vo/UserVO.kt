package online.mengchen.collect_helper.pojo.vo

import online.mengchen.collect_helper.pojo.User
import java.io.Serializable

/**
 * @Author mengchen
 * @create 2020-00-17 14:00
 */
class UserVO (
        var userId: Long,
        var username: String,
        var phone: String = "",
        var avatar: String = ""
): Serializable {
    companion object {
        fun convert(user: User): UserVO {
            return UserVO(user.uid!!, user.username, user.phone, user.avatar)
        }
    }
}