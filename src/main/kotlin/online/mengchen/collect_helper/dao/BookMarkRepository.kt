package online.mengchen.collect_helper.dao

import online.mengchen.collect_helper.domain.entity.BookMark
import org.springframework.data.jpa.repository.JpaRepository

/**
 * @Author mengchen
 * @create 2020-29-18 17:29
 */
interface BookMarkRepository: JpaRepository<BookMark, Long> {
    fun countByBookMarkCategory_CategoryId(categoryId: Long): Int
}