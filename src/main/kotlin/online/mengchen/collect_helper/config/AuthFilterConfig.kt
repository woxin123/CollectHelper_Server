package online.mengchen.collect_helper.config

import online.mengchen.collect_helper.common.Constant
import online.mengchen.collect_helper.filter.AuthorizationFilter
import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.InterceptorRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

/**
 * @Author mengchen
 * @create 2020-41-17 20:41
 */
@Configuration
class AuthFilterConfig: WebMvcConfigurer {

    override fun addInterceptors(registry: InterceptorRegistry) {
        registry.addInterceptor(AuthorizationFilter())
                .addPathPatterns(Constant.BookMark.BOOK_MARK + "/*")
    }

}