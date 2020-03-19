package online.mengchen.collect_helper.pojo

import java.time.LocalDateTime
import javax.persistence.*

@Entity
data class BookMark(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        var id: Long = -1,
        var url: String = "",
        var createTime: LocalDateTime = LocalDateTime.now(),
        @OneToOne(cascade = [CascadeType.ALL])
        @JoinColumn(name = "detail_id", referencedColumnName = "id")
        var bookMarkDetail: BookMarkDetail? = null,
        @ManyToOne(cascade = [CascadeType.ALL])
        @JoinColumn(name = "category_id")
        var bookMarkCategory: BookMarkCategory = BookMarkCategory(),
        @ManyToOne(cascade = [CascadeType.ALL])
        @JoinColumn(name = "uid")
        var user: User = User()
)