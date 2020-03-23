package online.mengchen.collect_helper.controller

import online.mengchen.collect_helper.common.ApiResult
import online.mengchen.collect_helper.domain.dto.RegisterUserDTO
import online.mengchen.collect_helper.domain.vo.UserVO
import online.mengchen.collect_helper.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

/**
 * @Author mengchen
 * @create 2020-09-17 00:09
 */
@RestController
class UserController {

    @Autowired
    lateinit var userService: UserService

    @PostMapping("/users")
    fun createUser(@RequestBody registerUser: RegisterUserDTO): ResponseEntity<ApiResult<UserVO>> {
        return userService.addUser(registerUser).let {
            ResponseEntity.status(HttpStatus.CREATED).body(ApiResult.success(HttpStatus.CREATED.value(), it))
        }
    }


}