package online.mengchen.collect_helper.controller

import online.mengchen.collect_helper.common.ApiResult
import online.mengchen.collect_helper.common.Constant
import online.mengchen.collect_helper.common.annotation.CurrentUser
import online.mengchen.collect_helper.domain.dto.BookMarkDTO
import online.mengchen.collect_helper.domain.dto.UserDTO
import online.mengchen.collect_helper.domain.vo.BookMarkVO
import online.mengchen.collect_helper.service.BookMarkService
import online.mengchen.collect_helper.utils.createResponseEntity
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.web.PageableDefault
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

/**
 * @Author mengchen
 * @create 2020-25-18 17:25
 */
@RestController
class BookMarkController {

    @Autowired
    lateinit var bookMarkService: BookMarkService

    @PostMapping(Constant.BookMark.BOOK_MARK)
    fun addBookMark(@RequestBody bookMark: BookMarkDTO, @CurrentUser user: UserDTO): ResponseEntity<ApiResult<BookMarkVO>> =
            bookMarkService.addBookMark(bookMark, user).let {
                createResponseEntity(it)
            }

    @GetMapping(Constant.BookMark.BOOK_MARK)
    fun getBookMarks(@PageableDefault(page = 0, size = 10) page: Pageable): ApiResult<Page<BookMarkVO>> {
        return bookMarkService.getBookMarks(page)
    }

    @DeleteMapping(Constant.BookMark.BOOK_MARK + "/{bookMarkId}")
    fun deleteBookMark(@PathVariable bookMarkId: Long): ResponseEntity<ApiResult<Unit>> {
        return bookMarkService.deleteBookMark(bookMarkId).let {
            if (it) {
                ResponseEntity.status(HttpStatus.NO_CONTENT).build()
            } else {
                ResponseEntity.status(HttpStatus.NOT_FOUND).body(ApiResult.failed(HttpStatus.NOT_FOUND.value(),"没有找过 id = $bookMarkId 的书签"))
            }
        }
    }

}