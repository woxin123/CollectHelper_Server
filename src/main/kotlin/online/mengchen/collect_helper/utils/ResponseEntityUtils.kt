package online.mengchen.collect_helper.utils

import online.mengchen.collect_helper.common.ApiResult
import org.springframework.http.ResponseEntity

fun <T> createResponseEntity(result: ApiResult<T>): ResponseEntity<ApiResult<T>> {
    return ResponseEntity.status(result.status).body(result)
}