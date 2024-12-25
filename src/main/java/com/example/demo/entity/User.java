package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.example.demo.common.enums.GradeEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@Builder
public class User {
    @TableId(type = IdType.AUTO)
    private Long id;
    @Schema(name = "任务号")
    private String name;
    @Schema(name = "年龄")
    private Integer age;
    @Schema(name = "邮箱")
    private String email;

    private GradeEnum grade; // 年级

    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    public User(long l, String test) {
        this.id = l;
        this.name = test;
    }

    public User() {
    }


}
