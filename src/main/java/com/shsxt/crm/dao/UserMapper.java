package com.shsxt.crm.dao;

import com.shsxt.base.BaseDao;
import com.shsxt.crm.vo.User;

public interface UserMapper extends BaseDao <User,Integer>{
    User queryUserByName(String name);

}