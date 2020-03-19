package online.mengchen.collect_helper.pojo.vo

import online.mengchen.collect_helper.pojo.BookMarkDetail
import java.time.LocalDateTime

/**
 * @Author mengchen
 * @create 2020-53-18 21:53
 */
data class BookMarkVO(
        var id: Long,
        var url: String,
        var createTime: LocalDateTime,
        var bookMarkDetail: BookMarkDetail?
)