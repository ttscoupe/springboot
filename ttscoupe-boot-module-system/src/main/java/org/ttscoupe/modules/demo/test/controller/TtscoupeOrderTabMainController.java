package org.ttscoupe.modules.demo.test.controller;

import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.ttscoupe.common.api.vo.Result;
import org.ttscoupe.common.system.query.QueryGenerator;
import org.ttscoupe.modules.demo.test.entity.TtscoupeOrderCustomer;
import org.ttscoupe.modules.demo.test.entity.TtscoupeOrderMain;
import org.ttscoupe.modules.demo.test.entity.TtscoupeOrderTicket;
import org.ttscoupe.modules.demo.test.service.ITtscoupeOrderCustomerService;
import org.ttscoupe.modules.demo.test.service.ITtscoupeOrderMainService;
import org.ttscoupe.modules.demo.test.service.ITtscoupeOrderTicketService;
import org.ttscoupe.modules.demo.test.vo.TtscoupeOrderMainPage;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import lombok.extern.slf4j.Slf4j;

/**
 * @Description: 一对多示例（ERP TAB风格）
 * @Author: ZhiLin
 * @Date: 2019-02-20
 * @Version: v2.0
 */
@Slf4j
@RestController
@RequestMapping("/test/order")
public class TtscoupeOrderTabMainController {

    @Autowired
    private ITtscoupeOrderMainService ttscoupeOrderMainService;
    @Autowired
    private ITtscoupeOrderCustomerService ttscoupeOrderCustomerService;
    @Autowired
    private ITtscoupeOrderTicketService ttscoupeOrderTicketService;

    /**
     * 分页列表查询
     *
     * @param ttscoupeOrderMain
     * @param pageNo
     * @param pageSize
     * @param req
     * @return
     */
    @GetMapping(value = "/orderList")
    public Result<?> respondePagedData(TtscoupeOrderMain ttscoupeOrderMain,
                                       @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                                       @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
                                       HttpServletRequest req) {
        QueryWrapper<TtscoupeOrderMain> queryWrapper = QueryGenerator.initQueryWrapper(ttscoupeOrderMain, req.getParameterMap());
        Page<TtscoupeOrderMain> page = new Page<TtscoupeOrderMain>(pageNo, pageSize);
        IPage<TtscoupeOrderMain> pageList = ttscoupeOrderMainService.page(page, queryWrapper);
        return Result.ok(pageList);
    }

    /**
     * 添加
     *
     * @param ttscoupeOrderMainPage
     * @return
     */
    @PostMapping(value = "/add")
    public Result<?> add(@RequestBody TtscoupeOrderMainPage ttscoupeOrderMainPage) {
        TtscoupeOrderMain ttscoupeOrderMain = new TtscoupeOrderMain();
        BeanUtils.copyProperties(ttscoupeOrderMainPage, ttscoupeOrderMain);
        ttscoupeOrderMainService.save(ttscoupeOrderMain);
        return Result.ok("添加成功!");
    }

    /**
     * 编辑
     *
     * @param ttscoupeOrderMainPage
     * @return
     */
    @PutMapping("/edit")
    public Result<?> edit(@RequestBody TtscoupeOrderMainPage ttscoupeOrderMainPage) {
        TtscoupeOrderMain ttscoupeOrderMain = new TtscoupeOrderMain();
        BeanUtils.copyProperties(ttscoupeOrderMainPage, ttscoupeOrderMain);
        ttscoupeOrderMainService.updateById(ttscoupeOrderMain);
        return Result.ok("编辑成功!");
    }

    /**
     * 通过id删除
     *
     * @param id
     * @return
     */
    @DeleteMapping(value = "/delete")
    public Result<?> delete(@RequestParam(name = "id", required = true) String id) {
        ttscoupeOrderMainService.delMain(id);
        return Result.ok("删除成功!");
    }

    /**
     * 批量删除
     *
     * @param ids
     * @return
     */
    @DeleteMapping(value = "/deleteBatch")
    public Result<?> deleteBatch(@RequestParam(name = "ids", required = true) String ids) {
        this.ttscoupeOrderMainService.removeByIds(Arrays.asList(ids.split(",")));
        return Result.ok("批量删除成功!");
    }

    /**
     * 通过id查询
     *
     * @param id
     * @return
     */
    @GetMapping(value = "/queryById")
    public Result<?> queryById(@RequestParam(name = "id", required = true) String id) {
        TtscoupeOrderMain ttscoupeOrderMain = ttscoupeOrderMainService.getById(id);
        return Result.ok(ttscoupeOrderMain);
    }


