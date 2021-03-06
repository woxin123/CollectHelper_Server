package online.mengchen.collect_helper.controller

import online.mengchen.collect_helper.common.ApiResult
import online.mengchen.collect_helper.common.Constant
import online.mengchen.collect_helper.common.annotation.CurrentUser
import online.mengchen.collect_helper.domain.dto.BookMarkCategoryDTO
import online.mengchen.collect_helper.domain.dto.UserDTO
import online.mengchen.collect_helper.domain.vo.BookMarkCategoryVO
import online.mengchen.collect_helper.service.BookMarkCategoryService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

/**
 * @Author mengchen
 * @create 2020-33-19 15:33
 */
@RestController
@RequestMapping(Constant.BookMark.BOOK_MARK_CATEGORY)
class BookMarkCategoryController {

    @Autowired
    lateinit var bookMarkCategoryService: BookMarkCategoryService

    @PostMapping
    fun addBookMarkCategory(@RequestBody bookMarkCategory: BookMarkCategoryDTO, @CurrentUser userDTO: UserDTO): ResponseEntity<ApiResult<BookMarkCategoryVO>> {
        return bookMarkCategoryService.addBookMarkCategory(bookMarkCategory, userDTO).let {
            if (it == null) {
                ResponseEntity.status(HttpStatus.CONFLICT).body(ApiResult.failed(HttpStatus.CONFLICT.value(), "该用户已经存在 name = ${bookMarkCategory.categoryName} 的分类"))
            } else {
                ResponseEntity.status(HttpStatus.CREATED).body(ApiResult.success(HttpStatus.CREATED.value(), it, "创建成功"))
            }
        }
    }

    @GetMapping()
    fun getBookMarkCategories(@CurrentUser userDTO: UserDTO): ResponseEntity<ApiResult<List<BookMarkCategoryVO>>> {
        return bookMarkCategoryService.getBookMarkCategories(userDTO).let {
            ResponseEntity.ok(ApiResult.success(200, it))
        }
    }

    fun updateBookMarkCategory(@RequestBody bookMarkCategory: BookMarkCategoryDTO, @CurrentUser userDTO: UserDTO): ResponseEntity<ApiResult<BookMarkCategoryVO>> {
        return bookMarkCategoryService.updateBookMarkCategory(bookMarkCategory, userDTO).let {
            ResponseEntity.status(it.status).body(it)
        }
    }

    @DeleteMapping("/{categoryId}")
    fun deleteBookMarkCategory(@PathVariable categoryId: Long, @CurrentUser userDTO: UserDTO): ResponseEntity<ApiResult<Unit>> {
        return bookMarkCategoryService.deleteBookMarkCategory(categoryId, userDTO).let {
            ResponseEntity.status(it.status).body(it)
        }
    }

}