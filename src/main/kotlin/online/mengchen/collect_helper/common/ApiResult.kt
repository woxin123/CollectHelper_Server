package online.mengchen.collect_helper.common

/**
 * @Author mengchen
 * @create 2020-38-16 23:38
 */
class ApiResult<T>(
        var status: Int,
        /**
         * 消息提示
         */
        var message: String?,
        /**
         * 数据
         */
         var data: T? = null) {

    companion object {
        fun <T> success(status: Int, data: T? = null, message: String? = null): ApiResult<T> {
            return ApiResult(status, message, data)
        }


        fun <T> failed(status: Int, message: String): ApiResult<T> {
            return ApiResult(status, message)
        }
    }

    /**
     * 额外的信息
     */

    var extraInfo: MutableMap<String, Any>? = null

}