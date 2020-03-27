package online.mengchen.collect_helper.domain.entity

import java.time.LocalDateTime
import javax.persistence.*

@Entity
data class BookMark(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        var id: Long? = null,
        var url: String = "",
        var createTime: LocalDateTime = LocalDateTime.now(),
        @OneToOne(cascade = [CascadeType.DETACH])
        @JoinColumn(name = "detail_id", referencedColumnName = "id")
        var bookMarkDetail: BookMarkDetail? = null,
        @ManyToOne(cascade = [CascadeType.DETACH])
        @JoinColumn(name = "category_id")
        var bookMarkCategory: BookMarkCategory = BookMarkCategory(),
        @ManyToOne(cascade = [CascadeType.DETACH])
        @JoinColumn(name = "uid")
        var user: User = User()
)