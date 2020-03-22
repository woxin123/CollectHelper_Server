package online.mengchen.collect_helper.dao

import online.mengchen.collect_helper.pojo.BookMarkCategory
import online.mengchen.collect_helper.pojo.User
import online.mengchen.collect_helper.pojo.vo.BookMarkCategoryVO
import org.springframework.data.jpa.repository.JpaRepository

/**
 * @Author mengchen
 * @create 2020-17-19 11:17
 */
interface BookMarkCategoryRepository: JpaRepository<BookMarkCategory, Long> {
    fun findByUser_UidAndCategoryName(uid: Long, categoryName: String): BookMarkCategory?
    fun existsByUser_UidAndCategoryName(uid: Long, categoryName: String): Boolean
    fun existsByUser_UidAndCategoryId(uid: Long, categoryId: Long): Boolean
    fun findAllByUser_Uid(uid: Long): List<BookMarkCategoryVO>
//    fun deleteByUser_UidAAndCategoryId(uid: Long, categoryId: Long)
}