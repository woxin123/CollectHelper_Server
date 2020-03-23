package online.mengchen.collect_helper.config

import online.mengchen.collect_helper.common.Constant
import online.mengchen.collect_helper.common.annotation.CurrentUser
import online.mengchen.collect_helper.domain.dto.UserDTO
import org.springframework.core.MethodParameter
import org.springframework.stereotype.Component
import org.springframework.web.bind.support.WebDataBinderFactory
import org.springframework.web.context.request.NativeWebRequest
import org.springframework.web.method.support.HandlerMethodArgumentResolver
import org.springframework.web.method.support.ModelAndViewContainer
import javax.servlet.http.HttpServletRequest

/**
 * @Author mengchen
 * @create 2020-06-19 13:06
 */
@Component
class UserArgumentResolver: HandlerMethodArgumentResolver {

    override fun supportsParameter(parameter: MethodParameter): Boolean {
        return parameter.hasParameterAnnotation(CurrentUser::class.java)
    }

    override fun resolveArgument(parameter: MethodParameter, mavContainer: ModelAndViewContainer?, webRequest: NativeWebRequest, binderFactory: WebDataBinderFactory?): UserDTO? {
        val servletRequest = webRequest.getNativeRequest(HttpServletRequest::class.java)
        val session = servletRequest?.session
        return session?.getAttribute(Constant.Session.USER) as UserDTO
    }


}