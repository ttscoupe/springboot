package org.ttscoupe.modules.demo.test.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.ttscoupe.common.api.vo.Result;
import org.ttscoupe.common.system.base.controller.TtscoupeController;
import org.ttscoupe.common.system.query.QueryGenerator;
import org.ttscoupe.common.system.vo.LoginUser;
import org.ttscoupe.common.util.oConvertUtils;
import org.ttscoupe.modules.demo.test.entity.TtscoupeDemo;
import org.ttscoupe.modules.demo.test.entity.TtscoupeOrderCustomer;
import org.ttscoupe.modules.demo.test.entity.TtscoupeOrderMain;
import org.ttscoupe.modules.demo.test.entity.TtscoupeOrderTicket;
import org.ttscoupe.modules.demo.test.service.ITtscoupeDemoService;
import org.ttscoupe.modules.demo.test.service.ITtscoupeOrderCustomerService;
import org.ttscoupe.modules.demo.test.service.ITtscoupeOrderMainService;
import org.ttscoupe.modules.demo.test.service.ITtscoupeOrderTicketService;
import org.ttscoupe.modules.demo.test.vo.TtscoupeOrderMainPage;
import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.def.NormalExcelConstants;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.poi.excel.view.JeecgEntityExcelView;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import lombok.extern.slf4j.Slf4j;

/**
 * @Description: 一对多示例（JEditableTable行编辑）
 * @Author: ttscoupe-boot
 * @Date:2019-02-15
 * @Version: V2.0
 */
@RestController
@RequestMapping("/test/ttscoupeOrderMain")
@Slf4j
public class TtscoupeOrderMainController extends TtscoupeController<TtscoupeOrderMain, ITtscoupeOrderMainService> {

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
    @GetMapping(value = "/list")
    public Result<?> queryPageList(TtscoupeOrderMain ttscoupeOrderMain, @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo, @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize, HttpServletRequest req) {
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
        ttscoupeOrderMainService.saveMain(ttscoupeOrderMain, ttscoupeOrderMainPage.getTtscoupeOrderCustomerList(), ttscoupeOrderMainPage.getTtscoupeOrderTicketList());
        return Result.ok("添加成功！");
    }

    /**
     * 编辑
     *
     * @param ttscoupeOrderMainPage
     * @return
     */
    @PutMapping(value = "/edit")
    public Result<?> eidt(@RequestBody TtscoupeOrderMainPage ttscoupeOrderMainPage) {
        TtscoupeOrderMain ttscoupeOrderMain = new TtscoupeOrderMain();
        BeanUtils.copyProperties(ttscoupeOrderMainPage, ttscoupeOrderMain);
        ttscoupeOrderMainService.updateMain(ttscoupeOrderMain, ttscoupeOrderMainPage.getTtscoupeOrderCustomerList(), ttscoupeOrderMainPage.getTtscoupeOrderTicketList());
        return Result.ok("编辑成功！");
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
        this.ttscoupeOrderMainService.delBatchMain(Arrays.asList(ids.split(",")));
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
     * @param id
     * @return
     */
    @GetMapping(value = "/queryOrderCustomerListByMainId")
    public Result<?> queryOrderCustomerListByMainId(@RequestParam(name = "id", required = true) String id) {
        List<TtscoupeOrderCustomer> ttscoupeOrderCustomerList = ttscoupeOrderCustomerService.selectCustomersByMainId(id);
        return Result.ok(ttscoupeOrderCustomerList);
    }

    /**
     * 通过id查询
     *
     * @param id
     * @return
     */
    @GetMapping(value = "/queryOrderTicketListByMainId")
    public Result<?> queryOrderTicketListByMainId(@RequestParam(name = "id", required = true) String id) {
        List<TtscoupeOrderTicket> ttscoupeOrderTicketList = ttscoupeOrderTicketService.selectTicketsByMainId(id);
        return Result.ok(ttscoupeOrderTicketList);
    }

    /**
     * 导出excel
     *
     * @param request
     */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, TtscoupeOrderMain ttscoupeOrderMain) {
        // Step.1 组装查询条件
        QueryWrapper<TtscoupeOrderMain> queryWrapper = QueryGenerator.initQueryWrapper(ttscoupeOrderMain, request.getParameterMap());
        //Step.2 AutoPoi 导出Excel
        ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
        //获取当前用户
        LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();

        List<TtscoupeOrderMainPage> pageList = new ArrayList<TtscoupeOrderMainPage>();

        List<TtscoupeOrderMain> ttscoupeOrderMainList = ttscoupeOrderMainService.list(queryWrapper);
        for (TtscoupeOrderMain orderMain : ttscoupeOrderMainList) {
            TtscoupeOrderMainPage vo = new TtscoupeOrderMainPage();
            BeanUtils.copyProperties(orderMain, vo);
            // 查询机票
            List<TtscoupeOrderTicket> ttscoupeOrderTicketList = ttscoupeOrderTicketService.selectTicketsByMainId(orderMain.getId());
            vo.setTtscoupeOrderTicketList(ttscoupeOrderTicketList);
            // 查询客户
            List<TtscoupeOrderCustomer> ttscoupeOrderCustomerList = ttscoupeOrderCustomerService.selectCustomersByMainId(orderMain.getId());
            vo.setTtscoupeOrderCustomerList(ttscoupeOrderCustomerList);
            pageList.add(vo);
        }

        // 导出文件名称
        mv.addObject(NormalExcelConstants.FILE_NAME, "一对多订单示例");
        // 注解对象Class
        mv.addObject(NormalExcelConstants.CLASS, TtscoupeOrderMainPage.class);
        // 自定义表格参数
        mv.addObject(NormalExcelConstants.PARAMS, new ExportParams("自定义导出Excel内容标题", "导出人:" + sysUser.getRealname(), "自定义Sheet名字"));
        // 导出数据列表
        mv.addObject(NormalExcelConstants.DATA_LIST, pageList);
        return mv;
    }

    /**
     * 通过excel导入数据
     *
     * @param request
     * @param
     * @return
     */
    @RequestMapping(value = "/importExcel", method = RequestMethod.POST)
    public Result<?> importExcel(HttpServletRequest request, HttpServletResponse response) {
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();
        for (Map.Entry<String, MultipartFile> entity : fileMap.entrySet()) {
            MultipartFile file = entity.getValue();// 获取上传文件对象
            ImportParams params = new ImportParams();
            params.setTitleRows(2);
            params.setHeadRows(2);
            params.setNeedSave(true);
            try {
                List<TtscoupeOrderMainPage> list = ExcelImportUtil.importExcel(file.getInputStream(), TtscoupeOrderMainPage.class, params);
                for (TtscoupeOrderMainPage page : list) {
                    TtscoupeOrderMain po = new TtscoupeOrderMain();
                    BeanUtils.copyProperties(page, po);
                    ttscoupeOrderMainService.saveMain(po, page.getTtscoupeOrderCustomerList(), page.getTtscoupeOrderTicketList());
                }
                return Result.ok("文件导入成功！");
            } catch (Exception e) {
                log.error(e.getMessage(), e);
                return Result.error("文件导入失败：" + e.getMessage());
            } finally {
                try {
                    file.getInputStream().close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return Result.error("文件导入失败！");
    }

}
