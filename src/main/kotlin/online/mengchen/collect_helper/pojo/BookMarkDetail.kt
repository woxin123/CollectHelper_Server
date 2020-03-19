package online.mengchen.collect_helper.pojo

import javax.persistence.*

@Entity
class BookMarkDetail(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        var id: Long = -1,
        var title: String = "",
        var summary: String = "",
        var icon: String = "",
        @OneToOne
        var bookMark: BookMark = BookMark()
)