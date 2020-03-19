package online.mengchen.collect_helper.service.impl

import online.mengchen.collect_helper.dao.BookMarkCategoryRepository
import online.mengchen.collect_helper.dao.BookMarkRepository
import online.mengchen.collect_helper.dao.UserRepository
import online.mengchen.collect_helper.pojo.BookMark
import online.mengchen.collect_helper.pojo.BookMarkCategory
import online.mengchen.collect_helper.pojo.dto.BookMarkDTO
import online.mengchen.collect_helper.pojo.dto.UserDTO
import online.mengchen.collect_helper.pojo.vo.BookMarkVO
import online.mengchen.collect_helper.service.BookMarkService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.http.HttpMethod
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
     * 校验 categoryId
     *  - -1 查找是否有未分类的类别，如果没有创建一个
     */
    override fun addBookMark(bookMarkDTO: BookMarkDTO, userDTO: UserDTO): BookMarkVO? {
        if (!validBookMark(bookMarkDTO)) {
            return null
        }
        val user = userRepository.findById(userDTO.userId)
        if (!user.isPresent) {
            return null
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
        }) ?: return null
        val bookMark = BookMark(url = bookMarkDTO.url, createTime = bookMarkDTO.createTime, bookMarkCategory = bookMarkCategory)
        bookMarkRepository.save(bookMark)
        return BookMarkVO(bookMark.id, bookMark.url, bookMark.createTime, bookMark.bookMarkDetail, bookMark.bookMarkCategory)
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

    override fun getBookMarks(pageable: Pageable): Page<BookMarkVO> {
        val bookMarks = bookMarkRepository.findAll(pageable)
        return bookMarks.map {
            BookMarkVO(it.id, it.url, it.createTime, it.bookMarkDetail, it.bookMarkCategory)
        }
    }

}