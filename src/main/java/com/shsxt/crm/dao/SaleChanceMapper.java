package com.shsxt.crm.dao;

import com.shsxt.base.BaseDao;
import com.shsxt.crm.query.SaleChanceQuery;
import com.shsxt.crm.vo.SaleChance;

import java.util.List;


public interface SaleChanceMapper extends BaseDao<SaleChance,Integer> {
    List<SaleChance> selectByParam(SaleChanceQuery saleChanceQuery);

    Boolean deleteWSaleChance(Integer[] ids );
}