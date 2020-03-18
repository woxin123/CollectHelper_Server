package online.mengchen.collect_helper.service.impl

import online.mengchen.collect_helper.dao.UserRepository
import online.mengchen.collect_helper.pojo.User
import online.mengchen.collect_helper.pojo.dto.UserDTO
import online.mengchen.collect_helper.pojo.vo.UserVO
import online.mengchen.collect_helper.service.UserService
import online.mengchen.collect_helper.utils.EncryptUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

/**
 * @Author mengchen
 * @create 2020-53-16 22:53
 */
@Service
class UserServiceImpl : UserService {

    @Autowired
    lateinit var userRepository: UserRepository

    override fun login(username: String, password: String): Boolean {
        val user = userRepository.findByUsername(username)
        if (user != null) {
            if (EncryptUtils.validPassword(password, user.password)) {
                return true
            }
        }
        return false
    }

    override fun addUser(userDTO: UserDTO): UserVO {
        val user = User()
        user.username = userDTO.username
        // 对密码进行加密
        user.password = EncryptUtils.encryptUserSHA256(userDTO.password)
        user.avatar = userDTO.avatar
        userRepository.save(user)
        return UserVO.convert(user)
    }

    override fun findUserById(id: Long): UserVO? {
        return userRepository.findById(id).let {
            if (it.isPresent) {
                null
            } else {
                UserVO.convert(it.get())
            }
        }
    }

    override fun findUserByUsername(username: String): UserVO? {
        return userRepository.findByUsername(username).let {
            if (it == null) {
                null
            } else {
                UserVO.convert(it)
            }
        }
    }

}