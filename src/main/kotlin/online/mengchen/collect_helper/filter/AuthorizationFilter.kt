package online.mengchen.collect_helper.filter

import online.mengchen.collect_helper.common.Constant
import org.springframework.http.HttpMethod
import org.springframework.web.servlet.HandlerInterceptor
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

/**
 * @Author mengchen
 * @create 2020-01-16 23:01
 */
//@Component
class AuthorizationFilter : HandlerInterceptor{

    override fun preHandle(request: HttpServletRequest, response: HttpServletResponse, handler: Any): Boolean {
        val path = request.servletPath
        if (path == "/users" && request.method == HttpMethod.POST.name) {
            return true
        }
        val session = request.session
        if (session.getAttribute(Constant.Session.USER) == null) {
            request.getRequestDispatcher(Constant.Session.SESSION_ERROR).forward(request, response)
            return false
        }
        return true
    }
}