package online.mengchen.collect_helper.pojo

import online.mengchen.collect_helper.common.FileType
import java.time.LocalDateTime
import javax.persistence.*

/**
 * @Author mengchen
 * @create 2020-58-15 01:58
 */

@Entity
data class FileInfo (
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        var fid: Long = -1,
        @Column
        var name: String = "",
        @Enumerated(EnumType.ORDINAL)
        var fileType: FileType = FileType.IMAGE,
        @Column(nullable = false)
        var fileBucket: String = "",
        var createTime: LocalDateTime = LocalDateTime.now(),
        @ManyToOne
        @JoinColumn(name = "uid", referencedColumnName = "uid")
        var user: User = User()
)