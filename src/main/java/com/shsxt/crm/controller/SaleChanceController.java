package com.shsxt.crm.controller;

import com.shsxt.base.BaseController;
import com.shsxt.crm.model.ResultInfo;
import com.shsxt.crm.query.SaleChanceQuery;
import com.shsxt.crm.service.SaleChanceService;
import com.shsxt.crm.vo.SaleChance;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Map;

@Controller
public class SaleChanceController extends BaseController {
    @Resource
    private SaleChanceService saleChanceService;

    @GetMapping("sale_chance/index")
    public String saleChance() {
        return "sale_chance";
    }

    @PostMapping("sale_chance/list")
    @ResponseBody
    public Map<String, Object> sale_Chance(SaleChanceQuery saleChanceQuery) {
        return saleChanceService.selectSaleChance(saleChanceQuery);
    }

    @PostMapping("sale_chance/save")
    @ResponseBody
    public ResultInfo saveSaleChance(SaleChance saleChance) {
        saleChanceService.addSaleChance(saleChance);
        return success("添加成功");
    }

    @PostMapping("sale_chance/delete")
    @ResponseBody
    public ResultInfo deleteSaleChance(Integer[] ids) {
        saleChanceService.deleteSaleChance(ids);
        return success("添加成功");
    }
    @PostMapping("sale_chance/update")
    @ResponseBody
    public ResultInfo updateSaleChance(SaleChance saleChance) {
        saleChanceService.updateSaleChance(saleChance);
        return success("更新成功");
    }
}
