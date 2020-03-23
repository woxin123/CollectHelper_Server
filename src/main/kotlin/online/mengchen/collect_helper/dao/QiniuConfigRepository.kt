package online.mengchen.collect_helper.dao

import online.mengchen.collect_helper.domain.entity.QiniuConfig
import org.springframework.data.jpa.repository.JpaRepository

interface QiniuConfigRepository : JpaRepository<QiniuConfig, Long> {
    fun findByUser_Uid(uid: Long): QiniuConfig?
}