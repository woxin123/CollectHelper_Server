package online.mengchen.collect_helper.controller

import online.mengchen.collect_helper.common.ApiResult
import online.mengchen.collect_helper.common.Constant
import online.mengchen.collect_helper.pojo.dto.LoginUser
import online.mengchen.collect_helper.pojo.vo.UserVO
import online.mengchen.collect_helper.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.servlet.http.HttpSession

/**
 * 这里的 session 表示的一次会话
 * @Author mengchen
 * @create 2020-02-16 23:02
 */
@RestController
class SessionController {

    @Autowired
    lateinit var userService: UserService

    @PostMapping("/sessions")
    fun createSession(@RequestBody user: LoginUser, session: HttpSession): ResponseEntity<ApiResult<UserVO>> {
        return if (userService.login(user.username, user.password)) {
            val userVO: UserVO? = userService.findUserByUsername(user.username)
            session.setAttribute(Constant.SESSION.USER, userVO)
            ResponseEntity.status(HttpStatus.CREATED).body(ApiResult.success(userVO))
        } else {
            ResponseEntity.status(HttpStatus.NOT_FOUND).body(ApiResult("用户名或密码错误"))
        }
    }

    @DeleteMapping("/sessions")
    fun deleteMapping(session: HttpSession): ResponseEntity<Unit> {
        session.removeAttribute(Constant.SESSION.USER)
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build()
    }

    @RequestMapping("/sessions/error")
    fun error(): ResponseEntity<ApiResult<Unit>> {
        return ResponseEntity.ok(ApiResult.failed("没有访问权限"))
    }

}