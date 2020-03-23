package online.mengchen.collect_helper.service

import online.mengchen.collect_helper.domain.dto.BookMarkDTO
import online.mengchen.collect_helper.domain.dto.UserDTO
import online.mengchen.collect_helper.domain.vo.BookMarkVO
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

/**
 * @Author mengchen
 * @create 2020-40-18 17:40
 */
interface BookMarkService {
    fun addBookMark(bookMarkDTO: BookMarkDTO, userDTO: UserDTO): BookMarkVO?
    fun getBookMarks(pageable: Pageable): Page<BookMarkVO>
    fun deleteBookMark(bookMarkId: Long): Boolean
}