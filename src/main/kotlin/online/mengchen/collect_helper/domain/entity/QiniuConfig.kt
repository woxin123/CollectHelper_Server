package online.mengchen.collect_helper.domain.entity

import javax.persistence.*

@Entity
data class QiniuConfig(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        var qiniuId: Long = 0,

        @Column(name = "bucket", nullable = false)
        var bucket: String = "",

        @Column(nullable = true)
        var imageBucket: String? = null,
        @Column(nullable = true)
        var documentBucket: String? = null,
        @Column(nullable = true)
        var musicBucket: String? = null,
        @Column(nullable = true)
        var videoBucket: String? = null,

        var imagePath: String = "image",
        var documentPath: String = "document",
        var musicPath: String = "music",
        var videoPath: String = "video",
        var qiniuAK: String? = null,
        var qiniuSK: String? = null,
        var storeRegion: String? = null,
        @OneToOne(cascade = [CascadeType.ALL])
        @JoinColumn(name = "uid", referencedColumnName = "uid")
        var user: User? = null
)
