package online.mengchen.collect_helper.service

import online.mengchen.collect_helper.common.ApiResult
import online.mengchen.collect_helper.domain.dto.BookMarkCategoryDTO
import online.mengchen.collect_helper.domain.dto.UserDTO
import online.mengchen.collect_helper.domain.vo.BookMarkCategoryVO

/**
 * @Author mengchen
 * @create 2020-09-19 16:09
 */
interface BookMarkCategoryService {
    fun addBookMarkCategory(bookMarkCategoryDTO: BookMarkCategoryDTO, userDTO: UserDTO): BookMarkCategoryVO?
    fun getBookMarkCategories(userDTO: UserDTO): List<BookMarkCategoryVO>
    fun updateBookMarkCategory(bookMarkCategoryDTO: BookMarkCategoryDTO, userDTO: UserDTO): ApiResult<BookMarkCategoryVO>
    fun deleteBookMarkCategory(categoryId: Long, userDTO: UserDTO): ApiResult<Unit>
}