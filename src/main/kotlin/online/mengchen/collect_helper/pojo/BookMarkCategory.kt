package online.mengchen.collect_helper.pojo

import java.time.LocalDateTime
import javax.annotation.Generated
import javax.persistence.*

/**
 * @Author mengchen
 * @create 2020-41-19 10:41
 */
@Entity
data class BookMarkCategory(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        var categoryId: Long? = null,
        var categoryName: String = "",
        @ManyToOne
        @JoinColumn(name = "uid")
        var user: User = User(),
        var createTime: LocalDateTime = LocalDateTime.now(),
        var updateTime: LocalDateTime = LocalDateTime.now()

)