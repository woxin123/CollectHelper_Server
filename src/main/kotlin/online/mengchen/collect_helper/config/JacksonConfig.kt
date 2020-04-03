package online.mengchen.collect_helper.config

import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer
import com.fasterxml.jackson.datatype.jsr310.deser.LocalTimeDeserializer
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer
import com.fasterxml.jackson.datatype.jsr310.ser.LocalTimeSerializer
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.format.DateTimeFormatter


/**
 * Jackson 的配置
 */cd
@Configuration
class JacksonConfig {

    /**
     * Date格式化字符串
     */
    private val DATE_FORMAT = "yyyy-MM-dd"
    /**
     * DateTime格式化字符串
     */
    private val DATETIME_FORMAT = "yyyy-MM-dd HH:mm:ss"
    /**
     * Time格式化字符串
     */
    private val TIME_FORMAT = "HH:mm:ss"

    /**
     * 自定义Bean
     *
     * @return
     */
    @Bean
    @Primary
    fun jackson2ObjectMapperBuilderCustomizer(): Jackson2ObjectMapperBuilderCustomizer? {
        return Jackson2ObjectMapperBuilderCustomizer { builder: Jackson2ObjectMapperBuilder ->
            builder.serializerByType(LocalDateTime::class.java, LocalDateTimeSerializer(DateTimeFormatter.ofPattern(DATETIME_FORMAT)))
                    .serializerByType(LocalDate::class.java, LocalDateSerializer(DateTimeFormatter.ofPattern(DATE_FORMAT)))
                    .serializerByType(LocalTime::class.java, LocalTimeSerializer(DateTimeFormatter.ofPattern(TIME_FORMAT)))
                    .deserializerByType(LocalDateTime::class.java, LocalDateTimeDeserializer(DateTimeFormatter.ofPattern(DATETIME_FORMAT)))
                    .deserializerByType(LocalDate::class.java, LocalDateDeserializer(DateTimeFormatter.ofPattern(DATE_FORMAT)))
                    .deserializerByType(LocalTime::class.java, LocalTimeDeserializer(DateTimeFormatter.ofPattern(TIME_FORMAT)))
        }
    }
}