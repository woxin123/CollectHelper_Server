package online.mengchen.collect_helper.controller

import online.mengchen.collect_helper.common.ApiResult
import online.mengchen.collect_helper.common.Constant.Session
import online.mengchen.collect_helper.domain.dto.LoginUser
import online.mengchen.collect_helper.domain.dto.UserDTO
import online.mengchen.collect_helper.domain.vo.UserVO
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

    @PostMapping(Session.SESSIONS)
    fun createSession(@RequestBody user: LoginUser, session: HttpSession): ResponseEntity<ApiResult<UserVO>> {
        return if (userService.login(user.username, user.password)) {
            val userVO: UserVO = userService.findUserByUsername(user.username)!!
            session.setAttribute(Session.USER, UserDTO(userVO.userId, userVO.username, userVO.phone, userVO.avatar))
            ResponseEntity.status(HttpStatus.CREATED).body(ApiResult.success(HttpStatus.CREATED.value(), userVO))
        } else {
            ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ApiResult(HttpStatus.BAD_REQUEST.value(), "用户名或密码错误"))
        }
    }

    @DeleteMapping(Session.SESSIONS)
    fun deleteMapping(session: HttpSession): ResponseEntity<Unit> {
        session.removeAttribute(Session.USER)
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build()
    }

    @RequestMapping(Session.SESSION_ERROR)
    fun error(): ResponseEntity<ApiResult<Unit>> {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(ApiResult.failed(HttpStatus.UNAUTHORIZED.value(),"没有访问权限"))
    }

}