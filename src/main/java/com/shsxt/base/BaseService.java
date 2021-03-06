package com.shsxt.base;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;

import java.util.List;

public class BaseService<T, ID> {
    @Autowired
    private BaseDao<T, ID> baseDao;

    public T selectByPrimaryKey(ID id) throws DataAccessException {
        return baseDao.selectByPrimaryKey(id);
    }

    public Integer updateByPrimaryKeySelective(T t) {
        return baseDao.updateByPrimaryKeySelective(t);
    }
    public List<T> selectByParams(T t){
        return baseDao.selectByParams(t);
    }
}
