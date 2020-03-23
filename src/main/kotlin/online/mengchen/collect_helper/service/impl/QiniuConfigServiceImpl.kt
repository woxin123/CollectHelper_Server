package online.mengchen.collect_helper.service.impl

import online.mengchen.collect_helper.common.ApiResult
import online.mengchen.collect_helper.dao.QiniuConfigRepository
import online.mengchen.collect_helper.dao.UserRepository
import online.mengchen.collect_helper.domain.dto.QiniuConfigDTO
import online.mengchen.collect_helper.domain.dto.UserDTO
import online.mengchen.collect_helper.domain.entity.QiniuConfig
import online.mengchen.collect_helper.service.QiniuConfigService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service

@Service
class QiniuConfigServiceImpl : QiniuConfigService {

    @Autowired
    lateinit var qiniuConfigRepository: QiniuConfigRepository

    @Autowired
    lateinit var userRepository: UserRepository

    override fun addOrUpdateQiniuConfig(qiniu: QiniuConfigDTO, userDTO: UserDTO): ApiResult<QiniuConfigDTO> {
        val user = userRepository.findById(userDTO.userId)
        val qiniuConfig = qiniu.convertQiniuConfig().also {
            it.user = user.get()
        }
        qiniuConfigRepository.save(qiniuConfig)
        return ApiResult.success(HttpStatus.OK.value(),  QiniuConfigDTO(qiniuConfig), "保存成功")
    }

    override fun getQiniuConfig(userDTO: UserDTO): ApiResult<QiniuConfigDTO> {
        val qiniuConfig = qiniuConfigRepository.findByUser_Uid(userDTO.userId)
                ?: return ApiResult.failed(HttpStatus.NOT_FOUND.value(), "没有找到七牛云存储配置")
        return ApiResult.success(HttpStatus.OK.value(), QiniuConfigDTO(qiniuConfig))
    }

}