package com.example.campusbuddy.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.campusbuddy.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper // 标记为 MyBatis Mapper，Spring 会自动注册为 Bean
public interface UserMapper extends BaseMapper<User> {
    // 继承 BaseMapper 后，已内置：
    // selectById, selectList, insert, updateById, deleteById 等 18 个方法
    // 无需写 XML，零 SQL 实现基础 CRUD
}