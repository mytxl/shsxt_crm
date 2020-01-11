package com.shsxt.base;

import java.util.List;

public interface BaseDao<T, ID> {
    int insertSelective(T t);

    T selectByPrimaryKey(ID id);

    List<T> selectByParams(T t);

    int updateByPrimaryKeySelective(T t);
}
