package com.example.demo.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.common.dto.PageParam;
import com.example.demo.common.response.Sheet;
import com.example.demo.entity.User;
import com.example.demo.mapper.UserMapper;
import com.example.demo.service.IUserService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author baomidou
 * @since 2024-09-13
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Override
    public List<User> queryAdultUser() {
        // lambda 查询
        lambdaQuery().eq(User::getAge, 18).list();
        // 构建Wrappers查询
        return baseMapper.selectList(Wrappers.<User>lambdaQuery().gt(User::getAge, 18));
    }

    @Override
    public Sheet<User> pageUser(PageParam pageParam) {
        Page<User> page = new Page<>(pageParam.getPage(), pageParam.getSize());
        page.setOrders(pageParam.orderItem());
        Page<User> pageResult = lambdaQuery().page(page);
        // 处理分页参数的封装等
        return Sheet.of(pageResult.getRecords(), pageResult.getTotal());
    }

    @Override
    public void updateUser() {
        // lambda 更新
        lambdaUpdate().gt(User::getAge, 18).set(User::getName, "成年人").update();
        // 构建Wrappers更新
        baseMapper.update(Wrappers.<User>lambdaUpdate().gt(User::getAge, 18).set(User::getName, "成年人"));
    }

    @Override
    public void updateUserName() {
        baseMapper.updateUserName(1L, "test", "test");
    }
}
