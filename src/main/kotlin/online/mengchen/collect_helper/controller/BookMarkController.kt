package online.mengchen.collect_helper.controller

import online.mengchen.collect_helper.common.ApiResult
import online.mengchen.collect_helper.common.Constant
import online.mengchen.collect_helper.pojo.dto.BookMarkDTO
import online.mengchen.collect_helper.pojo.vo.BookMarkVO
import online.mengchen.collect_helper.service.BookMarkService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

/**
 * @Author mengchen
 * @create 2020-25-18 17:25
 */
@RestController
class BookMarkController {

    @Autowired
    lateinit var bookMarkService: BookMarkService

    @PostMapping(Constant.BookMark.BOOK_MARK)
    fun addBookMark(@RequestBody bookMark: BookMarkDTO): ResponseEntity<ApiResult<BookMarkVO>> =
            bookMarkService.addBookMark(bookMark).let {
                if (it == null) {
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ApiResult("url 不正确或无法访问"))
                } else {
                    return ResponseEntity.status(HttpStatus.CREATED).body(ApiResult("创建成功"))
                }
            }

    @GetMapping(Constant.BookMark.BOOK_MARK + "")
    fun getBookMarks() {

    }



}