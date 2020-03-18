package online.mengchen.collect_helper.common

/**
 * @Author mengchen
 * @create 2020-38-16 23:38
 */
class ApiResult<T>(
        /**
         * 消息提示
         */
        private var message: String?,
        /**
         * 数据
         */
        private var data: T? = null) {

    companion object {
        fun <T> success(data: T? = null, message: String? = null): ApiResult<T> {
            return ApiResult(message, data)
        }


        fun <T> failed(message: String): ApiResult<T> {
            return ApiResult(message)
        }
    }

    /**
     * 额外的信息
     */

    var extraInfo: MutableMap<String, Any>? = null

}