package com.example.demo.controller;

import com.example.demo.common.dto.PageParam;
import com.example.demo.common.response.RetResult;
import com.example.demo.common.response.Sheet;
import com.example.demo.entity.User;
import com.example.demo.service.IUserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

/**
 swagger2到swagger3的变化规则：
 @Api → @Tag

 @ApiIgnore→@Parameter(hidden = true)或@Operation(hidden = true)或@Hidden

 @ApiImplicitParam → @Parameter

 @ApiImplicitParams → @Parameters

 @ApiModel → @Schema

 @ApiModelProperty(hidden = true) → @Schema(accessMode = READ_ONLY)

 @ApiModelProperty → @Schema

 @ApiOperation(value = "foo", notes = "bar") → @Operation(summary = "foo", description = "bar")

 @ApiParam → @Parameter

 @ApiResponse(code = 404, message = "foo") → @ApiResponse(responseCode = "404", description = "foo")


 */
@Tag(name = "测试文档接口-增删改查", description = "测试文档")
@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    IUserService userService;

    @Operation(summary = "新建用户")
    @PostMapping()
    public RetResult<User> add(@RequestBody User user) {
        return RetResult.success(user);
    }

    @Operation(summary = "删除用户")
    @DeleteMapping("/{id}")
    public RetResult<User> delete(@PathVariable String id) {
        User user = User.builder().name(id).age(18).build();
        return RetResult.success(user);
    }

    @Operation(summary = "修改用户")
    @PutMapping()
    public RetResult<User> update(@RequestBody User user) {
        user.setAge(9999);
        return RetResult.success(user);
    }

    @Operation(summary = "获取单个用户信息")
    @GetMapping("/{id}")
    public RetResult<User> getUser(@PathVariable Long id) {
        return RetResult.success(userService.getById(id));
    }

    @Operation(summary = "获取所有用户")
    @GetMapping()
    public RetResult<Sheet<User>> getAllUsers(PageParam pageParam) {
        return RetResult.success(userService.pageUser(pageParam));
    }

}
