package com.shsxt.base;

import com.shsxt.crm.vo.User;

public interface BaseDao<T, ID> {
    int insertSelective(T t);

    T selectByPrimaryKey(ID id);

    int updateByPrimaryKeySelective(T t);
}
