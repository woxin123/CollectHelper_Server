package online.mengchen.collect_helper

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession

@SpringBootApplication
@EnableRedisHttpSession
class CollectHelperApplication

fun main(args: Array<String>) {
    runApplication<CollectHelperApplication>(*args)
}
