package com.example.demo;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.batch.MybatisBatch;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.common.enums.GradeEnum;
import com.example.demo.entity.User;
import com.example.demo.mapper.UserMapper;
import org.apache.ibatis.session.ResultContext;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@SpringBootTest
class DemoApplicationTests {

    @Test
    void contextLoads() {
    }

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private SqlSessionFactory sqlSessionFactory;

    @Test
    public void testSelect() {
        System.out.println(("----- selectAll method test ------"));
        List<User> userList = userMapper.selectList(null);
        Assert.isTrue(5 == userList.size(), "");
        userList.forEach(System.out::println);
    }

    /**
     * 分页批量查询数据——流式操作
     * https://baomidou.com/guides/stream-query/
     */
    @Test
    public void testSelectBatch() {
        Page<User> page = new Page<>(1, 2);
        userMapper.selectList(page, Wrappers.emptyWrapper(), new ResultHandler<User>() {
            int count = 0;

            @Override
            public void handleResult(ResultContext<? extends User> resultContext) {
                User h2User = resultContext.getResultObject();
                System.out.println("当前处理第" + (++count) + "条记录: " + h2User);
                // 在这里进行你的业务处理，比如分发任务
                userMapper.deleteById(h2User.getId());
            }
        });
    }

    /**
     * 批量操作数据，支持自定义方法
     * https://baomidou.com/guides/batch-operation/
     */
    @Test
    public void testMybatisBatch() {
        List<User> userList = Arrays.asList(new User(2000L, "测试"), new User(2001L, "测试"));
        MybatisBatch<User> mybatisBatch = new MybatisBatch<>(sqlSessionFactory, userList);
        MybatisBatch.Method<User> method = new MybatisBatch.Method<>(UserMapper.class);
        mybatisBatch.execute(method.insert());

        Assert.isTrue(userMapper.selectById(2000L) != null, "User not found");
    }

    /**
     * 测试枚举类型自动转换
     * https://baomidou.com/guides/auto-convert-enum/
     */
    @Test
    public void testEnum() {
        userMapper.deleteById(2002L);
        User user = new User(2002L, "测试");
        user.setGrade(GradeEnum.HIGH);

        userMapper.insert(user);
        Assert.isTrue(userMapper.selectById(2002L).getGrade() == GradeEnum.HIGH,
                "Grade not equal HIGH");

        System.out.println(JSON.toJSONString(user));
    }

    /**
     * 配置MetaObjectHandler自动填充createTime和updateTime
     * https://baomidou.com/guides/auto-fill-field/
     */
    @Test
    public void testAutoFill() {
        userMapper.deleteById(2003L);
        User user = new User(2003L, "测试");
        user.setGrade(GradeEnum.PRIMARY);

        userMapper.insert(user);
        User selectUser = userMapper.selectById(2003L);
        Assert.isTrue(selectUser.getCreateTime() != null,
                "Create time should be filled");
        LocalDateTime oldUpdateTime = selectUser.getUpdateTime();

        selectUser.setName("测试2");
        userMapper.updateById(selectUser);
        Assert.isTrue(userMapper.selectById(2003L).getUpdateTime() != oldUpdateTime,
                "Update time should be changed");

        System.out.println(JSON.toJSONString(user));
    }
}
