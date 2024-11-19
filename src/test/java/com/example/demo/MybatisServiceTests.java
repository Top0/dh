package com.example.demo;

import com.example.demo.entity.User;
import com.example.demo.service.IUserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

@SpringBootTest
class MybatisServiceTests {

    @Test
    void contextLoads() {
    }

    @Autowired
    private IUserService userService;

    @Test
    public void testInsert() {
        // 假设有一个 User 实体对象
        User user = new User();
        user.setId(98L);
        user.setName("John Doe");
        user.setEmail("john.doe@example.com");
        boolean result = userService.save(user); // 调用 save 方法
        if (result) {
            System.out.println("User saved successfully.");
        } else {
            System.out.println("Failed to save user.");
        }

        User user1 = userService.getById(98L);
        Assert.isTrue(user1 != null, "User not found");
        System.out.println("User found: " + user1);
    }


}
