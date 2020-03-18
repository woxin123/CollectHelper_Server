package online.mengchen.collect_helper.service

import online.mengchen.collect_helper.pojo.dto.UserDTO
import online.mengchen.collect_helper.pojo.vo.UserVO

/**
 * @Author mengchen
 * @create 2020-52-16 22:52
 */
interface UserService {

    fun login(username: String, password: String): Boolean

    fun addUser(userDTO: UserDTO): UserVO

    fun findUserById(id: Long): UserVO?

    fun findUserByUsername(username: String): UserVO?

}