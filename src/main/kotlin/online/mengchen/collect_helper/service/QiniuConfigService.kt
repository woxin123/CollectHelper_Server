package online.mengchen.collect_helper.service

import online.mengchen.collect_helper.common.ApiResult
import online.mengchen.collect_helper.domain.dto.QiniuConfigDTO
import online.mengchen.collect_helper.domain.entity.QiniuConfig
import online.mengchen.collect_helper.domain.dto.UserDTO

interface QiniuConfigService {
    fun addOrUpdateQiniuConfig(qiniu: QiniuConfigDTO, userDTO: UserDTO): ApiResult<QiniuConfigDTO>
    fun getQiniuConfig(userDTO: UserDTO): ApiResult<QiniuConfigDTO>
}