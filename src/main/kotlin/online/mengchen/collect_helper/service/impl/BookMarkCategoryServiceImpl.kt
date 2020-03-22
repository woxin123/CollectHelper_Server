package online.mengchen.collect_helper.service.impl

import online.mengchen.collect_helper.common.ApiResult
import online.mengchen.collect_helper.dao.BookMarkCategoryRepository
import online.mengchen.collect_helper.dao.BookMarkRepository
import online.mengchen.collect_helper.dao.UserRepository
import online.mengchen.collect_helper.pojo.BookMarkCategory
import online.mengchen.collect_helper.pojo.dto.BookMarkCategoryDTO
import online.mengchen.collect_helper.pojo.dto.UserDTO
import online.mengchen.collect_helper.pojo.vo.BookMarkCategoryVO
import online.mengchen.collect_helper.service.BookMarkCategoryService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import java.time.LocalDateTime

/**
 * @Author mengchen
 * @create 2020-16-19 16:16
 */
@Service
class BookMarkCategoryServiceImpl: BookMarkCategoryService {

    @Autowired
    lateinit var bookMarkCategoryRepository: BookMarkCategoryRepository

    @Autowired
    lateinit var userRepository: UserRepository

    @Autowired
    lateinit var bookMarkRepository: BookMarkRepository

    override fun addBookMarkCategory(bookMarkCategoryDTO: BookMarkCategoryDTO, userDTO: UserDTO): BookMarkCategoryVO? {
        if (bookMarkCategoryRepository.existsByUser_UidAndCategoryName(userDTO.userId, bookMarkCategoryDTO.categoryName)) {
            return null
        }
        val bookMarkCategory = BookMarkCategory(categoryName = bookMarkCategoryDTO.categoryName, user = userRepository.findById(userDTO.userId).get())
        bookMarkCategoryRepository.save(bookMarkCategory)
        return BookMarkCategoryVO(bookMarkCategory)
    }

    override fun getBookMarkCategories(userDTO: UserDTO): List<BookMarkCategoryVO> {
        return bookMarkCategoryRepository.findAllByUser_Uid(userDTO.userId).let {
            if (it.isEmpty()) {
                val user = userRepository.findById(userDTO.userId)
                if (user.isPresent) {
                    val bookMarkCategory = BookMarkCategory(categoryName = "未分类", user = user.get())
                    bookMarkCategoryRepository.save(bookMarkCategory)
                    listOf(BookMarkCategoryVO(bookMarkCategory))
                } else {
                    it
                }
            } else {
                it
            }
        }
    }

    override fun updateBookMarkCategory(bookMarkCategoryDTO: BookMarkCategoryDTO, userDTO: UserDTO): ApiResult<BookMarkCategoryVO> {
        if (bookMarkCategoryRepository.existsByUser_UidAndCategoryName(userDTO.userId, bookMarkCategoryDTO.categoryName)) {
            return ApiResult(HttpStatus.CONFLICT.value(), "该用户已经存在 ${bookMarkCategoryDTO.categoryName} 的分类")
        }
        val bookMarkCategory = bookMarkCategoryRepository.findById(bookMarkCategoryDTO.categoryId)
        if (!bookMarkCategory.isPresent) {
            return ApiResult(HttpStatus.NOT_FOUND.value(), "没有发现 id = ${bookMarkCategoryDTO.categoryId} 的分类")
        }
        bookMarkCategory.get().let {
            it.categoryName = bookMarkCategoryDTO.categoryName
            it.updateTime = LocalDateTime.now()
        }
        bookMarkCategoryRepository.save(bookMarkCategory.get())
        return ApiResult.success(HttpStatus.OK.value(), BookMarkCategoryVO(bookMarkCategory.get()), "修改成功")
    }

    override fun deleteBookMarkCategory(categoryId: Long, userDTO: UserDTO): ApiResult<Unit> {
        if (!bookMarkCategoryRepository.existsByUser_UidAndCategoryId(userDTO.userId, categoryId)) {
            return ApiResult.failed(HttpStatus.NOT_FOUND.value(), "没有找到该用户 $categoryId 的分类")
        }
        if (bookMarkRepository.countByBookMarkCategory_CategoryId(categoryId) != 0) {
            return ApiResult.failed(HttpStatus.BAD_REQUEST.value(), "分类 $categoryId 不为空，无法删除")
        }
        bookMarkCategoryRepository.deleteById(categoryId)
        return ApiResult.success(HttpStatus.OK.value(), null, "删除成功")
    }

}