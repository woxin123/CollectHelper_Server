package online.mengchen.collect_helper.pojo

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
data class User(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        var uid: Long? = null,
        var avatar: String = "",
        var phone: String = "",
        var username: String = "",
        var password: String  = ""
)