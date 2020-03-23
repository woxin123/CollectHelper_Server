package online.mengchen.collect_helper.domain.dto

import online.mengchen.collect_helper.domain.entity.QiniuConfig
import javax.persistence.Column

data class QiniuConfigDTO(
        var qiniuId: Long = 0,
        var bucket: String,
        var imageBucket: String? = null,
        var documentBucket: String? = null,
        var musicBucket: String? = null,
        var videoBucket: String? = null,
        var imagePath: String = "image",
        var documentPath: String = "document",
        var musicPath: String = "music",
        var videoPath: String = "video",
        var qiniuAK: String,
        var qiniuSK: String,
        var storeRegion: String? = null
) {
    constructor(qiniu: QiniuConfig) : this(qiniu.qiniuId, qiniu.bucket, qiniu.imageBucket, qiniu.documentBucket,
            qiniu.musicBucket, qiniu.videoBucket, qiniu.imagePath, qiniu.documentPath, qiniu.musicPath, qiniu.videoPath,
            qiniu.qiniuAK!!, qiniu.qiniuSK!!, qiniu.storeRegion)

    fun convertQiniuConfig(): QiniuConfig {
        return QiniuConfig().also {
            it.qiniuId = qiniuId
            it.bucket = bucket
            it.imageBucket = imageBucket
            it.documentBucket = documentBucket
            it.musicBucket = musicBucket
            it.videoBucket = videoBucket
            it.imageBucket = imagePath
            it.documentPath = documentPath
            it.musicPath = musicPath
            it.videoPath = videoPath
            it.qiniuAK = qiniuAK
            it.qiniuSK = qiniuSK
            it.storeRegion = storeRegion
        }

    }
}