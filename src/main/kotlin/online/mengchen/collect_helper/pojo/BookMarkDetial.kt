package online.mengchen.collect_helper.pojo

import javax.persistence.*

@Entity
class BookMarkDetial(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        var id: Long = -1,
        var title: String = "",
        var summary: String = "",
        var icon: String = "",
        @OneToOne
        @JoinColumn(name = "bid", referencedColumnName = "id")
        var bookMark: BookMark = BookMark()
)