    /**
     * 通过id查询
     *
     * @param ttscoupeOrderCustomer
     * @return
     */
    @GetMapping(value = "/listOrderCustomerByMainId")
    public Result<?> queryOrderCustomerListByMainId(TtscoupeOrderCustomer ttscoupeOrderCustomer,
                                                    @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                                                    @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
                                                    HttpServletRequest req) {
        QueryWrapper<TtscoupeOrderCustomer> queryWrapper = QueryGenerator.initQueryWrapper(ttscoupeOrderCustomer, req.getParameterMap());
        Page<TtscoupeOrderCustomer> page = new Page<TtscoupeOrderCustomer>(pageNo, pageSize);
        IPage<TtscoupeOrderCustomer> pageList = ttscoupeOrderCustomerService.page(page, queryWrapper);
        return Result.ok(pageList);
    }

    /**
     * 通过id查询
     *
     * @param ttscoupeOrderTicket
     * @return
     */
    @GetMapping(value = "/listOrderTicketByMainId")
    public Result<?> queryOrderTicketListByMainId(TtscoupeOrderTicket ttscoupeOrderTicket,
                                                  @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                                                  @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
                                                  HttpServletRequest req) {
        QueryWrapper<TtscoupeOrderTicket> queryWrapper = QueryGenerator.initQueryWrapper(ttscoupeOrderTicket, req.getParameterMap());
        Page<TtscoupeOrderTicket> page = new Page<TtscoupeOrderTicket>(pageNo, pageSize);
        IPage<TtscoupeOrderTicket> pageList = ttscoupeOrderTicketService.page(page, queryWrapper);
        return Result.ok(pageList);
    }

    /**
     * 添加
     *
     * @param ttscoupeOrderCustomer
     * @return
     */
    @PostMapping(value = "/addCustomer")
    public Result<?> addCustomer(@RequestBody TtscoupeOrderCustomer ttscoupeOrderCustomer) {
        ttscoupeOrderCustomerService.save(ttscoupeOrderCustomer);
        return Result.ok("添加成功!");
    }

    /**
     * 编辑
     *
     * @param ttscoupeOrderCustomer
     * @return
     */
    @PutMapping("/editCustomer")
    public Result<?> editCustomer(@RequestBody TtscoupeOrderCustomer ttscoupeOrderCustomer) {
        ttscoupeOrderCustomerService.updateById(ttscoupeOrderCustomer);
        return Result.ok("添加成功!");
    }

    /**
     * 通过id删除
     *
     * @param id
     * @return
     */
    @DeleteMapping(value = "/deleteCustomer")
    public Result<?> deleteCustomer(@RequestParam(name = "id", required = true) String id) {
        ttscoupeOrderCustomerService.removeById(id);
        return Result.ok("删除成功!");
    }

    /**
     * 批量删除
     *
     * @param ids
     * @return
     */
    @DeleteMapping(value = "/deleteBatchCustomer")
    public Result<?> deleteBatchCustomer(@RequestParam(name = "ids", required = true) String ids) {
        this.ttscoupeOrderCustomerService.removeByIds(Arrays.asList(ids.split(",")));
        return Result.ok("批量删除成功!");
    }

    /**
     * 添加
     *
     * @param ttscoupeOrderTicket
     * @return
     */
    @PostMapping(value = "/addTicket")
    public Result<?> addTicket(@RequestBody TtscoupeOrderTicket ttscoupeOrderTicket) {
        ttscoupeOrderTicketService.save(ttscoupeOrderTicket);
        return Result.ok("添加成功!");
    }

    /**
     * 编辑
     *
     * @param ttscoupeOrderTicket
     * @return
     */
    @PutMapping("/editTicket")
    public Result<?> editTicket(@RequestBody TtscoupeOrderTicket ttscoupeOrderTicket) {
        ttscoupeOrderTicketService.updateById(ttscoupeOrderTicket);
        return Result.ok("编辑成功!");
    }

    /**
     * 通过id删除
     *
     * @param id
     * @return
     */
    @DeleteMapping(value = "/deleteTicket")
    public Result<?> deleteTicket(@RequestParam(name = "id", required = true) String id) {
        ttscoupeOrderTicketService.removeById(id);
        return Result.ok("删除成功!");
    }

    /**
     * 批量删除
     *
     * @param ids
     * @return
     */
    @DeleteMapping(value = "/deleteBatchTicket")
    public Result<?> deleteBatchTicket(@RequestParam(name = "ids", required = true) String ids) {
        this.ttscoupeOrderTicketService.removeByIds(Arrays.asList(ids.split(",")));
        return Result.ok("批量删除成功!");
    }

}