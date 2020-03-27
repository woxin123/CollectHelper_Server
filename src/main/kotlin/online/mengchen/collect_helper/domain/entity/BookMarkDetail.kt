package online.mengchen.collect_helper.domain.entity

import online.mengchen.collect_helper.domain.entity.BookMark
import javax.persistence.*

@Entity
class BookMarkDetail(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        var id: Long? = null,
        var title: String = "",
        var summary: String = "",
        var icon: String = "",
        @OneToOne
        var bookMark: BookMark = BookMark()
)