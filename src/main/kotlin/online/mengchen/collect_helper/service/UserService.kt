package online.mengchen.collect_helper.service

import online.mengchen.collect_helper.domain.dto.RegisterUserDTO
import online.mengchen.collect_helper.domain.vo.UserVO

/**
 * @Author mengchen
 * @create 2020-52-16 22:52
 */
interface UserService {

    fun login(username: String, password: String): Boolean

    fun addUser(registerUserDTO: RegisterUserDTO): UserVO

    fun findUserById(id: Long): UserVO?

    fun findUserByUsername(username: String): UserVO?

}