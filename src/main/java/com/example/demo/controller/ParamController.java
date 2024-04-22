package com.example.demo.controller;

import com.example.demo.entity.User;
import org.springframework.web.bind.annotation.*;

@RestController
public class ParamController {
    /**
     * /noannotation?name=无注解方式&age=18;
     *
     * @param user
     * @return
     */
    @GetMapping("/noannotation")
    public User noAnnotation(User user) {
        return user;
    }

    /**
     * /requestparam?name=@requestparam方式&age=18;
     *
     * @param name
     * @param age
     * @return
     */
    @GetMapping("/requestparam")
    public User requestParam(@RequestParam String name, @RequestParam int age) {
        return User.builder().age(age).name(name).build();
    }

    /**
     * /pathvariable/@PathVariable方式/18;
     *
     * @param name
     * @param age
     * @return
     */
    @GetMapping("/pathvariable/{name}/{age}")
    public User pathVariable(@PathVariable String name, @PathVariable int age) {
        return User.builder().age(age).name(name).build();
    }


    /**
     * /requesbody    {"name":"@RequestBody方式","age":13}
     * @param user
     * @return
     */
    @PostMapping("/requestbody")
    public User requestBody(@RequestBody User user) {
        return user;
    }
}
