package online.mengchen.collect_helper.service.impl

import online.mengchen.collect_helper.common.ApiResult
import online.mengchen.collect_helper.dao.BookMarkCategoryRepository
import online.mengchen.collect_helper.dao.BookMarkRepository
import online.mengchen.collect_helper.dao.UserRepository
import online.mengchen.collect_helper.domain.entity.BookMark
import online.mengchen.collect_helper.domain.entity.BookMarkCategory
import online.mengchen.collect_helper.domain.dto.BookMarkDTO
import online.mengchen.collect_helper.domain.dto.UserDTO
import online.mengchen.collect_helper.domain.vo.BookMarkCategoryVO
import online.mengchen.collect_helper.domain.vo.BookMarkVO
import online.mengchen.collect_helper.service.BookMarkService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.http.HttpMethod
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate
import org.springframework.web.client.exchange
import java.lang.Exception


/**
 * @Author mengchen
 * @create 2020-41-18 17:41
 */
@Service
class BookMarkServiceImpl : BookMarkService {

    @Autowired
    lateinit var bookMarkRepository: BookMarkRepository

    @Autowired
    lateinit var bookMarkCategoryRepository: BookMarkCategoryRepository

    @Autowired
    lateinit var userRepository: UserRepository

    private var restTemplate: RestTemplate = RestTemplate()

    /**
     * 添加 book mark
     * 1. 在数据库中该用户没有此 book mark
     * 2. 校验是否能够访问
     *
     * return
     *  - 201 创建成功
     *  - 401 未授权
     *  - 400 url 校验未通过
     *  - 407 该用户已经收藏了指定的 url
     */
    override fun addBookMark(bookMarkDTO: BookMarkDTO, userDTO: UserDTO): ApiResult<BookMarkVO> {
        val user = userRepository.findById(userDTO.userId)
        if (!user.isPresent) {
            return ApiResult.failed(HttpStatus.UNAUTHORIZED.value(), "需要登录")
        }
        if (bookMarkRepository.existsByUser_UidAndBookMarkCategory_CategoryIdAndUrl(userDTO.userId, bookMarkDTO.categoryId, bookMarkDTO.url)) {
            return ApiResult.failed(HttpStatus.CONFLICT.value(), "该用户已经添加了 ${bookMarkDTO.url} 书签")
        }
        if (!validBookMark(bookMarkDTO)) {
            return ApiResult.failed(HttpStatus.BAD_REQUEST.value(), "url 不合法或无法访问")
        }
        val bookMarkCategory = (if (bookMarkDTO.categoryId == -1L) {
            bookMarkCategoryRepository.findByUser_UidAndCategoryName(user.get().uid!!, "未分类").let {
                if (it == null) {
                    val bookMarkCategory = BookMarkCategory(categoryName = "未分类", user = user.get())
                    bookMarkCategoryRepository.save(bookMarkCategory)
                } else {
                    it
                }
            }
        } else {
            bookMarkCategoryRepository.findById(bookMarkDTO.categoryId).let {
                if (it.isPresent) {
                    it.get()
                } else {
                    null
                }
            }
        }) ?: return ApiResult.failed(HttpStatus.INTERNAL_SERVER_ERROR.value(), "内部错误")
        val bookMark = BookMark(url = bookMarkDTO.url, createTime = bookMarkDTO.createTime,
                bookMarkCategory = bookMarkCategory, user = user.get())
        bookMarkRepository.save(bookMark)
        return BookMarkVO(bookMark.id!!, bookMark.url, bookMark.createTime, bookMark.bookMarkDetail,
                BookMarkCategoryVO(bookMark.bookMarkCategory)).run {
            ApiResult.success(HttpStatus.CREATED.value(), this, "创建成功")
        }
    }

    private fun validBookMark(bookMarkDTO: BookMarkDTO): Boolean {
        if (bookMarkDTO.url.isEmpty()) {
            return false
        }
        try {
            val resp: ResponseEntity<String> = restTemplate.exchange(bookMarkDTO.url, HttpMethod.GET, null)
            if (resp.statusCode.is2xxSuccessful) {
                return true
            }
        } catch (e: Exception) {
            return false
        }

        return false
    }

    override fun getBookMarks(pageable: Pageable): ApiResult<Page<BookMarkVO>> {
        val bookMarks = bookMarkRepository.findAll(pageable)
        return bookMarks.map {
            BookMarkVO(it.id!!, it.url, it.createTime, it.bookMarkDetail, BookMarkCategoryVO(it.bookMarkCategory))
        }.run { ApiResult.success(HttpStatus.OK.value(), this) }
    }

    override fun deleteBookMark(bookMarkId: Long): Boolean {
        return if (bookMarkRepository.findById(bookMarkId).isPresent) {
            bookMarkRepository.deleteById(bookMarkId)
            true
        } else {
            false
        }
    }

}