package com.example.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.entity.User;
import org.apache.ibatis.annotations.Update;

public interface UserMapper extends BaseMapper<User> {
    @Update("update user set name = #{newName} where name =  #{oldName} and id = #{userId}")
    int updateUserName(Long userId, String oldName, String newName);

}
