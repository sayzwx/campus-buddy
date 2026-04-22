package com.example.campusbuddy.controller;
import jakarta.validation.Valid;
import com.example.campusbuddy.entity.User;
import com.example.campusbuddy.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController // = @Controller + @ResponseBody，返回 JSON 而非视图
@RequestMapping("/api/users") // 统一前缀
@RequiredArgsConstructor // Lombok：为 final 字段生成构造器，实现依赖注入
public class UserController {
    
    private final UserService userService; // final + 构造器注入（Spring 官方推荐写法）
    
    // GET /api/users → 查询全部
    @GetMapping
    public List<User> list() {
        return userService.list();
    }
    
    // POST /api/users → 新增
    @PostMapping
    public User create(@RequestBody @Valid User user) {
        userService.save(user);
        return user;
    }
    
    // DELETE /api/users/{id} → 删除
    @DeleteMapping("/{id}")
    public boolean delete(@PathVariable Long id) {
        return userService.removeById(id);
    }
}