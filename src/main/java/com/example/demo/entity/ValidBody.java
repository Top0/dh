package com.example.demo.entity;

import jakarta.validation.constraints.*;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

/**
 @Null
 @NotNull
 @AssertTrue
 @AssertFalse
 @Min(value) 被修饰的元素必须是一个数字，且必须大于等于设定的值
 @Max(value) 被修饰的元素必须是一个数字，且必须小于等于设定的值
 @Size(max,min)  被修饰的元素的个数必须在指定范围，适用于集合
 @Digits(integer,fraction) 被修饰的元素不能超过指定位数(包括整数和小数)
 @Past 被修饰的元素必须是一个过去的日期
 @Future 被修饰的元素必须是一个未来的日期
 @Pattern(value) 被修饰的元素必须符合指定的正则表达式
 @Email
 @Length 被修饰的字符串的长度必须在指定范围
 @NotEmpty
 */
@Data
@Builder
public class ValidBody {
    @NotBlank(message = "名称不能为空")
    private String name;

    @Min(value = 1, message = "年龄不能小于1")
    private int age;

    @Email(message = "Email格式不正确")
    @NotNull(message = "Email不能为null")
    private String email;

    @Past(message = "生日必须为过去的时间")
    private LocalDate birthday;
}
