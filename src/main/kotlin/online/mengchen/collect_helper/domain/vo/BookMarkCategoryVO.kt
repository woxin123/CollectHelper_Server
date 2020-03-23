package online.mengchen.collect_helper.domain.vo

import online.mengchen.collect_helper.domain.entity.BookMarkCategory
import java.io.Serializable
import java.time.LocalDateTime

/**
 * @Author mengchen
 * @create 2020-58-19 15:58
 */
data class BookMarkCategoryVO(
        var categoryId: Long,
        var categoryName: String,
        var createTime: LocalDateTime,
        var updateTime: LocalDateTime
) : Serializable {
    constructor(bookMarkCategory: BookMarkCategory) : this(bookMarkCategory.categoryId!!, bookMarkCategory.categoryName,
            bookMarkCategory.createTime, bookMarkCategory.updateTime)
}