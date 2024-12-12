package com.example.demo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.demo.entity.User;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author baomidou
 * @since 2024-09-13
 */
public interface IUserService extends IService<User> {
    List<User> queryAdultUser();
}
