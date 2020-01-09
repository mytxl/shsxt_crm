package com.shsxt.crm.service;

import com.shsxt.crm.dao.UserMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
@Service
public class UserService {
    @Resource
    UserMapper userMapper;
}
