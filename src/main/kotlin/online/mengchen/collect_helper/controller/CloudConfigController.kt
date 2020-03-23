package online.mengchen.collect_helper.controller

import online.mengchen.collect_helper.common.ApiResult
import online.mengchen.collect_helper.common.annotation.CurrentUser
import online.mengchen.collect_helper.domain.dto.QiniuConfigDTO
import online.mengchen.collect_helper.domain.entity.QiniuConfig
import online.mengchen.collect_helper.domain.dto.UserDTO
import online.mengchen.collect_helper.service.QiniuConfigService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class CloudConfigController {

    @Autowired
    lateinit var qiniuConfigService: QiniuConfigService

    @PostMapping
    fun addOrUpdateQiniuConfig(@RequestBody config: QiniuConfigDTO, @CurrentUser userDTO: UserDTO): ResponseEntity<ApiResult<QiniuConfigDTO>>{
        return qiniuConfigService.addOrUpdateQiniuConfig(config, userDTO).let {
            ResponseEntity.status(it.status).body(it)
        }
    }

    @GetMapping
    fun getQiniuConfig(@CurrentUser userDTO: UserDTO): ResponseEntity<ApiResult<QiniuConfigDTO>> {
        return qiniuConfigService.getQiniuConfig(userDTO).let {
            ResponseEntity.status(it.status).body(it)
        }
    }

}