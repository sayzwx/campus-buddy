package com.example.campusbuddy.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.campusbuddy.entity.User;
import com.example.campusbuddy.mapper.UserMapper;
import org.springframework.stereotype.Service;

@Service // 标记为 Spring 业务层 Bean
public class UserService extends ServiceImpl<UserMapper, User> {
    // 继承 ServiceImpl 后，自带事务管理 + 批量操作 + 链式查询
    // 如：this.list(), this.save(), this.lambdaQuery().eq(...)
}