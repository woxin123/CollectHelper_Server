package online.mengchen.collect_helper.dao

import online.mengchen.collect_helper.pojo.BookMarkCategory
import online.mengchen.collect_helper.pojo.User
import org.springframework.data.jpa.repository.JpaRepository

/**
 * @Author mengchen
 * @create 2020-17-19 11:17
 */
interface BookMarkCategoryRepository: JpaRepository<BookMarkCategory, Long> {
    fun findByUser_UidAndCategoryName(uid: Long, categoryName: String): BookMarkCategory?
}