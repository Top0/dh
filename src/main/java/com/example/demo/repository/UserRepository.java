package com.example.demo.repository;

import com.example.demo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * https://spring.io/projects/spring-data-jpa
 * <p>
 * JPA可以通过方法名字直接实现条件查询：https://docs.spring.io/spring-data/jpa/reference/repositories/query-by-example.html
 * UserRepository 通过继承 JpaRepository 接口，实现了对 User 的操作，也提供了约定方法
 */
public interface UserRepository extends JpaRepository<User, Integer> {

    List<User> findByNameAndAddressStartingWith(String name, String address);

}
