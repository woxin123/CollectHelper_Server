package online.mengchen.collect_helper.pojo.dto

import org.hibernate.validator.constraints.Length
import java.time.LocalDateTime
import javax.validation.constraints.NotBlank

/**
 * @Author mengchen
 * @create 2020-30-18 17:30
 */
data class BookMarkDTO (
        @NotBlank
        @Length
        val url: String,
        var categoryId: Long = -1,
        val createTime: LocalDateTime = LocalDateTime.now()
)