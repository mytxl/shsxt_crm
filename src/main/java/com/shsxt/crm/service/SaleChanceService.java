package com.shsxt.crm.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.shsxt.base.BaseService;
import com.shsxt.crm.dao.SaleChanceMapper;
import com.shsxt.crm.enums.DevResult;
import com.shsxt.crm.enums.StateStatus;
import com.shsxt.crm.model.ResultInfo;
import com.shsxt.crm.query.SaleChanceQuery;
import com.shsxt.crm.utils.AssertUtil;
import com.shsxt.crm.utils.PhoneUtils;
import com.shsxt.crm.vo.SaleChance;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class SaleChanceService extends BaseService<SaleChance, Integer> {
    @Resource
    SaleChanceMapper saleChanceMapper;

    public Map<String, Object> selectSaleChance(SaleChanceQuery saleChanceQuery) {
        System.out.println(saleChanceQuery);
        Map<String, Object> result = new HashMap<String, Object>();
        PageHelper.startPage(saleChanceQuery.getPage(), saleChanceQuery.getRows());
        PageInfo<SaleChance> pageInfo = new PageInfo<>(saleChanceMapper.selectByParam(saleChanceQuery));
        result.put("total", pageInfo.getTotal());
        result.put("rows", pageInfo.getList());
        return result;
    }

    public void addSaleChance(SaleChance saleChance) {
        checkParms(saleChance);
        saleChance.setState(StateStatus.UNSTATE.getType());
        saleChance.setDevResult(DevResult.UNDEV.getType());
        if (StringUtils.isNotBlank(saleChance.getAssignMan())) {
            saleChance.setState(StateStatus.STATUS.getType());
            saleChance.setDevResult(DevResult.DEING.getType());
            saleChance.setAssignTime(new Date());
        }
        saleChance.setIsValid(1);
        saleChance.setCreateDate(new Date());
        saleChance.setUpdateDate(new Date());
        AssertUtil.isTrue(saleChanceMapper.insertSelective(saleChance) < 1, "添加失败");
    }

    public void updateSaleChance(SaleChance saleChance) {
        AssertUtil.isTrue(null == saleChance.getId(), "待更新记录不存在");
        SaleChance temp = selectByPrimaryKey(saleChance.getId());
        checkParms(saleChance);
        if (StringUtils.isBlank(saleChance.getAssignMan()) && StringUtils.isNotBlank(temp.getAssignMan())) {
            saleChance.setAssignMan("");
            saleChance.setState(StateStatus.UNSTATE.getType());
            saleChance.setAssignMan(null);
            saleChance.setDevResult(DevResult.UNDEV.getType());
        } else if (StringUtils.isNotBlank(saleChance.getAssignMan()) && StringUtils.isBlank(temp.getAssignMan())) {
            saleChance.setState(StateStatus.STATUS.getType());
            saleChance.setDevResult(DevResult.DEING.getType());
            saleChance.setAssignTime(new Date());
        }
        saleChance.setUpdateDate(new Date());
        AssertUtil.isTrue(updateByPrimaryKeySelective(saleChance) < 1, "更新失败");
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void deleteSaleChance(Integer[] ids) {
        AssertUtil.isTrue(ids == null || ids.length < 1, "请选择删除的机会数据");
        AssertUtil.isTrue(!saleChanceMapper.deleteWSaleChance(ids), "删除失败");
    }

    private void checkParms(SaleChance saleChance) {
        AssertUtil.isTrue(StringUtils.isBlank(saleChance.getCustomerName()), "客户不能为空");
        AssertUtil.isTrue(StringUtils.isBlank(saleChance.getLinkMan()), "联系人不能为空");
        AssertUtil.isTrue(StringUtils.isBlank(saleChance.getLinkPhone()), "手机号不能为空");
        AssertUtil.isTrue(!PhoneUtils.phoneTest(saleChance.getLinkPhone()), "手机格式不正确");
    }
}
