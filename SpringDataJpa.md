# Spring Data jpa 的使用的一些知识点

### Spring Data Jpa 中使用枚举类型

使用枚举类型在属性上添加 `@Enumerated`。

```java
@Target({METHOD, FIELD}) 
@Retention(RUNTIME)
public @interface Enumerated {

    /** (Optional) The type used in mapping an enum type. */
    EnumType value() default ORDINAL;
}

public enum EnumType {
    /** Persist enumerated type property or field as an integer. */
    ORDINAL,

    /** Persist enumerated type property or field as a string. */
    STRING
}
```

上面的注释解释的已经很清楚了。

+ ORDINAL: 存储的是枚举序列。
+ STRING: 存储的是枚举名称。


