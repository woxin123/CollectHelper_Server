package online.mengchen.collect_helper.dao

import online.mengchen.collect_helper.domain.entity.User
import org.springframework.data.jpa.repository.JpaRepository

/**
 * @Author mengchen
 * @create 2020-25-16 23:25
 */
interface UserRepository : JpaRepository<User, Long> {
    fun findByUsername(username: String): User?
}