package online.mengchen.collect_helper.service

import online.mengchen.collect_helper.pojo.dto.BookMarkDTO
import online.mengchen.collect_helper.pojo.vo.BookMarkVO

/**
 * @Author mengchen
 * @create 2020-40-18 17:40
 */
interface BookMarkService {
    fun addBookMark(bookMarkDTO: BookMarkDTO): BookMarkVO?
}